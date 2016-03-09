package com.maverik.realestate.service;

import java.util.List;

import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.view.bean.NoteBean;
import com.maverik.realestate.view.bean.UserBean;

public interface NoteManagementService {

    public List<NoteBean> findNotesByProperty(Long propertyId, Long pageId)
	    throws GenericException;

    public List<NoteBean> findNotesByProject(Long projectId, Long pageId)
	    throws GenericException;

    public NoteBean insertNoteByProperty(Long propertyId, String noteText,
	    UserBean user, Long pageId) throws GenericException;

    public NoteBean insertNoteByProject(Long projectId, String noteText,
	    UserBean user, Long pageId) throws GenericException;

    public void deleteNote(NoteBean note) throws GenericException;
}
