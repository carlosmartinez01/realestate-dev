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
import com.maverik.realestate.view.bean.PermittingAssignmentTaskBean;
import com.maverik.realestate.view.bean.TaskStatusBean;

/**
 * @author jorge
 *
 */
@Controller
@RequestMapping(value = "/task")
public class PermittingTaskController {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(PermittingTaskController.class);

    @Autowired
    private PropertyManagementService propertyManagementService;

    @RequestMapping(value = "/{propertyId}/permitting/get/tasks", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("isFullyAuthenticated()")
    public List<PermittingAssignmentTaskBean> getAssignmentTasks(
	    @PathVariable Long propertyId) throws GenericException {
	LOGGER.info("Getting all permitting tasks related to Property id {}",
		propertyId);

	return propertyManagementService.getAllTaskByProperty(propertyId);
    }

    @RequestMapping(value = "/{propertyId}/permitting/add", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse addAssignmentTask(@PathVariable Long propertyId,
	    @RequestBody @Valid PermittingAssignmentTaskBean task,
	    BindingResult result) {
	LOGGER.info("Adding permitting task related to Property id {}",
		propertyId);

	GenericResponse response = new GenericResponse();
	try {
	    if (result.hasErrors()) {
		response.setErrorMessage("Assigned To is Empty");
		return response;
	    }
	    task.setPropertyId(propertyId);
	    propertyManagementService.saveAssignmentTask(task);
	    response.setSuccessMessage("Task successfully added");
	} catch (GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage("Unable to save the task");
	}

	return response;
    }

    @RequestMapping(value = "/permitting/update", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse saveTask(@RequestBody TaskStatusBean tasks)
	    throws GenericException {
	LOGGER.info("Marking tasks {}", tasks);

	GenericResponse response = new GenericResponse();
	try {
	    propertyManagementService.markAssignmentTask(tasks);
	    response.setSuccessMessage("Tasks succesfully updated");
	} catch (GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage("Unable to update tasks");
	}

	return response;
    }
}
