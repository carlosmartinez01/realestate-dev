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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.maverik.realestate.constants.RealEstateConstants.Actions;
import com.maverik.realestate.constants.RealEstateConstants.RequestParams;
import com.maverik.realestate.exception.DBException;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.service.RoleManagementService;
import com.maverik.realestate.view.bean.ActiveUser;
import com.maverik.realestate.view.bean.RoleBean;

@Controller
@RequestMapping(value = "/role")
@SessionAttributes("propertiesSession")
public class RoleMgmtController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleMgmtController.class);
	
	private static final String VIEW_DETAILS_FORM = "roleView";
	
	private static final String VIEW_DETAILS_URL = "/secured/viewRoleDetails";
	
	@Autowired
	private RoleManagementService roleManagementService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getRoles(Model model, @AuthenticationPrincipal ActiveUser activeUser) throws DBException {
		LOGGER.info("RoleMgmtController - Getting all the roles");
		
		model.addAttribute(RequestParams.USER_FULLNAME.toString(), activeUser.getUserFullName());
		List<RoleBean> roles = roleManagementService.findAllRoles();
		model.addAttribute("lstRoles", roles);
		Collection<? extends GrantedAuthority> authorities = activeUser.getAuthorities();
		boolean authorized = authorities.contains(new SimpleGrantedAuthority("ROLE_ADMINISTRATOR"));
		if(authorized) {
			model.addAttribute("authorized", authorized);
		}
		
		return "/secured/roleMgmt";
	}
	
	@RequestMapping(value = "/{roleId}/update", method = RequestMethod.GET)
	public String getRoleDetails(Model model, @AuthenticationPrincipal ActiveUser activeUser, @PathVariable Long roleId) throws DBException {
		LOGGER.info("RoleMgmtController - Getting {} role details", roleId);
		
		model.addAttribute(RequestParams.USER_FULLNAME.toString(), activeUser.getUserFullName());
		RoleBean roleBean = roleManagementService.findByRole(roleId);
		model.addAttribute(VIEW_DETAILS_FORM, roleBean);
		model.addAttribute(RequestParams.ACTION.toString(), Actions.UPDATE.toString());
		model.addAttribute("roleOID", roleId);
		
		return VIEW_DETAILS_URL;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addRole(Model model, @AuthenticationPrincipal ActiveUser activeUser) {
		LOGGER.info("RoleMgmtController - Add role");
		
		model.addAttribute(RequestParams.USER_FULLNAME.toString(), activeUser.getUserFullName());
		model.addAttribute(VIEW_DETAILS_FORM, new RoleBean());
		model.addAttribute(RequestParams.ACTION.toString(), Actions.ADD.toString());
		
		return VIEW_DETAILS_URL;
	}
	
	@RequestMapping(value = "/{action}/addOrUpdate", method = RequestMethod.POST)
	public String editOrAddRole(Model model, @AuthenticationPrincipal ActiveUser activeUser, @PathVariable String action, @RequestParam(required=true) Long roleId,
			@ModelAttribute(VIEW_DETAILS_FORM) @Valid RoleBean role, BindingResult result) throws GenericException {
		LOGGER.info("RoleMgmtController - {} role to be updated / role to be added", role);
		
		model.addAttribute(RequestParams.USER_FULLNAME.toString(), activeUser.getUserFullName());
		if(action == null) {
			throw new GenericException("Unable to process the request - Please contact support team", "Unable to process the request", "N/A");
		}
		if(action.equalsIgnoreCase(Actions.UPDATE.toString())) {
			role.setId(roleId);
			model.addAttribute(RequestParams.ACTION.toString(), Actions.UPDATE.toString());
		} else {
			model.addAttribute(RequestParams.ACTION.toString(), Actions.ADD.toString());
		}
		model.addAttribute(VIEW_DETAILS_FORM, role);
		String message = "";
		
		if (result.hasErrors()) {
			return VIEW_DETAILS_URL;
		} else {
			if(action.equalsIgnoreCase(Actions.ADD.toString())) {
				roleManagementService.insertRole(role);
				message = "New Role added successful";
			} else if(action.equalsIgnoreCase(Actions.UPDATE.toString())) {
				message = "Update Role has been successful";
				roleManagementService.updateRole(role);
			}
			model.addAttribute("messageForm", message);
		}		
		
		return VIEW_DETAILS_URL;
	}
	
	@RequestMapping(value = "/{roleId}/delete", method = RequestMethod.GET)
	public String deleteRole(Model model, @AuthenticationPrincipal ActiveUser activeUser, @PathVariable Long roleId,
			final RedirectAttributes redirectAttributes) throws DBException {
		LOGGER.info("RoleMgmtController - Delete role id {}", roleId);
		
		model.addAttribute(RequestParams.USER_FULLNAME.toString(), activeUser.getUserFullName());
		String message = "";
		RoleBean roleBean = roleManagementService.findByRole(roleId);
		roleManagementService.deleteRole(roleBean);
		message = "Role has been deleted successful";
		redirectAttributes.addFlashAttribute("messageForm", message);
		
		return "redirect:/role";
	}
}
