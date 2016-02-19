package com.maverik.realestate.view.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.maverik.realestate.constants.RealEstateConstants.RequestParams;
import com.maverik.realestate.view.bean.ActiveUser;

@Controller
@RequestMapping(value = "/admin")
public class AdministrationController {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(AdministrationController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String getProjects(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser) {
	LOGGER.info("AdministrationController - Administration page");

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	String msg = "This page is for project Administration";
	model.addAttribute("adminProjectMsg", msg);

	return "/secured/administration";
    }

}
