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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.constants.RealEstateConstants.RequestParams;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.response.GenericResponse;
import com.maverik.realestate.service.PropertyManagementService;
import com.maverik.realestate.view.bean.ActiveUser;
import com.maverik.realestate.view.bean.LeaseAndExtensionBean;

/**
 * @author jorge
 *
 */
@Controller
@RequestMapping(value = "/propertyLease")
@SonarClassExclusion
public class PropertyLeaseController {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(PropertyLeaseController.class);

    @Autowired
    private PropertyManagementService propertyManagementService;

    @RequestMapping(value = "/showLease/{propertyId}", method = RequestMethod.POST)
    public String showLease(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long propertyId,
	    @RequestParam(value = "lease_id", required = false) Long leaseId)
	    throws GenericException {
	LOGGER.info("Getting accepted and non-accepted Lease for property id {}");

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	model.addAttribute("leaseAcceptedLst", propertyManagementService
		.findAcceptedLease(propertyId, leaseId));
	model.addAttribute("leaseNonAcceptedLst", propertyManagementService
		.findNonAcceptedLease(propertyId, leaseId));

	return "/secured/leaseView";
    }

    @RequestMapping(value = "/save/lease/{propertyId}", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse saveLOI(@PathVariable Long propertyId,
	    @RequestBody LeaseAndExtensionBean leaseAndExtension) {
	LOGGER.info("Saving lease id {} for property id {}", leaseAndExtension
		.getLease().getId(), propertyId);

	GenericResponse response = new GenericResponse();
	try {
	    propertyManagementService.saveLease(leaseAndExtension.getLease(),
		    leaseAndExtension.getExtension());
	    response.setSuccessMessage("Lease was saved successfully");
	} catch (GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage("Unable to save the lease");
	}

	return response;
    }

    @RequestMapping(value = "/accept/lease/{propertyId}", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse acceptLOI(@PathVariable Long propertyId,
	    @RequestBody LeaseAndExtensionBean leaseAndExtension) {
	LOGGER.info("Accepting Lease for property LOI {}", propertyId);

	GenericResponse response = new GenericResponse();
	try {
	    propertyManagementService.acceptLease(leaseAndExtension.getLease(),
		    leaseAndExtension.getExtension());
	    response.setSuccessMessage("Lease was accepted successfully");
	} catch (GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage("Unable to accept this lease");
	}

	return response;
    }
}
