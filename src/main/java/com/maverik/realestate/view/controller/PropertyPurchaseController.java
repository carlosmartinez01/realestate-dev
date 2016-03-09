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
import com.maverik.realestate.view.bean.PurchaseAndExtensionWrapBean;

/**
 * @author jorge
 *
 */
@Controller
@RequestMapping(value = "/propertyPurchase")
@SonarClassExclusion
public class PropertyPurchaseController {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(PropertyPurchaseController.class);

    @Autowired
    private PropertyManagementService propertyManagementService;

    @RequestMapping(value = "/showPurchase/{propertyId}", method = RequestMethod.POST)
    public String showPurchase(
	    Model model,
	    @AuthenticationPrincipal ActiveUser activeUser,
	    @PathVariable Long propertyId,
	    @RequestParam(value = "purchase_id", required = false) Long purchaseId)
	    throws GenericException {
	LOGGER.info("Getting accepted and non-accepted Purchases for property id {}");

	model.addAttribute(RequestParams.USER_FULLNAME.toString(),
		activeUser.getUserFullName());
	model.addAttribute("purchaseAcceptedLst", propertyManagementService
		.findAcceptedPurchase(propertyId, purchaseId));
	model.addAttribute("purchaseNonAcceptedLst", propertyManagementService
		.findNonAcceptedPurchase(propertyId, purchaseId));

	return "/secured/purchaseView";
    }

    @RequestMapping(value = "/save/purchase/{propertyId}", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse savePurchase(@PathVariable Long propertyId,
	    @RequestBody PurchaseAndExtensionWrapBean purchaseWrapper) {
	LOGGER.info("Saving purchase id {} for property id {}", purchaseWrapper
		.getPurchase().getId(), propertyId);

	GenericResponse response = new GenericResponse();
	try {
	    propertyManagementService.savePurchase(
		    purchaseWrapper.getPurchase(),
		    purchaseWrapper.getExtensions());
	    response.setSuccessMessage("Property Purchase was saved successfully");
	} catch (GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage("Unable to save the property purchase");
	}

	return response;
    }

    @RequestMapping(value = "/accept/purchase/{propertyId}", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse acceptPurchase(@PathVariable Long propertyId,
	    @RequestBody PurchaseAndExtensionWrapBean purchaseWrapper) {
	LOGGER.info("Accepting Purchase for property id {}", propertyId);

	GenericResponse response = new GenericResponse();
	try {
	    propertyManagementService.acceptPurchase(
		    purchaseWrapper.getPurchase(),
		    purchaseWrapper.getExtensions());
	    response.setSuccessMessage("Purcahse was accepted successfully");
	} catch (GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage("Unable to accept this purchase");
	}

	return response;
    }
}
