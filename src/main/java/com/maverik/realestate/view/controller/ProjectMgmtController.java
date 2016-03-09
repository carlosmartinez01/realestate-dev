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

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.constants.RealEstateConstants.Actions;
import com.maverik.realestate.constants.RealEstateConstants.ObjectType;
import com.maverik.realestate.constants.RealEstateConstants.RequestParams;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.service.ProjectManagementService;
import com.maverik.realestate.view.bean.ActiveUser;
import com.maverik.realestate.view.bean.ProjectBean;

@SonarClassExclusion
@Controller
@RequestMapping(value = "/project")
public class ProjectMgmtController {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(ProjectMgmtController.class);

    private static final String VIEW_DETAILS_FORM = "projectView";

    private static final String VIEW_DETAILS_URL = "/secured/viewProjectDetails";

    @Autowired
    private ProjectManagementService projectManagementService;

    @RequestMapping(method = RequestMethod.GET)
    public String getProjects(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser)
	    throws GenericException {
	LOGGER.info("ProjectMgmtController - Getting all the projects");

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	List<ProjectBean> projects = projectManagementService.findAllProjects();
	model.addAttribute("lstProject", projects);
	Collection<? extends GrantedAuthority> authorities = activeUser
		.getAuthorities();
	boolean authorized = authorities.contains(new SimpleGrantedAuthority(
		"ROLE_ADMINISTRATOR"));
	if (authorized) {
	    model.addAttribute("authorized", authorized);
	}

	return "/secured/projectMgmt";
    }

    @RequestMapping(value = "/{projectId}/update", method = RequestMethod.GET)
    public String getProjectDetails(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long projectId) throws GenericException {
	LOGGER.info("ProjectMgmtController - Getting {} project details",
		projectId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	ProjectBean projectBean = projectManagementService
		.findByProject(projectId);
	model.addAttribute(VIEW_DETAILS_FORM, projectBean);
	model.addAttribute(RequestParams.ACTION.toString(),
		Actions.UPDATE.toString());
	model.addAttribute("projectOID", projectId);
	model.addAttribute("objectType", ObjectType.PROJECT.toString());

	return VIEW_DETAILS_URL;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addProject(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser)
	    throws GenericException {
	LOGGER.info("ProjectMgmtController - Add project");

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	model.addAttribute(VIEW_DETAILS_FORM, new ProjectBean());
	model.addAttribute(RequestParams.ACTION.toString(),
		Actions.ADD.toString());

	return VIEW_DETAILS_URL;
    }

    @RequestMapping(value = "/{action}/addOrUpdate", method = RequestMethod.POST)
    public String editORAddProject(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable String action,
	    @RequestParam(required = true) Long projectId,
	    @ModelAttribute("projectView") @Valid ProjectBean project,
	    BindingResult result) throws GenericException {
	LOGGER.info(
		"ProjectMgmtController - {} Project to be updated / Project to be added",
		project);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	if (action == null) {
	    throw new GenericException(
		    "Unable to process the request - Please contact support team",
		    "Unable to process the request", "N/A");
	}
	if (action.equalsIgnoreCase(Actions.UPDATE.toString())) {
	    project.setId(projectId);
	    model.addAttribute(RequestParams.ACTION.toString(),
		    Actions.UPDATE.toString());
	} else {
	    model.addAttribute(RequestParams.ACTION.toString(),
		    Actions.ADD.toString());
	}
	model.addAttribute(VIEW_DETAILS_FORM, project);
	model.addAttribute("projectOID", projectId);
	model.addAttribute("objectType", ObjectType.PROJECT.toString());
	String message = "";
	if (result.hasErrors()) {
	    if (!action.equalsIgnoreCase(Actions.UPDATE.toString())) {
		model.addAttribute(RequestParams.ACTION.toString(),
			Actions.ADD.toString());
	    }
	    return "/secured/viewProjectDetails";
	} else {
	    if (action.equalsIgnoreCase(Actions.ADD.toString())) {
		model.addAttribute(RequestParams.ACTION.toString(),
			Actions.ADD.toString());
		projectManagementService.insertProject(project);
		message = "New Project added successful";
	    } else if (action.equalsIgnoreCase(Actions.UPDATE.toString())) {
		message = "Update Project has been successful";
		projectManagementService.updateProject(project);
	    }
	    model.addAttribute("messageForm", message);
	}

	return VIEW_DETAILS_URL;
    }

    @RequestMapping(value = "/{projectId}/delete", method = RequestMethod.GET)
    public String deleteProject(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long projectId,
	    final RedirectAttributes redirectAttributes)
	    throws GenericException {
	LOGGER.info("ProjectMgmtController - Delete project id {}", projectId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	String message = "";
	ProjectBean project = projectManagementService.findByProject(projectId);
	projectManagementService.deleteProject(project);
	message = "Project has been deleted successful";
	redirectAttributes.addFlashAttribute("messageForm", message);

	return "redirect:/project";
    }
}
