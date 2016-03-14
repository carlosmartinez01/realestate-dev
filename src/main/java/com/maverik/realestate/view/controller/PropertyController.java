/**
 * 
 */
package com.maverik.realestate.view.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.maverik.realestate.constants.RealEstateConstants;
import com.maverik.realestate.constants.RealEstateConstants.Actions;
import com.maverik.realestate.constants.RealEstateConstants.ObjectType;
import com.maverik.realestate.constants.RealEstateConstants.RequestParams;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.service.ProjectManagementService;
import com.maverik.realestate.service.PropertyManagementService;
import com.maverik.realestate.view.bean.ActiveUser;
import com.maverik.realestate.view.bean.PermittingMeetingsViewBean;
import com.maverik.realestate.view.bean.ProjectBean;
import com.maverik.realestate.view.bean.PropertyBean;
import com.maverik.realestate.view.bean.PropertyContractViewBean;

/**
 * @author jorge
 *
 */

@Controller
@RequestMapping(value = "/properties")
public class PropertyController {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(PropertyController.class);

    private static final String VIEW_DETAILS_FORM = "propertyView";

    private static final String PERMITTING_DETAILS_FORM = "permittingView";

    private static final String VIEW_PROPERTY_RESEARCH_URL = "/secured/propertyResearch";

    private static final String VIEW_PROPERTY_CONTRACT_URL = "/secured/propertyContract";

    private static final String VIEW_PROPERTY_PERMITTING_URL = "/secured/propertyPermitting";

    private static final String PARAM_OBJECT_TYPE = "objectType";

    private static final String UPDATE_SUCESS_MESSAGE = "Update property has been successful";

    @Autowired
    private PropertyManagementService propertyManagementService;

    @Autowired
    private ProjectManagementService projectManagementService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllProperties(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser)
	    throws GenericException {
	LOGGER.info("Pulling properties registered");

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	model.addAttribute("newProperties",
		propertyManagementService.findPropertiesByResearchPhase());
	model.addAttribute("contractProperties",
		propertyManagementService.findPropertiesByContractPhase());
	model.addAttribute("permittingProperties",
		propertyManagementService.findPropertiesByPermittingPhase());
	model.addAttribute("permittingTasksProperties",
		propertyManagementService
			.findPropertiesByPermittingTasksPhase());

	return "/secured/viewAllProjects";
    }

    @RequestMapping(value = "/{propertyId}/summary", method = RequestMethod.GET)
    public String showPropertySummary(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long propertyId, HttpSession session)
	    throws GenericException {
	LOGGER.info("Pulling property summary for {}", propertyId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	PropertyBean propertySummary = propertyManagementService
		.findByProperty(propertyId);
	model.addAttribute("PROPERTY_SUMMARY", propertySummary);
	session.setAttribute(RequestParams.PROPERTY_ID.toString(), propertyId);
	model.addAttribute(RequestParams.PROPERTY_ID.toString(), propertyId);

	return "/secured/viewPropertySummary";
    }

    @RequestMapping(value = "/{propertyId}/addProject", method = RequestMethod.POST)
    public String addProjectToProperty(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long propertyId,
	    @RequestParam(required = true) Long projectId)
	    throws GenericException {
	LOGGER.info("Adding project to property {}", propertyId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	PropertyBean property = propertyManagementService
		.findByProperty(propertyId);
	List<ProjectBean> lstProjects = new ArrayList<ProjectBean>();
	lstProjects.add(projectManagementService.findByProject(projectId));
	propertyManagementService.insertPropertyWithProjects(property,
		lstProjects);
	model.addAttribute("messageForm", "Project Added to Property");

	return "/secured/viewPropertySummary";
    }

    @RequestMapping(value = "/addResearch", method = RequestMethod.POST)
    public String addProperty(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @ModelAttribute(VIEW_DETAILS_FORM) @Valid PropertyBean property,
	    BindingResult result) throws GenericException {
	LOGGER.info("The following property is going to be added {}", property);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	model.addAttribute(PARAM_OBJECT_TYPE, ObjectType.PROPERTY.toString());
	model.addAttribute(
		RealEstateConstants.RequestParams.USERNAME.toString(),
		activeUser.getUsername());
	if (result.hasErrors()) {
	    return VIEW_PROPERTY_RESEARCH_URL;
	} else {
	    property = propertyManagementService.insertProperty(property);
	    model.addAttribute("messageForm", "New property added successful");
	}
	model.addAttribute(VIEW_DETAILS_FORM, property);
	model.addAttribute(RequestParams.PROPERTY_ID.toString(),
		property.getId());

	return VIEW_PROPERTY_RESEARCH_URL;
    }

    @RequestMapping(value = "/updateResearch", method = RequestMethod.POST)
    public String updateResearch(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @ModelAttribute(VIEW_DETAILS_FORM) @Valid PropertyBean property,
	    BindingResult result) throws GenericException {
	LOGGER.info("The following property is going to be updated {}",
		property);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	model.addAttribute(PARAM_OBJECT_TYPE, ObjectType.PROPERTY.toString());
	model.addAttribute(
		RealEstateConstants.RequestParams.USERNAME.toString(),
		activeUser.getUsername());

	if (result.hasErrors()) {
	    return VIEW_PROPERTY_RESEARCH_URL;
	} else {
	    property = propertyManagementService.updateProperty(property);
	    model.addAttribute("messageForm",
		    "Update property has been successful");
	}
	model.addAttribute(VIEW_DETAILS_FORM, property);
	model.addAttribute(RequestParams.PROPERTY_ID.toString(),
		property.getId());

	return VIEW_PROPERTY_RESEARCH_URL;
    }

    @RequestMapping(value = "/moveResearch", method = RequestMethod.POST)
    public String moveToContract(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @ModelAttribute(VIEW_DETAILS_FORM) @Valid PropertyBean property,
	    BindingResult result) throws GenericException {
	LOGGER.info("Moving to Contract phase the following property {}",
		property);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	model.addAttribute(VIEW_DETAILS_FORM, property);
	model.addAttribute(RequestParams.PROPERTY_ID.toString(),
		property.getId());
	model.addAttribute(PARAM_OBJECT_TYPE, ObjectType.PROPERTY.toString());
	model.addAttribute(
		RealEstateConstants.RequestParams.USERNAME.toString(),
		activeUser.getUsername());
	if (result.hasErrors()) {
	    return VIEW_PROPERTY_RESEARCH_URL;
	} else {
	    propertyManagementService.updateProperty(property);
	    propertyManagementService.createContract(property);
	    model.addAttribute("messageForm",
		    "Moving to contract phase was successful");
	}

	return VIEW_PROPERTY_RESEARCH_URL;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String requestAddProperty(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser)
	    throws GenericException {
	LOGGER.info("Redirect to New property page");

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	model.addAttribute(VIEW_DETAILS_FORM, new PropertyBean());
	model.addAttribute(RequestParams.ACTION.toString(),
		Actions.ADD.toString());

	return VIEW_PROPERTY_RESEARCH_URL;
    }

    @RequestMapping(value = "/{propertyId}/research", method = RequestMethod.GET)
    public String getPropertyResearch(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long propertyId) throws GenericException {
	LOGGER.info("Property Research Id {}", propertyId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	PropertyBean property = propertyManagementService
		.findByProperty(propertyId);
	model.addAttribute(VIEW_DETAILS_FORM, property);
	model.addAttribute(RequestParams.ACTION.toString(),
		Actions.UPDATE.toString());
	model.addAttribute(RequestParams.PROPERTY_ID.toString(),
		property.getId());
	model.addAttribute(PARAM_OBJECT_TYPE, ObjectType.PROPERTY.toString());
	model.addAttribute(
		RealEstateConstants.RequestParams.USERNAME.toString(),
		activeUser.getUsername());

	return VIEW_PROPERTY_RESEARCH_URL;
    }

    @RequestMapping(value = "/{propertyId}/contract", method = RequestMethod.GET)
    public String getPropertyContract(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long propertyId) throws GenericException {
	LOGGER.info("Property Contract id {}", propertyId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	PropertyContractViewBean propertyContract = propertyManagementService
		.findPropertyContract(propertyId);
	model.addAttribute(VIEW_DETAILS_FORM, propertyContract);
	model.addAttribute(RequestParams.ACTION.toString(),
		Actions.UPDATE.toString());
	model.addAttribute(RequestParams.PROPERTY_ID.toString(),
		propertyContract.getProperty().getId());
	model.addAttribute(PARAM_OBJECT_TYPE, ObjectType.PROPERTY.toString());
	model.addAttribute(RequestParams.USERNAME.toString(),
		activeUser.getUsername());
	model.addAttribute("CONTRACT_SELECTED", propertyContract
		.getPropertyContract().getContractType());

	return VIEW_PROPERTY_CONTRACT_URL;
    }

    @RequestMapping(value = "/saveContract/{propertyId}/{contractId}", method = RequestMethod.POST)
    public String saveContract(
	    Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @ModelAttribute(VIEW_DETAILS_FORM) @Valid PropertyContractViewBean propertyContract,
	    BindingResult result, @PathVariable Long propertyId,
	    @RequestParam(required = false) Byte currentStatus,
	    @PathVariable Long contractId) throws GenericException {
	LOGGER.info(
		"{} property to be updated / property to move to next phase",
		propertyContract.getProperty());

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());

	PropertyBean p = new PropertyBean();
	p.setId(propertyId);
	p.setStatus(currentStatus);
	propertyContract.setProperty(p);
	propertyContract.getPropertyContract().setId(contractId);
	model.addAttribute(VIEW_DETAILS_FORM, propertyContract);
	model.addAttribute(RequestParams.PROPERTY_ID.toString(),
		propertyContract.getProperty().getId());
	model.addAttribute(PARAM_OBJECT_TYPE, ObjectType.PROPERTY.toString());
	model.addAttribute(
		RealEstateConstants.RequestParams.USERNAME.toString(),
		activeUser.getUsername());
	model.addAttribute("CONTRACT_SELECTED", propertyContract
		.getPropertyContract().getContractType());
	String message = "";
	if (result.hasErrors()) {
	    return VIEW_PROPERTY_CONTRACT_URL;
	} else {
	    propertyManagementService.savePropertyContract(propertyContract);
	    message = "Update property has been successful";
	    model.addAttribute("messageForm", message);
	}

	return VIEW_PROPERTY_CONTRACT_URL;
    }

    @RequestMapping(value = "/moveContract/{propertyId}/{contractId}", method = RequestMethod.POST)
    public String moveToLandPermittingPhase(
	    Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @ModelAttribute(VIEW_DETAILS_FORM) @Valid PropertyContractViewBean propertyContract,
	    BindingResult result, @PathVariable Long propertyId,
	    @RequestParam(required = false) Byte currentStatus,
	    @PathVariable Long contractId,
	    final RedirectAttributes redirectAttributes)
	    throws GenericException {
	LOGGER.info(
		"{} property to be updated / property to move to next phase",
		propertyContract.getProperty());

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	PropertyBean p = new PropertyBean();
	p.setId(propertyId);
	p.setStatus(currentStatus);
	propertyContract.setProperty(p);
	propertyContract.getPropertyContract().setId(contractId);
	model.addAttribute(VIEW_DETAILS_FORM, propertyContract);
	model.addAttribute(RequestParams.PROPERTY_ID.toString(),
		propertyContract.getProperty().getId());
	model.addAttribute(PARAM_OBJECT_TYPE, ObjectType.PROPERTY.toString());
	model.addAttribute(
		RealEstateConstants.RequestParams.USERNAME.toString(),
		activeUser.getUsername());
	model.addAttribute("CONTRACT_SELECTED", propertyContract
		.getPropertyContract().getContractType());
	String message = "";
	if (result.hasErrors()) {
	    return VIEW_PROPERTY_CONTRACT_URL;
	} else {
	    propertyContract.getProperty().setStatus((byte) 2);
	    propertyManagementService.savePropertyContract(propertyContract);
	    propertyManagementService
		    .createLandPermittingAndUpdateProperty(propertyContract);
	    message = "Update property has been successful";
	    model.addAttribute("messageForm", message);
	}

	redirectAttributes.addFlashAttribute("messageForm", message);

	return "redirect:/properties/" + propertyId + "/permitting";
    }

    @RequestMapping(value = "/{propertyId}/permitting", method = RequestMethod.GET)
    public String getPropertyPermitting(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long propertyId) throws GenericException {
	LOGGER.info("Land Use Permitting - Property id {}", propertyId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	PermittingMeetingsViewBean view = propertyManagementService
		.findLandPermittingByProperty(propertyId);
	model.addAttribute(PERMITTING_DETAILS_FORM, view);
	model.addAttribute(RequestParams.PROPERTY_ID.toString(), view
		.getPermitting().getPropertyId());
	model.addAttribute(PARAM_OBJECT_TYPE, ObjectType.PROPERTY.toString());
	model.addAttribute(RequestParams.USERNAME.toString(),
		activeUser.getUsername());

	return VIEW_PROPERTY_PERMITTING_URL;
    }

    @RequestMapping(value = "/savePermitting/{propertyId}/{permittingId}", method = RequestMethod.POST)
    public String saveLandPermitting(
	    Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @ModelAttribute(PERMITTING_DETAILS_FORM) @Valid PermittingMeetingsViewBean propertyPermitting,
	    BindingResult result, @PathVariable Long propertyId,
	    @RequestParam(required = false) Byte currentStatus,
	    @PathVariable Long permittingId) throws GenericException {
	LOGGER.info("Save {} Property Land Permitting", propertyPermitting);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	propertyPermitting.getPermitting().setPropertyId(propertyId);
	propertyPermitting.getPermitting().setId(permittingId);
	model.addAttribute(VIEW_DETAILS_FORM, propertyPermitting);
	model.addAttribute(RequestParams.PROPERTY_ID.toString(),
		propertyPermitting.getPermitting().getPropertyId());
	model.addAttribute(PARAM_OBJECT_TYPE, ObjectType.PROPERTY.toString());
	model.addAttribute(
		RealEstateConstants.RequestParams.USERNAME.toString(),
		activeUser.getUsername());
	if (result.hasErrors()) {
	    return VIEW_PROPERTY_PERMITTING_URL;
	} else {
	    propertyManagementService
		    .savePropertyPermitting(propertyPermitting);
	    model.addAttribute("messageForm", UPDATE_SUCESS_MESSAGE);
	}

	return VIEW_PROPERTY_PERMITTING_URL;
    }

    @RequestMapping(value = "/movePermittingLand/{propertyId}/{permittingId}", method = RequestMethod.POST)
    public String moveToPermittingTaskPhase(
	    Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @ModelAttribute(PERMITTING_DETAILS_FORM) @Valid PermittingMeetingsViewBean propertyPermitting,
	    BindingResult result, @PathVariable Long propertyId,
	    @RequestParam(required = false) Byte currentStatus,
	    @PathVariable Long permittingId,
	    final RedirectAttributes redirectAttributes)
	    throws GenericException {
	LOGGER.info("Property {} is moving to Permitting Task Phase",
		propertyPermitting.getPermitting().getPropertyId());

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	propertyPermitting.getPermitting().setPropertyId(propertyId);
	propertyPermitting.getPermitting().setId(permittingId);
	model.addAttribute(VIEW_DETAILS_FORM, propertyPermitting);
	model.addAttribute(RequestParams.PROPERTY_ID.toString(),
		propertyPermitting.getPermitting().getPropertyId());
	model.addAttribute(PARAM_OBJECT_TYPE, ObjectType.PROPERTY.toString());
	model.addAttribute(
		RealEstateConstants.RequestParams.USERNAME.toString(),
		activeUser.getUsername());
	if (result.hasErrors()) {
	    return VIEW_PROPERTY_PERMITTING_URL;
	} else {
	    propertyManagementService
		    .updatePropertyToPermittingTaskPhase(propertyPermitting
			    .getPermitting());
	    model.addAttribute("messageForm", UPDATE_SUCESS_MESSAGE);
	}

	redirectAttributes.addFlashAttribute("messageForm",
		UPDATE_SUCESS_MESSAGE);

	return "redirect:/properties/" + propertyId + "/permitting/tasks";
    }

    @RequestMapping(value = "/{propertyId}/permitting/tasks", method = RequestMethod.GET)
    public String getPermittingTask(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long propertyId) throws GenericException {
	LOGGER.info("Getting all permitting tasks related to Property id {}",
		propertyId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	model.addAttribute("users", propertyManagementService
		.getTaskAvailableUsersByProperty(propertyId));
	model.addAttribute(RequestParams.PROPERTY_ID.toString(), propertyId);
	model.addAttribute(PARAM_OBJECT_TYPE, ObjectType.PROPERTY.toString());
	model.addAttribute(RequestParams.USERNAME.toString(),
		activeUser.getUsername());

	return "/secured/permittingTask";
    }
}
