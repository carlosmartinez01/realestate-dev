package com.maverik.realestate.view.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.maverik.realestate.constants.RealEstateConstants.Actions;
import com.maverik.realestate.constants.RealEstateConstants.RequestParams;
import com.maverik.realestate.constants.RealEstateConstants.Roles;
import com.maverik.realestate.constants.RealEstateConstants.UserInfo;
import com.maverik.realestate.exception.DBException;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.service.RoleManagementService;
import com.maverik.realestate.service.UserManagementService;
import com.maverik.realestate.view.bean.ActiveUser;
import com.maverik.realestate.view.bean.RoleBean;
import com.maverik.realestate.view.bean.UserBean;

/**
 * @author jorge
 *
 */
@Controller
@RequestMapping(value = "/profile")
@SessionAttributes("userDetailsSession")
public class UserProfileMgmtController {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(UserProfileMgmtController.class);

    private static final String VIEW_DETAILS_FORM = "userProfileView";

    private static final String VIEW_PROFILE_DETAILS_URL = "/secured/userProfileMgmt";

    private static final String VIEW_DETAILS_URL = "/secured/viewProfileDetails";

    private static final String LIST_USERS = "lstUsers";

    private static final String LIST_ROLES = "lstRoles";

    /**
     * UserManagementService userProfileService
     */
    @Autowired
    private UserManagementService userProfileService;

    /**
     * RoleManagementService roleManagementService
     */
    @Autowired
    private RoleManagementService roleManagementService;

    /**
     * @param model
     * @param principal
     * @return
     * @throws GenericException
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getCurrentUserProfile(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser)
	    throws GenericException {
	LOGGER.info("UserProfileMgmtController - Get User profile for {}",
		activeUser.getUsername());

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	UserBean userProfile = null;
	userProfile = userProfileService.getActiveUserProfile(
		activeUser.getUsername());
	model.addAttribute(VIEW_DETAILS_FORM, userProfile);
	Map<String, Object> userDetails = new HashMap<String, Object>();
	userDetails.put(RequestParams.PASSWORD.toString(),
		userProfile.getPassword());
	userDetails.put(RequestParams.ACTIVE.toString(),
		userProfile.getActive());
	model.addAttribute("userDetailsSession", userDetails);

	return VIEW_PROFILE_DETAILS_URL;
    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public String updateUserProfile(
	    Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @ModelAttribute(VIEW_DETAILS_FORM) @Valid UserBean userProfile,
	    BindingResult result,
	    @ModelAttribute("userDetailsSession") Map<String, Object> userDetails,
	    @RequestParam(required = true) Long userId) throws GenericException {
	LOGGER.info("UserProfileMgmtController - Updating user {} details",
		userProfile.getUsername());

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	if (userDetails == null || userDetails.isEmpty()) {
	    throw new GenericException(
		    "Unable to process the request - Please contact support team",
		    "Unable to process the request", "500");
	}

	userProfile.setActive((Boolean) userDetails.get("active"));
	userProfile.setPassword((String) userDetails.get("password"));
	userProfile.setId(userId);
	model.addAttribute(VIEW_DETAILS_FORM, userProfile);

	if (result.hasErrors()) {

	    return VIEW_PROFILE_DETAILS_URL;
	} else {
	    userProfileService.updateUserProfile(userProfile,
		    (String) userDetails.get("password"));
	    String message = "Update has been successful";
	    model.addAttribute("updateMessage", message);
	    userDetails.put(RequestParams.PASSWORD.toString(),
		    userProfile.getPassword());
	    userDetails.put(RequestParams.ACTIVE.toString(),
		    userProfile.getActive());
	    model.addAttribute("userDetailsSession", userDetails);

	    return VIEW_PROFILE_DETAILS_URL;
	}
    }

    @RequestMapping(value = "/addUserToCompany", method = RequestMethod.POST)
    public String addUser(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @ModelAttribute(VIEW_DETAILS_FORM) @Valid UserBean userProfile,
	    BindingResult result,
	    @RequestParam(required = true) Long companyId,
	    @RequestParam(required = false) String role) throws DBException {
	LOGGER.info("UserProfileMgmtController - Adding user {} to Company {}",
		userProfile.getUsername(), companyId);
	LOGGER.info("User chose role {}", role);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	userProfile.setActive(Boolean.valueOf(UserInfo.BOOL_ACTIVE.toString()));
	userProfile.getCompany().setId(companyId);
	model.addAttribute(VIEW_DETAILS_FORM, userProfile);
	List<RoleBean> lstRoles = roleManagementService
		.findAllRolesNotDefault(Roles.USER.toString());
	model.addAttribute(LIST_ROLES, lstRoles);
	model.addAttribute(RequestParams.ACTION.toString(),
		Actions.ADD.toString());
	model.addAttribute("pageAction", Actions.PAGE_ADD.toString());
	if (result.hasErrors()) {
	    return VIEW_PROFILE_DETAILS_URL;
	} else {
	    userProfileService.addUserToCompany(userProfile, role);
	    String message = "New user added successfully";
	    model.addAttribute("updateMessage", message);
	}

	return VIEW_PROFILE_DETAILS_URL;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllUserProfile(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser) throws DBException {
	LOGGER.info("UserProfileMgmtController - Get all users");

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	List<UserBean> users = userProfileService.getAllUsers();
	model.addAttribute(LIST_USERS, users);
	Collection<? extends GrantedAuthority> authorities = activeUser
		.getAuthorities();
	boolean authorized = authorities.contains(new SimpleGrantedAuthority(
		"ROLE_ADMINISTRATOR"));
	if (authorized) {
	    model.addAttribute("authorized", authorized);
	}

	return "/secured/usersProfile";
    }

    @RequestMapping(value = "/{userId}/{username}/delete", method = RequestMethod.GET)
    public String deleteUser(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long userId, @PathVariable String username,
	    final RedirectAttributes redirectAttributes) throws DBException {

	LOGGER.info("UserProfileMgmtController - Delete user id {}", userId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	String message = "";
	if (activeUser.getUsername().equalsIgnoreCase(username)) {
	    message = "You can't delete yourself";
	    redirectAttributes.addFlashAttribute("errorMessage", message);

	    return "redirect:/profile/all";
	}
	userProfileService.deleteUserWithCompany(userId);
	message = "User has been deleted successful";
	redirectAttributes.addFlashAttribute("messageForm", message);

	return "redirect:/profile/all";
    }

    @RequestMapping(value = "/{userId}/details", method = RequestMethod.GET)
    public String getUserInfoDetails(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long userId) throws DBException {
	LOGGER.info("UserProfileMgmtController - Getting details for {}",
		userId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	UserBean user = userProfileService.getUserProfile(userId);
	model.addAttribute("userView", user);
	model.addAttribute(RequestParams.ACTION.toString(),
		Actions.UPDATE.toString());
	model.addAttribute("userOID", userId);
	model.addAttribute(RequestParams.ACTIVE.toString(), user.getActive());
	List<RoleBean> lstRoles = roleManagementService.findAllRoles();
	model.addAttribute("roleSelected",
		roleManagementService.getUserRoleSelected(user.getRoles()));
	model.addAttribute(LIST_ROLES, lstRoles);

	return VIEW_DETAILS_URL;
    }

    @RequestMapping(value = "/{action}/addOrUpdate", method = RequestMethod.POST)
    public String editOrAddUserProfile(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable String action,
	    @ModelAttribute("userView") @Valid UserBean user,
	    BindingResult result, @RequestParam(required = false) String role)
	    throws GenericException {
	LOGGER.info("UserProfileMgmtController - {} User to be updated", user);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	if (action == null) {
	    throw new GenericException(
		    "Unable to process the request - Please contact support team",
		    "Unable to process the request", "N/A");
	}
	if (action.equalsIgnoreCase(Actions.UPDATE.toString())) {
	    model.addAttribute("action", Actions.UPDATE.toString());
	}
	List<RoleBean> lstRoles = roleManagementService.findAllRoles();
	model.addAttribute(LIST_ROLES, lstRoles);
	model.addAttribute("roleSelected", role);
	model.addAttribute("userView", user);
	Set<String> roles = new HashSet<String>();
	roles.add(role);
	user.setRoles(roles);
	String message = "";
	if (result.hasErrors()) {
	    return VIEW_DETAILS_URL;
	} else {
	    if (action.equalsIgnoreCase(Actions.UPDATE.toString())) {
		message = "Update User Profile has been successful";
		userProfileService.deleteUserRolesWhenUpdateProfile(user);
		userProfileService.updateUserProfile(user, null);
	    }
	    model.addAttribute("messageForm", message);
	}

	return VIEW_DETAILS_URL;
    }

    @RequestMapping(value = "/{userId}/changePassword", method = RequestMethod.POST)
    @ResponseBody
    public String changePassword(
	    Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @RequestParam(value = "r_password", required = true) String password,
	    @PathVariable Long userId) {
	LOGGER.info("UserProfileMgmtController - Change password for user {}",
		activeUser.getUsername());

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	try {
	    userProfileService.changeUserPassword(userId, password);
	} catch (GenericException e) {
	    LOGGER.info("" + e);
	    return "Failed to udpate";
	}

	return "Update successful";
    }
}
