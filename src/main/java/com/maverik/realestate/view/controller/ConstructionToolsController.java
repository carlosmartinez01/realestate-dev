/**
 * 
 */
package com.maverik.realestate.view.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.maverik.realestate.constants.RealEstateConstants.RequestParams;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.service.PropertyManagementService;
import com.maverik.realestate.view.bean.ActiveUser;

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
    private PropertyManagementService propertyService;

    @RequestMapping(value = "/{propertyId}/rfi", method = RequestMethod.POST)
    public String getAllRFI(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long propertyId) throws GenericException {
	LOGGER.info("Get all RFI assigned to property {}", propertyId);

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	// ProjectCloseOutBean bean = projectManagementService
	// .getProjectCloseOut(projectId);
	model.addAttribute("rfiForm", null);

	return "/secured/construction-rfi";
    }
}
