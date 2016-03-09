package com.maverik.realestate.view.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.maverik.realestate.exception.DBException;
import com.maverik.realestate.service.UserManagementService;

@Controller
public class ResetPasswordController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResetPasswordController.class);
	
	@Autowired
	private UserManagementService updatePasswordService;

	@RequestMapping(value = "/resetUserPassword", method = RequestMethod.POST)
	public String resetPassword(@RequestParam(value = "username", required = false) String username, Model model) throws DBException {
		LOGGER.info("ResetPasswordController - Reset password");
		
		String message = null;
		updatePasswordService.resetPasswordForUser(username);
		message = "Password reset successfull - Your new password was sent through email";
		model.addAttribute("resetPassMsg", message);
		model.addAttribute("render", false);
		
		return "resetPassword";
	}
	
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public String main(Model model) {
		LOGGER.info("ResetPasswordController - Render reset password page");
		
		model.addAttribute("render", true);
		
		return "resetPassword";
	}
	
}
