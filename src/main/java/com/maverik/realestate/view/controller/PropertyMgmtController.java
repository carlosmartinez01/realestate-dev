package com.maverik.realestate.view.controller;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

import com.maverik.realestate.constants.RealEstateConstants.Actions;
import com.maverik.realestate.constants.RealEstateConstants.ObjectType;
import com.maverik.realestate.constants.RealEstateConstants.RequestParams;
import com.maverik.realestate.constants.RealEstateConstants.Strings;
import com.maverik.realestate.exception.DBException;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.service.PropertyManagementService;
import com.maverik.realestate.service.UserManagementService;
import com.maverik.realestate.view.bean.ActiveUser;
import com.maverik.realestate.view.bean.PropertyBean;

@Controller
@RequestMapping(value = "/property")
public class PropertyMgmtController {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(PropertyMgmtController.class);

    private static final String VIEW_DETAILS_URL = "/secured/viewPropertyDetails";

    private static final String VIEW_DETAILS_FORM = "propertyView";

    private static final String ACTION = "action";

    @Autowired
    private PropertyManagementService propertyManagementService;

    @Autowired
    private UserManagementService userManagementService;

    @RequestMapping(method = RequestMethod.GET)
    public String getProperties(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser)
	    throws GenericException {
	LOGGER.info("Getting all properties registered");

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	List<PropertyBean> properties = propertyManagementService
		.findAllProperties();
	model.addAttribute("lstProperties", properties);
	// Map<String, PropertyBean> propertyDetails = new HashMap<String,
	// PropertyBean>();
	// for (PropertyBean property : properties) {
	// propertyDetails.put(property.getName(), property);
	// }
	// model.addAttribute("propertiesSession", propertyDetails);
	Collection<? extends GrantedAuthority> authorities = activeUser
		.getAuthorities();
	boolean authorized = authorities.contains(new SimpleGrantedAuthority(
		"ROLE_ADMINISTRATOR"));
	if (authorized) {
	    model.addAttribute("authorized", authorized);
	}

	return "/secured/propertyMgmt";
    }

    @RequestMapping(value = "/{propertyId}/update", method = RequestMethod.GET)
    public String getPropertyDetails(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long propertyId) throws GenericException {
	LOGGER.info("Getting {} property Details", propertyId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	PropertyBean property = propertyManagementService
		.findByProperty(propertyId);
	model.addAttribute(VIEW_DETAILS_FORM, property);
	model.addAttribute(ACTION, Actions.UPDATE.toString());
	model.addAttribute("propertyOID", property.getId());
	model.addAttribute("objectType", ObjectType.PROPERTY.toString());

	return VIEW_DETAILS_URL;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addProperty(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser) throws DBException {
	LOGGER.info("Add property");

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	model.addAttribute(VIEW_DETAILS_FORM, new PropertyBean());
	model.addAttribute(ACTION, Actions.ADD.toString());
	model.addAttribute("addNote", Strings.NOT_EMPTY);

	return VIEW_DETAILS_URL;
    }

    @RequestMapping(value = "/{action}/addOrUpdate", method = RequestMethod.POST)
    public String editOrAddProperty(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @ModelAttribute("propertyView") @Valid PropertyBean property,
	    BindingResult result, @PathVariable String action,
	    @RequestParam(required = true) Long propertyId)
	    throws GenericException {
	LOGGER.info("{} property to be updated / property to be added",
		property);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	if (action == null) {
	    throw new GenericException(
		    "Unable to process the request - Please contact support team",
		    "Unable to process the request", "N/A");
	}
	if (action.equalsIgnoreCase(Actions.UPDATE.toString())) {
	    property.setId(propertyId);
	}
	model.addAttribute(VIEW_DETAILS_FORM, property);
	model.addAttribute("propertyOID", property.getId());
	model.addAttribute("objectType", ObjectType.PROPERTY.toString());
	String message = "";
	if (result.hasErrors()) {
	    if (!action.equalsIgnoreCase(Actions.UPDATE.toString())) {
		model.addAttribute(ACTION, Actions.ADD.toString());
	    }
	    return "/secured/viewPropertyDetails";
	} else {
	    if (action.equalsIgnoreCase(Actions.ADD.toString())) {
		model.addAttribute(ACTION, Actions.ADD.toString());
		propertyManagementService.insertProperty(property);
		message = "New property added successful";
	    } else if (action.equalsIgnoreCase(Actions.UPDATE.toString())) {
		message = "Update property has been successful";
		propertyManagementService.updateProperty(property);
	    }
	    model.addAttribute("messageForm", message);
	}

	return VIEW_DETAILS_URL;
    }

    @RequestMapping(value = "/addProperty", method = RequestMethod.POST)
    public String addProperty(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @ModelAttribute("propertyView") @Valid PropertyBean property,
	    BindingResult result) throws GenericException {
	LOGGER.info("Adding the following property {}", property);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	if (result.hasErrors()) {
	    return VIEW_DETAILS_URL;
	} else {
	    property = propertyManagementService.insertProperty(property);
	    model.addAttribute("messageForm", "New property added successful");
	    model.addAttribute("propertyOID", property.getId());
	}
	model.addAttribute(VIEW_DETAILS_FORM, property);
	model.addAttribute("objectType", ObjectType.PROPERTY.toString());

	return VIEW_DETAILS_URL;
    }

    @RequestMapping(value = "/updateProperty", method = RequestMethod.POST)
    public String updateProperty(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @ModelAttribute("propertyView") @Valid PropertyBean property,
	    BindingResult result) throws GenericException {
	LOGGER.info("Updating the following property {}", property);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	if (result.hasErrors()) {
	    return "/secured/viewPropertyDetails";
	} else {
	    property = propertyManagementService.updateProperty(property);
	    model.addAttribute("messageForm",
		    "Update property has been successful");
	}
	model.addAttribute(VIEW_DETAILS_FORM, property);
	model.addAttribute("propertyOID", property.getId());
	model.addAttribute("objectType", ObjectType.PROPERTY.toString());

	return VIEW_DETAILS_URL;
    }

    @RequestMapping(value = "/{propertyId}/delete", method = RequestMethod.GET)
    public String deleteProperty(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long propertyId,
	    final RedirectAttributes redirectAttributes)
	    throws GenericException {
	LOGGER.info("Delete property id {}", propertyId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	String message = "";
	PropertyBean property = propertyManagementService
		.findByProperty(propertyId);
	propertyManagementService.deleteProperty(property);
	message = "Property has been deleted successful";
	redirectAttributes.addFlashAttribute("messageForm", message);

	return "redirect:/property";
    }
}