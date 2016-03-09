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

import com.maverik.realestate.constants.RealEstateConstants.RequestParams;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.response.GenericResponse;
import com.maverik.realestate.service.ProjectManagementService;
import com.maverik.realestate.service.UserManagementService;
import com.maverik.realestate.view.bean.ActiveUser;
import com.maverik.realestate.view.bean.ArchitectDrawingBean;
import com.maverik.realestate.view.bean.ProjectBean;
import com.maverik.realestate.view.bean.ProjectPreConstructionBean;
import com.maverik.realestate.view.bean.PropertyBean;

@Controller
@RequestMapping(value = "/projects")
public class ProjectController {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(ProjectController.class);

    @Autowired
    private ProjectManagementService projectManagementService;

    @Autowired
    private UserManagementService userManagementService;

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
	model.addAttribute("projectId", projectId);

	return "/secured/preConstruction";
    }

    @RequestMapping(value = "/{projectId}/summary", method = RequestMethod.GET)
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
	// redirectAttributes.addAttribute("messageForm",
	// "Update Successfully");

	// return "redirect:/projects/" + projectId + "/preconstruction";
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
}