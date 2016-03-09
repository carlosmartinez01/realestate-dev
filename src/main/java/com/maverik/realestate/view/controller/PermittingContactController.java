/**
 * 
 */
package com.maverik.realestate.view.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.response.GenericResponse;
import com.maverik.realestate.service.PropertyManagementService;
import com.maverik.realestate.view.bean.PermittingContactBean;

/**
 * @author jorge
 *
 */
@Controller
@RequestMapping(value = "/contact")
public class PermittingContactController {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(PermittingTaskController.class);

    @Autowired
    private PropertyManagementService propertyManagementService;

    @RequestMapping(value = "/{propertyId}/permitting/get/contacts", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("isFullyAuthenticated()")
    public List<PermittingContactBean> getContacts(@PathVariable Long propertyId)
	    throws GenericException {
	LOGGER.info(
		"Getting all permitting contacts related to Property id {}",
		propertyId);

	return propertyManagementService.getAllContactsByProperty(propertyId);
    }

    @RequestMapping(value = "/{propertyId}/permitting/add", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse addContact(@PathVariable Long propertyId,
	    @RequestBody @Valid PermittingContactBean contact,
	    BindingResult result) {
	LOGGER.info("Adding permitting contacts related to Property id {}",
		propertyId);

	GenericResponse response = new GenericResponse();
	try {
	    if (result.hasErrors()) {
		response.setErrorMessage("Title or Name are Empty");
		return response;
	    }
	    contact.setPropertyId(propertyId);
	    propertyManagementService.saveContact(contact);
	    response.setSuccessMessage("Contact successfully added");
	} catch (GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage("Unable to save the contact");
	}

	return response;
    }
}
