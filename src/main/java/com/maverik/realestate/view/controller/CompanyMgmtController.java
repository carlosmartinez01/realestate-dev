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
import com.maverik.realestate.constants.RealEstateConstants.Roles;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.service.CompanyManagementService;
import com.maverik.realestate.service.RoleManagementService;
import com.maverik.realestate.service.UserManagementService;
import com.maverik.realestate.view.bean.ActiveUser;
import com.maverik.realestate.view.bean.CompanyBean;
import com.maverik.realestate.view.bean.RoleBean;
import com.maverik.realestate.view.bean.UserBean;

@SonarClassExclusion
@Controller
@RequestMapping(value = "/company")
public class CompanyMgmtController {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(CompanyMgmtController.class);

    private static final String VIEW_DETAILS_FORM = "companyView";

    private static final String VIEW_DETAILS_URL = "/secured/viewCompanyDetails";

    @Autowired
    private CompanyManagementService companyManagementService;

    @Autowired
    private RoleManagementService roleManagementService;

    @Autowired
    private UserManagementService userManagementService;

    @RequestMapping(method = RequestMethod.GET)
    public String getCompanies(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser)
	    throws GenericException {
	LOGGER.info("CompanyMgmtController - Getting all the companies");

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	List<CompanyBean> companies = companyManagementService
		.findAllCompanies();
	model.addAttribute("lstCompany", companies);
	Collection<? extends GrantedAuthority> authorities = activeUser
		.getAuthorities();
	boolean authorized = authorities.contains(new SimpleGrantedAuthority(
		"ROLE_ADMINISTRATOR"));
	if (authorized) {
	    model.addAttribute("authorized", authorized);
	}

	return "/secured/companyMgmt";
    }

    @RequestMapping(value = "/{companyId}/update", method = RequestMethod.GET)
    public String getCompanyDetails(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long companyId) throws GenericException {
	LOGGER.info("CompanyMgmtController - Getting {} company details",
		companyId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	CompanyBean companyBean = companyManagementService
		.findByCompany(companyId);
	model.addAttribute(VIEW_DETAILS_FORM, companyBean);
	model.addAttribute(RequestParams.ACTION.toString(),
		Actions.UPDATE.toString());
	model.addAttribute("companyOID", companyId);
	model.addAttribute("active", companyBean.getActive());
	model.addAttribute("objectType", ObjectType.COMPANY.toString());

	return VIEW_DETAILS_URL;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCompany(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser)
	    throws GenericException {
	LOGGER.info("CompanyMgmtController - Add company");

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	model.addAttribute(VIEW_DETAILS_FORM, new CompanyBean());
	model.addAttribute(RequestParams.ACTION.toString(),
		Actions.ADD.toString());

	return VIEW_DETAILS_URL;
    }

    @RequestMapping(value = "/{companyId}/userToCompany", method = RequestMethod.GET)
    public String addUsertToCompany(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long companyId) throws GenericException {
	LOGGER.info("CompanyMgmtController - Add user for Company {}",
		companyId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	CompanyBean companyBean = companyManagementService
		.findByCompany(companyId);
	List<RoleBean> lstRoles = roleManagementService
		.findAllRolesNotDefault(Roles.USER.toString());
	UserBean newUser = new UserBean();
	newUser.setCompany(companyBean);
	model.addAttribute("userProfileView", newUser);
	model.addAttribute(RequestParams.ACTION.toString(),
		Actions.ADD.toString());
	model.addAttribute("pageAction", Actions.PAGE_ADD.toString());
	model.addAttribute("lstRoles", lstRoles);

	return "/secured/userProfileMgmt";
    }

    // @RequestParam(required=true) Boolean active
    @RequestMapping(value = "/{action}/addOrUpdate", method = RequestMethod.POST)
    public String editOrAddCompany(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable String action,
	    @RequestParam(required = true) Long companyId,
	    @ModelAttribute(VIEW_DETAILS_FORM) @Valid CompanyBean company,
	    BindingResult result) throws GenericException {
	LOGGER.info(
		"CompanyMgmtController - {} Company to be updated / Company to be added",
		company);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	if (action == null) {
	    throw new GenericException(
		    "Unable to process the request - Please contact support team",
		    "Unable to process the request", "N/A");
	}
	if (action.equalsIgnoreCase(Actions.UPDATE.toString())) {
	    company.setId(companyId);
	    model.addAttribute(RequestParams.ACTION.toString(),
		    Actions.UPDATE.toString());
	} else {
	    model.addAttribute(RequestParams.ACTION.toString(),
		    Actions.ADD.toString());
	}
	model.addAttribute(VIEW_DETAILS_FORM, company);
	model.addAttribute("companyOID", company.getId());
	model.addAttribute("objectType", ObjectType.COMPANY.toString());
	String message = "";
	if (result.hasErrors()) {
	    if (!action.equalsIgnoreCase(Actions.UPDATE.toString())) {
		model.addAttribute(RequestParams.ACTION.toString(),
			Actions.ADD.toString());
	    }
	    return "/secured/viewCompanyDetails";
	} else {
	    if (action.equalsIgnoreCase(Actions.ADD.toString())) {
		model.addAttribute(RequestParams.ACTION.toString(),
			Actions.ADD.toString());
		companyManagementService.insertCompany(company);
		message = "New Company added successful";
	    } else if (action.equalsIgnoreCase(Actions.UPDATE.toString())) {
		message = "Update Company has been successful";
		companyManagementService.updateCompany(company);
	    }
	    model.addAttribute("messageForm", message);
	}

	return VIEW_DETAILS_URL;
    }

    @RequestMapping(value = "/{companyId}/delete", method = RequestMethod.GET)
    public String deleteCompany(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long companyId,
	    final RedirectAttributes redirectAttributes)
	    throws GenericException {
	LOGGER.info("CompanyMgmtController - Delete company id {}", companyId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	String message = "";
	companyManagementService.softDeleteCompany(companyId);
	message = "Company has been deleted successful";
	redirectAttributes.addFlashAttribute("messageForm", message);

	return "redirect:/company";
    }
}
