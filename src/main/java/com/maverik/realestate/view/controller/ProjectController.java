package com.maverik.realestate.view.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import com.maverik.realestate.view.bean.PreConstructionViewBean;
import com.maverik.realestate.view.bean.ProjectBean;

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
	LOGGER.info("Get preconstruction permitting for project id {}",
		projectId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	if (!projectManagementService
		.checkPreconstructionAvailability(projectId)) {
	    model.addAttribute("preConstructionForm",
		    new PreConstructionViewBean());
	    return "/secured/preConstruction";
	}
	model.addAttribute("messageForm", "User added to Project successfully");

	return "/secured/preConstruction2";
    }

    @RequestMapping(value = "/{projectId}/summary", method = RequestMethod.GET)
    public String getProjectSummary(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long projectId) throws GenericException {
	LOGGER.info("Get summary for project id {}", projectId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	model.addAttribute("projectForm",
		projectManagementService.findByProject(projectId));

	return "/secured/viewProjectSummary";
    }
}
