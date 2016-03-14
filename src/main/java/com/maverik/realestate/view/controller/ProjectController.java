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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.maverik.realestate.constants.RealEstateConstants.ObjectType;
import com.maverik.realestate.constants.RealEstateConstants.RequestParams;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.response.GenericResponse;
import com.maverik.realestate.service.ProjectManagementService;
import com.maverik.realestate.view.bean.ActiveUser;
import com.maverik.realestate.view.bean.ArchitectDrawingBean;
import com.maverik.realestate.view.bean.ProjectBean;
import com.maverik.realestate.view.bean.ProjectCloseOutBean;
import com.maverik.realestate.view.bean.ProjectManagementBean;
import com.maverik.realestate.view.bean.ProjectPreConstructionBean;
import com.maverik.realestate.view.bean.PropertyBean;

@Controller
@RequestMapping(value = "/projects")
public class ProjectController {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(ProjectController.class);

    @Autowired
    private ProjectManagementService projectManagementService;

    private static final String PARAM_OBJECT_TYPE = "objectType";

    private static final String PARAM_PROJECT_ID = "projectOID";

    @RequestMapping(method = RequestMethod.GET)
    public String getProjects(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser) {
	LOGGER.info("Get Projects");

	model.addAttribute("userFullName", activeUser.getUserFullName());
	String msg = "This page will get all projects information and display the project menu bar";
	model.addAttribute("projectMsg", msg);

	return "/secured/viewAllProjects";
    }

    @RequestMapping(value = "/menuNavigation", method = RequestMethod.GET)
    public String insertMenuNav(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser) {
	LOGGER.info("Loading navigation bar");

	model.addAttribute("userFullName", activeUser.getUserFullName());

	return "/secured/menuNavegation";
    }

    @RequestMapping(value = "/{projectId}/addUser", method = RequestMethod.POST)
    public String addUserToProject(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long projectId,
	    @RequestParam(required = true) Long userId) throws GenericException {
	LOGGER.info("Adding a User to property {}", projectId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	List<Long> users = new ArrayList<Long>();
	users.add(userId);
	projectManagementService.addUsersAssignedToProject(projectId, users);
	model.addAttribute("messageForm", "User added to Project successfully");

	return "/secured/viewPropertySummary";
    }

    @RequestMapping(value = "/add/{propertyId}", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse addProject(@RequestBody @Valid ProjectBean project,
	    BindingResult result, @PathVariable Long propertyId) {
	LOGGER.info("Adding new project to property id {}", propertyId);

	GenericResponse response = new GenericResponse();
	try {
	    if (result.hasErrors()) {
		response.setErrorMessage("Project name must not be Empty!");
		return response;
	    }
	    ProjectBean p = projectManagementService.insertProject(project);
	    PropertyBean property = new PropertyBean();
	    property.setId(propertyId);
	    projectManagementService.createNextProjectPhases(property, p);
	    if (p == null) {
		response.setErrorMessage("No valid property!");
		return response;
	    }
	    response.setSuccessMessage("Project successfully added");
	} catch (GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage("Unable to create project");
	}

	return response;
    }

    @RequestMapping(value = "/{propertyId}/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<ProjectBean> showProjects(@PathVariable Long propertyId)
	    throws GenericException {
	LOGGER.info("Show all projects assigned to property id {}", propertyId);

	return projectManagementService.getProjectsByProperty(propertyId);
    }

    @RequestMapping(value = "/{projectId}/preconstruction", method = RequestMethod.GET)
    public String getPreConstruction(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long projectId) throws GenericException {
	LOGGER.info("Get preconstruction for project id {}", projectId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	ProjectPreConstructionBean bean = projectManagementService
		.getPreConstruction(projectId);
	bean.setDrawings(projectManagementService.getArchitectDrawing(bean
		.getId()));
	model.addAttribute("preConstructionForm", bean);
	model.addAttribute(PARAM_PROJECT_ID, projectId);
	model.addAttribute(PARAM_OBJECT_TYPE, ObjectType.PROJECT.toString());
	model.addAttribute(RequestParams.USERNAME.toString(),
		activeUser.getUsername());

	return "/secured/preConstruction";
    }

    @RequestMapping(value = "/{projectId}/summary", method = RequestMethod.POST)
    public String getProjectSummary(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long projectId, HttpSession session)
	    throws GenericException {
	LOGGER.info("Get summary for project id {}", projectId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	model.addAttribute("projectDetails",
		projectManagementService.findByProject(projectId));
	session.setAttribute("projectOID", projectId);

	return "/secured/viewProjectSummary";
    }

    @RequestMapping(value = "/{projectId}/preconstruction/save", method = RequestMethod.POST)
    public String savePreConstruction(
	    Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long projectId,
	    @ModelAttribute("preConstructionForm") @Valid ProjectPreConstructionBean bean,
	    BindingResult result) throws GenericException {
	LOGGER.info("Save preconstruction for {}", projectId);

	if (result.hasErrors()) {
	    model.addAttribute("messageForm", "The form has some errors");
	    return "/secured/preConstruction";
	}
	bean = projectManagementService.savePreConstruction(bean);
	bean.setDrawings(projectManagementService.getArchitectDrawing(bean
		.getId()));
	model.addAttribute("preConstructionForm", bean);
	model.addAttribute("messageForm", "Update Successfully");
	model.addAttribute(PARAM_PROJECT_ID, projectId);
	model.addAttribute(PARAM_OBJECT_TYPE, ObjectType.PROJECT.toString());
	model.addAttribute(RequestParams.USERNAME.toString(),
		activeUser.getUsername());

	return "/secured/preConstruction";
    }

    @RequestMapping(value = "/{preconstructionId}/architect/save", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse saveArchitectDrawingDetails(
	    @PathVariable Long preconstructionId,
	    @RequestBody @Valid ProjectPreConstructionBean bean,
	    BindingResult result) {
	LOGGER.info("Save architect drawing details for preconstruction id {}",
		preconstructionId);

	GenericResponse response = new GenericResponse();
	if (result.hasErrors()) {
	    response.setErrorMessage(result.getFieldError().getDefaultMessage());
	    return response;
	}
	try {
	    bean.setId(preconstructionId);
	    projectManagementService.saveDrawingDetails(bean);
	    response.setSuccessMessage("Architect Drawings was save successfully");
	} catch (GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage("Unable to save Architect Drawings");
	}

	return response;
    }

    @RequestMapping(value = "/{preconstructionId}/get/drawings", method = RequestMethod.POST)
    @ResponseBody
    public List<ArchitectDrawingBean> getUpdateDrawingDetails(
	    @PathVariable Long preconstructionId) throws GenericException {
	LOGGER.info("Get updated drawings for preconstruction id {}",
		preconstructionId);

	return projectManagementService.getArchitectDrawing(preconstructionId);
    }

    @RequestMapping(value = "/{projectId}/management", method = RequestMethod.POST)
    public String getProjectManagement(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long projectId) throws GenericException {
	LOGGER.info("Get project management for project id {}", projectId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	ProjectManagementBean bean = projectManagementService
		.getProjectManagement(projectId);
	model.addAttribute("managementForm", bean);
	model.addAttribute(PARAM_PROJECT_ID, projectId);
	model.addAttribute(PARAM_OBJECT_TYPE, ObjectType.PROJECT.toString());
	model.addAttribute(RequestParams.USERNAME.toString(),
		activeUser.getUsername());

	return "/secured/management";
    }

    @RequestMapping(value = "/{managementId}/management/save", method = RequestMethod.POST)
    public String saveProjectManagement(
	    Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long managementId,
	    @ModelAttribute("managementForm") @Valid ProjectManagementBean bean,
	    BindingResult result) throws GenericException {
	LOGGER.info("Save project management id {}", managementId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	bean.setId(managementId);
	bean = projectManagementService.saveManagement(bean);
	model.addAttribute("messageForm",
		"Update Project Management Successfully");
	model.addAttribute("managementForm", bean);
	model.addAttribute(PARAM_PROJECT_ID, bean.getProject());
	model.addAttribute(PARAM_OBJECT_TYPE, ObjectType.PROJECT.toString());
	model.addAttribute(RequestParams.USERNAME.toString(),
		activeUser.getUsername());

	return "/secured/management";
    }

    @RequestMapping(value = "/{managementId}/management/move", method = RequestMethod.POST)
    public String moveProjectManagement(
	    Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long managementId,
	    @ModelAttribute("managementForm") @Valid ProjectManagementBean bean,
	    BindingResult result, final RedirectAttributes redirectAttributes)
	    throws GenericException {
	LOGGER.info("Move project management id {}", managementId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	bean.setId(managementId);
	projectManagementService.moveToCloseOut(bean);
	redirectAttributes.addFlashAttribute("messageForm",
		"Moving to Project Close Out");
	redirectAttributes.addFlashAttribute(PARAM_PROJECT_ID,
		bean.getProject());
	redirectAttributes.addFlashAttribute(PARAM_OBJECT_TYPE,
		ObjectType.PROJECT.toString());
	redirectAttributes.addFlashAttribute(RequestParams.USERNAME.toString(),
		activeUser.getUsername());

	return "redirect:/properties/" + bean.getProject() + "/close-out";
    }

    @RequestMapping(value = "/{projectId}/close-out", method = RequestMethod.POST)
    public String getProjectCloseOut(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long projectId) throws GenericException {
	LOGGER.info("Get project close-out for project id {}", projectId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	ProjectCloseOutBean bean = projectManagementService
		.getProjectCloseOut(projectId);
	model.addAttribute("closeOutForm", bean);
	model.addAttribute(PARAM_PROJECT_ID, projectId);
	model.addAttribute(PARAM_OBJECT_TYPE, ObjectType.PROJECT.toString());
	model.addAttribute(RequestParams.USERNAME.toString(),
		activeUser.getUsername());

	return "/secured/close-out";
    }

    @RequestMapping(value = "/{closeOutId}/close-out/save", method = RequestMethod.POST)
    public String saveProjectCloseOut(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long closeOutId,
	    @ModelAttribute("closeOutForm") @Valid ProjectCloseOutBean bean,
	    BindingResult result) throws GenericException {
	LOGGER.info("Save project close out id {}", closeOutId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	bean.setId(closeOutId);
	bean = projectManagementService.saveCloseOut(bean);
	model.addAttribute("messageForm",
		"Update Project Close Out was Successfully");
	model.addAttribute("closeOutForm", bean);
	model.addAttribute(PARAM_PROJECT_ID, bean.getProject());
	model.addAttribute(PARAM_OBJECT_TYPE, ObjectType.PROJECT.toString());
	model.addAttribute(RequestParams.USERNAME.toString(),
		activeUser.getUsername());

	return "/secured/close-out";
    }

    @RequestMapping(value = "/{projectId}/close-project", method = RequestMethod.POST)
    public String closeProject(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long projectId,
	    @ModelAttribute("closeOutForm") @Valid ProjectCloseOutBean bean,
	    BindingResult result) throws GenericException {
	LOGGER.info("Closing project {}", projectId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	projectManagementService.closeProject(projectId);
	model.addAttribute("messageForm", "Closing project was successful");
	model.addAttribute("closeOutForm", bean);
	model.addAttribute(PARAM_PROJECT_ID, projectId);
	model.addAttribute(PARAM_OBJECT_TYPE, ObjectType.PROJECT.toString());
	model.addAttribute(RequestParams.USERNAME.toString(),
		activeUser.getUsername());

	return "/secured/close-out";
    }
}