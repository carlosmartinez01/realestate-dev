/**
 * 
 */
package com.maverik.realestate.view.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.maverik.realestate.service.NoteManagementService;
import com.maverik.realestate.service.PageManagementService;
import com.maverik.realestate.service.UserManagementService;
import com.maverik.realestate.view.bean.NoteBean;
import com.maverik.realestate.view.bean.PageBean;
import com.maverik.realestate.view.bean.UserBean;

/**
 * @author jorge
 *
 */
@Controller
@RequestMapping(value = "/note")
public class NoteController {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(NoteController.class);

    private static final String NOTE_ADD_ERROR_MSG = "Unable to find the page where to add the note";

    @Autowired
    private NoteManagementService noteManagementService;

    @Autowired
    private UserManagementService userManagementService;

    @Autowired
    private PageManagementService pageManagementService;

    @RequestMapping(value = "/property/{propertyId}/{pageName}/showNotes", method = RequestMethod.GET)
    @ResponseBody
    public List<NoteBean> showPropertyNotes(@PathVariable Long propertyId,
	    @PathVariable String pageName) throws GenericException {
	LOGGER.info("NoteController - Getting notes for property id {}",
		propertyId);

	PageBean page = pageManagementService.findByPageName(pageName);
	List<NoteBean> notes = noteManagementService.findNotesByProperty(
		propertyId, page.getId());
	Collections.sort(notes);

	return notes;
    }

    @RequestMapping(value = "/{project}/{projectId}/showNotes", method = RequestMethod.GET)
    @ResponseBody
    public List<NoteBean> showProjectNotes(@PathVariable Long projectId)
	    throws GenericException {
	LOGGER.info("NoteController - Getting notes for project id {}",
		projectId);

	List<NoteBean> notes = noteManagementService.findNotesByProject(
		projectId, 1L); // change to page id variable
	Collections.sort(notes);

	return notes;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String loadNoteBox(Model model,
	    @RequestParam(value = "objectId") Long objectId,
	    @RequestParam(value = "objectType") String objectType,
	    @RequestParam(value = "pageId") String pageName,
	    @RequestParam(value = "userId") String userId)
	    throws GenericException {
	LOGGER.info("NoteController - Loading the Note Box");

	model.addAttribute("noteView", new NoteBean());
	model.addAttribute("oID", objectId);
	model.addAttribute("oType", objectType);
	model.addAttribute("pageId", pageName);
	model.addAttribute(RequestParams.USERNAME.toString(), userId);

	return "/secured/noteBox";
    }

    @RequestMapping(value = "/property/{propertyId}/{pageName}/{userId}/addNote", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse addNoteToProperty(@PathVariable Long propertyId,
	    @PathVariable String pageName, @PathVariable String userId,
	    @RequestParam(value = "notesText") String notes) {
	LOGGER.info("NoteController - Add a note to property id {}", propertyId);

	UserBean user;
	GenericResponse response = new GenericResponse();
	try {
	    PageBean page = pageManagementService.findByPageName(pageName);
	    user = userManagementService.getUserProfileActive(userId, (byte) 0);
	    noteManagementService.insertNoteByProperty(propertyId, notes, user,
		    page.getId());
	} catch (GenericException e) {
	    response.setErrorMessage(NOTE_ADD_ERROR_MSG);
	    LOGGER.info("" + e);
	}
	response.setSuccessMessage("Note added");

	return response;
    }
}
