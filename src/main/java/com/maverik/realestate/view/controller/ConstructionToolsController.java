/**
 * 
 */
package com.maverik.realestate.view.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.maverik.realestate.constants.RealEstateConstants.RequestParams;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.service.ProjectManagementService;
import com.maverik.realestate.view.bean.ActiveUser;
import com.maverik.realestate.view.bean.ProjectASIBean;
import com.maverik.realestate.view.bean.ProjectBean;
import com.maverik.realestate.view.bean.ProjectRFIBean;

/**
 * @author jorge
 *
 */
@Controller
@RequestMapping(value = "/tools")
public class ConstructionToolsController {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(ConstructionToolsController.class);

    @Autowired
    private ProjectManagementService projectService;

    @RequestMapping(value = "/{propertyId}/rfi", method = RequestMethod.POST)
    public String getAllRFI(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long propertyId) throws GenericException {
	LOGGER.info("Get all RFI assigned to property {}", propertyId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	List<ProjectRFIBean> rfis = projectService
		.findRFIByProperty(propertyId);
	model.addAttribute("rfiLst", rfis);

	return "/secured/construction-rfi";
    }

    @RequestMapping(value = "/rfi/save", method = RequestMethod.POST)
    public String saveRFI(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @ModelAttribute("rfiForm") @Valid ProjectRFIBean bean,
	    BindingResult resutl) throws GenericException {
	LOGGER.info("Save RFI {}", bean);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	bean = projectService.saveRFI(bean);
	model.addAttribute("rfiForm", bean);

	return "/secured/construction-rfi";
    }

    @RequestMapping(value = "/{propertyId}/get-projects", method = RequestMethod.GET)
    @ResponseBody
    public List<ProjectBean> getProjectsRelatedToProperty(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long propertyId) throws GenericException {
	LOGGER.info("Get projects related with property id {}", propertyId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());

	return projectService.getProjectsByProperty(propertyId);
    }

    @RequestMapping(value = "/{propertyId}/asi", method = RequestMethod.POST)
    public String getAllASI(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long propertyId) throws GenericException {
	LOGGER.info("Get all ASI assigned to property {}", propertyId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	List<ProjectASIBean> asis = projectService
		.findASIByProperty(propertyId);
	model.addAttribute("asiLst", asis);

	return "/secured/construction-asi";
    }

    @RequestMapping(value = "/asi/save", method = RequestMethod.POST)
    public String saveASI(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @ModelAttribute("asiForm") @Valid ProjectASIBean bean,
	    BindingResult resutl) throws GenericException {
	LOGGER.info("Save ASI {}", bean);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	bean = projectService.saveASI(bean);
	model.addAttribute("asiForm", bean);

	return "/secured/construction-asi";
    }
}
