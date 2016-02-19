/**
 * 
 */
package com.maverik.realestate.view.controller;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maverik.realestate.constants.RealEstateConstants.RequestParams;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.response.GenericResponse;
import com.maverik.realestate.service.PropertyManagementService;
import com.maverik.realestate.view.bean.ActiveUser;
import com.maverik.realestate.view.bean.PropertyLOIBean;

/**
 * @author jorge
 *
 */
@Controller
@RequestMapping(value = "/propertyLOI")
public class PropertyLOIController {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(PropertyLOIController.class);

    @Autowired
    private PropertyManagementService propertyManagementService;

    @RequestMapping(value = "/showLOI/{propertyId}", method = RequestMethod.POST)
    public String showLOI(Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long propertyId) throws GenericException {
	LOGGER.info("Getting accepted and non-accepted LOI for property id {}");

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	model.addAttribute("loiAcceptedLst", propertyManagementService
		.findLOIByAcceptance(propertyId, (byte) 1));
	model.addAttribute("loiNonAcceptedLst", propertyManagementService
		.findLOIByAcceptance(propertyId, (byte) 0));

	return "/secured/loiView";
    }

    @RequestMapping(value = "/showLoi/accepted/{propertyId}", method = RequestMethod.POST)
    @ResponseBody
    public List<PropertyLOIBean> getLOIAccepted(@PathVariable Long propertyId)
	    throws GenericException {
	LOGGER.info("Getting accepted price LOI");

	return propertyManagementService.findLOIByAcceptance(propertyId,
		(byte) 1);
    }

    @RequestMapping(value = "/showLoi/nonaccepted/{propertyId}", method = RequestMethod.POST)
    @ResponseBody
    public List<PropertyLOIBean> getLOINonAccepted(@PathVariable Long propertyId)
	    throws GenericException {
	LOGGER.info("Getting non-accepted price for LOI");

	return propertyManagementService.findLOIByAcceptance(propertyId,
		(byte) 0);
    }

    @RequestMapping(value = "/save/price/{loiId}", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse savePrice(@PathVariable Long loiId,
	    @RequestParam(value = "r_price", required = true) BigDecimal price) {
	LOGGER.info("Saving Price for property LOI {}", loiId);

	GenericResponse response = new GenericResponse();
	try {
	    propertyManagementService.saveLOIPrice(loiId, price);
	    response.setSuccessMessage("Price saved successfully");
	} catch (GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage("Unable to save price");
	}

	return response;
    }

    @RequestMapping(value = "/accept/price/{propertyId}/{loiId}", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse acceptLOI(@PathVariable Long loiId,
	    @RequestParam(value = "r_price", required = true) BigDecimal price,
	    @PathVariable Long propertyId) {
	LOGGER.info("Accepting Price for property LOI {}", loiId);

	GenericResponse response = new GenericResponse();
	try {
	    propertyManagementService.acceptLOIPrice(loiId, price, propertyId);
	    response.setSuccessMessage("Price was accepted successfully");
	} catch (GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage("Unable to accepte this price");
	}

	return response;
    }
}
