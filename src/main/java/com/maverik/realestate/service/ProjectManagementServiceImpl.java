package com.maverik.realestate.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.maverik.realestate.domain.entity.Project;
import com.maverik.realestate.domain.entity.Property;
import com.maverik.realestate.domain.entity.User;
import com.maverik.realestate.exception.DBException;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.exception.NoRecordFoundException;
import com.maverik.realestate.handler.ExceptionHandler;
import com.maverik.realestate.mapper.ProjectMapper;
import com.maverik.realestate.repository.ProjectRepository;
import com.maverik.realestate.repository.PropertyRepository;
import com.maverik.realestate.repository.UserRepository;
import com.maverik.realestate.view.bean.ProjectBean;

@Service
public class ProjectManagementServiceImpl implements ProjectManagementService {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(ProjectManagementServiceImpl.class);

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private NoteManagementService noteManagementService;

    @Autowired
    private ExceptionHandler exceptionHandler;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public ProjectBean insertProject(ProjectBean project)
	    throws GenericException {
	LOGGER.info("insertProject({})", project);

	ProjectBean projectBean = null;
	try {
	    Property property = propertyRepository.findOne(project
		    .getProperty().getId());
	    if (property == null) {
		return null;
	    }
	    Project entity = projectMapper.projectBeanToProject(project);
	    entity.setProperty(property);
	    entity = projectRepository.save(entity);
	    projectBean = projectMapper.projectToProjectBean(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return projectBean;
    }

    @Override
    public ProjectBean updateProject(ProjectBean project)
	    throws GenericException {
	LOGGER.info("updateProject()");
	return insertProject(project);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public void deleteProject(ProjectBean project) throws DBException {
	try {
	    Project entity = projectMapper.projectBeanToProject(project);
	    projectRepository.delete(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectBean findByProject(Long id) throws GenericException {
	ProjectBean project = null;
	try {
	    Project entity = projectRepository.findOne(id);
	    if (entity == null) {
		throw new NoRecordFoundException("No project found",
			"No project found for id " + id, "-1");
	    }
	    project = projectMapper.projectToProjectBean(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return project;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectBean> findAllProjects() throws DBException {
	List<ProjectBean> listProjects = null;
	try {
	    List<Project> listEntities = projectRepository.findAll();
	    listProjects = projectMapper
		    .listOfProjectToListOfProjectBeans(listEntities);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return listProjects;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.ProjectManagementService#
     * findProjectsByResearchPhase()
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProjectBean> findProjectsByResearchPhase() throws DBException {
	List<ProjectBean> listProjects = null;
	try {
	    List<Project> listEntities = projectRepository
		    .findByStatus((byte) 1);
	    listProjects = projectMapper
		    .listOfProjectToListOfProjectBeans(listEntities);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return listProjects;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.ProjectManagementService#findByProjectName
     * (java.lang.String)
     */
    @Override
    @Transactional(readOnly = true)
    public ProjectBean findByProjectName(String projectName)
	    throws GenericException {
	ProjectBean project = null;
	try {
	    Project entity = projectRepository.findByProjectName(projectName);
	    project = projectMapper.projectToProjectBean(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return project;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.ProjectManagementService#
     * addUsersAssignedToProject(com.maverik.realestate.view.bean.ProjectBean,
     * java.util.List)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public ProjectBean addUsersAssignedToProject(Long projectId,
	    List<Long> users) throws GenericException {
	LOGGER.info("Insert users " + users + " to Project " + projectId);

	ProjectBean projectBean = null;
	try {
	    Project entity = projectRepository.findOne(projectId);
	    List<User> lstUsers = new ArrayList<User>();
	    for (Long userId : users) {
		lstUsers.add(userRepository.findOne(userId));
	    }
	    Set<User> setUsers = new HashSet<User>(lstUsers);
	    entity.setUsers(setUsers);
	    entity = projectRepository.save(entity);
	    projectBean = projectMapper.projectToProjectBean(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return projectBean;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.ProjectManagementService#
     * checkPreconstructionAvailable()
     */
    @Override
    @Transactional(readOnly = true)
    public Boolean checkPreconstructionAvailability(Long projectId)
	    throws GenericException {
	LOGGER.info("checkPreconstructionAvailable({})", projectId);

	try {
	    Project project = projectRepository.findOne(projectId);
	    if (project == null || project.getStatus() > 0) {
		return Boolean.FALSE;
	    }

	    return Boolean.TRUE;
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
     * com.maverik.realestate.service.ProjectManagementService#getProjectsByProperty
     * (java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProjectBean> getProjectsByProperty(Long propertyId)
	    throws GenericException {
	LOGGER.info("getProjectsByProperty({})", propertyId);

	try {
	    Property property = propertyRepository.findOne(propertyId);
	    List<ProjectBean> projects = new ArrayList<ProjectBean>();
	    property.getProjects().forEach(
		    project -> projects.add(new ProjectBean(project.getId(),
			    project.getProjectName(), null, null, project
				    .getProjectType(), null, null)));
	    return projects;
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
    }
}
