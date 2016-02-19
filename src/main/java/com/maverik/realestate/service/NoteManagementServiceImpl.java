package com.maverik.realestate.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.Note;
import com.maverik.realestate.domain.entity.Page;
import com.maverik.realestate.domain.entity.Project;
import com.maverik.realestate.domain.entity.ProjectNotes;
import com.maverik.realestate.domain.entity.Property;
import com.maverik.realestate.domain.entity.PropertyNotes;
import com.maverik.realestate.domain.entity.User;
import com.maverik.realestate.exception.DBException;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.handler.ExceptionHandler;
import com.maverik.realestate.mapper.CompanyMapper;
import com.maverik.realestate.mapper.NoteMapper;
import com.maverik.realestate.mapper.PropertyMapper;
import com.maverik.realestate.mapper.UserMapper;
import com.maverik.realestate.repository.NoteRepository;
import com.maverik.realestate.repository.ProjectNotesRepository;
import com.maverik.realestate.repository.PropertyNotesRepository;
import com.maverik.realestate.utils.DateFormatter;
import com.maverik.realestate.view.bean.NoteBean;
import com.maverik.realestate.view.bean.UserBean;

@SonarClassExclusion
@Service
public class NoteManagementServiceImpl implements NoteManagementService {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(NoteManagementServiceImpl.class);

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private PropertyNotesRepository propertyNotesRepository;

    @Autowired
    private ProjectNotesRepository projectNotesRepository;

    @Autowired
    private PropertyMapper propertyMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ExceptionHandler exceptionHandler;

    private PropertyNotes setPropertyRef(Long id) {
	Property propertyEntity = new Property();
	propertyEntity.setId(id);
	PropertyNotes propertNotes = new PropertyNotes();
	propertNotes.setPropertyId(propertyEntity);

	return propertNotes;
    }

    private ProjectNotes setProjectRef(Long id) {
	Project projectEntity = new Project();
	projectEntity.setId(id);
	ProjectNotes projectNotes = new ProjectNotes();
	projectNotes.setProjectId(projectEntity);

	return projectNotes;
    }

    private List<PropertyNotes> findPropertyRef(Property property, Page page)
	    throws GenericException {
	try {
	    return propertyNotesRepository.findByPropertyIdAndPageId(property,
		    page);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
    }

    private List<ProjectNotes> findProjectRef(Project project, Page page)
	    throws GenericException {
	try {
	    return projectNotesRepository.findByProjectIdAndPageId(project,
		    page);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.NoteManagementService#findNoteByProperty
     * (java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public List<NoteBean> findNotesByProperty(Long propertyId, Long pageId)
	    throws GenericException {
	if (propertyId == null) {
	    return new ArrayList<NoteBean>();
	}
	List<PropertyNotes> entities = null;
	List<NoteBean> notes = null;
	Page page = new Page();
	page.setId(pageId);
	Property property = new Property();
	property.setId(propertyId);
	entities = findPropertyRef(property, page);
	notes = new ArrayList<NoteBean>();
	for (PropertyNotes ref : entities) {
	    NoteBean note = new NoteBean();
	    note.setNoteId(ref.getNoteId().getNoteId());
	    note.setNotes(ref.getNoteId().getNotes());
	    note.setUser(ref.getUserId().getFirstName() + " "
		    + ref.getUserId().getLastName());
	    note.setFormattedDate(DateFormatter.formatDate(ref
		    .getCreationTime()));
	    notes.add(note);
	}

	return notes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.NoteManagementService#findNoteByProject
     * (java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public List<NoteBean> findNotesByProject(Long projectId, Long pageId)
	    throws GenericException {
	if (projectId == null) {
	    return new ArrayList<NoteBean>();
	}
	List<ProjectNotes> entities = null;
	List<NoteBean> notes = null;
	Page page = new Page();
	page.setId(pageId);
	Project project = new Project();
	project.setId(projectId);
	entities = findProjectRef(project, page);
	notes = new ArrayList<NoteBean>();
	for (ProjectNotes ref : entities) {
	    NoteBean note = new NoteBean();
	    note.setNoteId(ref.getNoteId().getNoteId());
	    note.setNotes(ref.getNoteId().getNotes());
	    note.setUser(ref.getUserId().getFirstName() + " "
		    + ref.getUserId().getLastName());
	    note.setFormattedDate(DateFormatter.formatDate(ref
		    .getCreationTime()));
	    notes.add(note);
	}

	return notes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.NoteManagementService#insertNoteByProperty
     * (java.lang.Long, java.lang.String,
     * com.maverik.realestate.view.bean.UserBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = DBException.class)
    public NoteBean insertNoteByProperty(Long propertyId, String noteText,
	    UserBean user, Long pageId) throws DBException {
	LOGGER.info("Inserting Note for Property {}", propertyId);

	if (propertyId == null || user == null) {
	    return null;
	}
	NoteBean note = null;
	PropertyNotes propertyNotes = null;
	Page page = new Page();
	page.setId(pageId);
	propertyNotes = setPropertyRef(propertyId);
	propertyNotes.setPageId(page);
	User u = userMapper.userBeanToUser(user);
	propertyNotes.setUserId(u);
	Note entity = new Note();
	entity.setNotes(noteText);
	entity.setPropertyNotes(propertyNotes);
	propertyNotes.setNoteId(entity);
	try {
	    entity = noteRepository.save(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
	note = noteMapper.noteToNoteBean(entity);

	return note;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.NoteManagementService#insertNoteByProject
     * (java.lang.Long, java.lang.String,
     * com.maverik.realestate.view.bean.UserBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = DBException.class)
    public NoteBean insertNoteByProject(Long projectId, String noteText,
	    UserBean user, Long pageId) throws DBException {
	LOGGER.info("Inserting Note for Project {}", projectId);

	if (projectId == null || user == null || pageId == null) {
	    return null;
	}
	NoteBean note = null;
	ProjectNotes projectNotes = null;
	Page page = new Page();
	page.setId(pageId);
	projectNotes = setProjectRef(projectId);
	projectNotes.setPageId(page);
	User u = userMapper.userBeanToUser(user);
	projectNotes.setUserId(u);
	Note entity = new Note();
	entity.setNotes(noteText);
	entity.setProjectNotes(projectNotes);
	projectNotes.setNoteId(entity);
	try {
	    entity = noteRepository.save(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
	note = noteMapper.noteToNoteBean(entity);

	return note;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.NoteManagementService#deleteNote(com.maverik
     * .realestate.view.bean.NoteBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = DBException.class)
    public void deleteNote(NoteBean note) throws GenericException {
	LOGGER.info("Deleting Note {}", note);

	try {
	    noteRepository.delete(note.getNoteId());
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
    }
}
