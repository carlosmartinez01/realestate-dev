package com.maverik.realestate.view.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.view.bean.ActiveUser;

/**
 * Handles requests for the application home page, login and logout page
 */
@SonarClassExclusion
@Controller
@RequestMapping(value = "/")
public class HomeController {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(HomeController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String home(HttpSession session, Model model,
	    @AuthenticationPrincipal ActiveUser activeUser) {
	LOGGER.info("HomeController - User logged is {}. ",
		activeUser.getUsername());
	session.setAttribute("userFullName", activeUser.getUserFullName());

	return "/secured/home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(
	    @RequestParam(value = "error", required = false) String error,
	    Model model) {
	LOGGER.info("HomeController - Login Page {}.");

	if (error != null) {
	    model.addAttribute("error", "Invalid username and password!");
	}

	return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(SessionStatus status) {
	LOGGER.info("HomeController - Log out {}.");

	status.setComplete();

	return "login";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser) {
	LOGGER.info("HomeController - Access Denied for user {}.",
		activeUser.getUsername());

	model.addAttribute("userFullName", activeUser.getUserFullName());

	return "/secured/accessDenied";
    }
}
