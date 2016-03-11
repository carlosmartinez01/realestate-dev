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

import com.maverik.realestate.constants.RealEstateConstants.ConstructionDocumentTypes;
import com.maverik.realestate.constants.RealEstateConstants.ProjectPhases;
import com.maverik.realestate.domain.entity.ArchitectDrawing;
import com.maverik.realestate.domain.entity.Filename;
import com.maverik.realestate.domain.entity.PreConstructionType;
import com.maverik.realestate.domain.entity.PreConstructionTypeDetails;
import com.maverik.realestate.domain.entity.Project;
import com.maverik.realestate.domain.entity.ProjectManagement;
import com.maverik.realestate.domain.entity.ProjectPreConstruction;
import com.maverik.realestate.domain.entity.Property;
import com.maverik.realestate.domain.entity.PropertyPermitting;
import com.maverik.realestate.domain.entity.User;
import com.maverik.realestate.exception.DBException;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.exception.NoRecordFoundException;
import com.maverik.realestate.handler.ExceptionHandler;
import com.maverik.realestate.mapper.ArchitectDrawingMapper;
import com.maverik.realestate.mapper.FileMapper;
import com.maverik.realestate.mapper.PreConstructionMapper;
import com.maverik.realestate.mapper.ProjectManagementMapper;
import com.maverik.realestate.mapper.ProjectMapper;
import com.maverik.realestate.repository.FilenameRepository;
import com.maverik.realestate.repository.PreConstructionDetailRepository;
import com.maverik.realestate.repository.PreConstructionRepository;
import com.maverik.realestate.repository.ProjectManagementRepository;
import com.maverik.realestate.repository.ProjectRepository;
import com.maverik.realestate.repository.PropertyRepository;
import com.maverik.realestate.repository.UserRepository;
import com.maverik.realestate.view.bean.ArchitectDrawingBean;
import com.maverik.realestate.view.bean.FileBean;
import com.maverik.realestate.view.bean.ProjectBean;
import com.maverik.realestate.view.bean.ProjectManagementBean;
import com.maverik.realestate.view.bean.ProjectPreConstructionBean;
import com.maverik.realestate.view.bean.PropertyBean;
import com.maverik.realestate.view.bean.PropertyContractViewBean;

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

    @Autowired
    private PreConstructionRepository preconstructionRepository;

    @Autowired
    private PreConstructionMapper preConstructionMapper;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private FilenameRepository fileRepository;

    @Autowired
    private PreConstructionDetailRepository preConstructionDetailsRepository;

    @Autowired
    private PropertyManagementService propertyManagementService;

    @Autowired
    private ArchitectDrawingMapper architectMapper;

    @Autowired
    private ProjectManagementRepository managementRepository;

    @Autowired
    private ProjectManagementMapper managementMapper;

    private static final Byte LAND_USE_PERMITTING_STATUS = 0;

    private static final Byte PRE_CONSTRUCTION_PERMITTING_STATUS = 1;

    private static final Byte PROJECT_MANAGEMENT_STATUS = 2;

    private static final Byte CLOSE_OUT_STATUS = 3;

    private static final String NO_RECORD_FOUND_ID = "No record found related with id ";

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
	    entity.setStatus(setProjectStatusBySelectedPhase(project
		    .getProjectPhase()));
	    entity = projectRepository.save(entity);
	    projectBean = projectMapper.projectToProjectBean(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return projectBean;
    }

    private Byte setProjectStatusBySelectedPhase(String phase) {
	if (phase != null) {
	    if (phase
		    .equalsIgnoreCase(ProjectPhases.PRE_CONSTRUCTION_PERMITTING
			    .toString())) {
		return PRE_CONSTRUCTION_PERMITTING_STATUS;
	    } else if (phase.equalsIgnoreCase(ProjectPhases.PROJECT_MANAGEMENT
		    .toString())) {
		return PROJECT_MANAGEMENT_STATUS;
	    } else if (phase.equalsIgnoreCase(ProjectPhases.CLOSE_OUT
		    .toString())) {
		return CLOSE_OUT_STATUS;
	    }
	}

	return LAND_USE_PERMITTING_STATUS;
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
		    .findByStatus((byte) 0);
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
    public ProjectPreConstructionBean getPreConstruction(Long projectId)
	    throws GenericException {
	LOGGER.info("getPreConstruction({})", projectId);

	try {
	    Project project = new Project();
	    project.setId(projectId);
	    ProjectPreConstruction preConstruction = preconstructionRepository
		    .findByProject(project);
	    if (preConstruction == null) {
		throw new NoRecordFoundException(
			"No record found por project id ["
				+ projectId
				+ "], unable to continue and process the request",
			"No Preconstruction record found, unable to process your request",
			"-1");
	    }
	    return preConstructionMapper.entityToBean(preConstruction);
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
				    .getProjectPhase(), null, null)));
	    return projects;
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.ProjectManagementService#
     * createNextProjectPhases(com.maverik.realestate.view.bean.PropertyBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public void createNextProjectPhases(PropertyBean property,
	    ProjectBean project) throws GenericException {
	LOGGER.info("createNextProjectPhases({})", property);
	Byte status = project.getStatus();
	try {
	    Property propertyEntity = propertyRepository.findOne(property
		    .getId());
	    Project projectEntity = projectRepository.findOne(project.getId());
	    if (status == LAND_USE_PERMITTING_STATUS) {
		PropertyPermitting permitting = propertyEntity.getPermitting();
		if (permitting == null) {
		    insertLandPermitting(property);
		    insertPreConstruction(projectEntity);
		}
	    } else if (status == PRE_CONSTRUCTION_PERMITTING_STATUS
		    && projectEntity.getPreConstruction() == null) {
		insertPreConstruction(projectEntity);
	    }
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
    }

    private void insertLandPermitting(PropertyBean property)
	    throws GenericException {
	LOGGER.info("insertLandPermitting({})", property);
	PropertyContractViewBean contract = new PropertyContractViewBean();
	contract.setProperty(property);
	propertyManagementService
		.createLandPermittingAndUpdateProperty(contract);
    }

    private void insertPreConstruction(Project projectEntity) {
	LOGGER.info("insertPreConstruction({})", projectEntity);
	ProjectPreConstruction preConstruction = new ProjectPreConstruction();
	preConstruction.setProject(projectEntity);
	for (ConstructionDocumentTypes type : ConstructionDocumentTypes
		.values()) {
	    PreConstructionType documentType = new PreConstructionType();
	    documentType.setConstructionDocumentType(type.toString());
	    documentType.setPreConstruction(preConstruction);
	    preConstruction.addTypes(documentType);
	}
	projectEntity.setPreConstruction(preConstruction);
	projectRepository.save(projectEntity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.ProjectManagementService#savePreConstruction
     * (com.maverik.realestate.view.bean.PreConstructionViewBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public ProjectPreConstructionBean savePreConstruction(
	    ProjectPreConstructionBean bean) throws GenericException {
	LOGGER.info("savePreConstruction({})", bean);

	try {
	    ProjectPreConstruction preConstruction = preConstructionMapper
		    .beanToEntity(bean);
	    Project projectId = projectRepository.findOne(bean.getProject());
	    preConstruction.setPermitFilename(projectId.getPreConstruction()
		    .getPermitFilename());
	    preConstruction.setProject(projectId);
	    preConstruction
		    .getDetails()
		    .forEach(
			    types -> {
				types.setPreConstruction(preConstruction);
				if (types.getTypeDetails() != null) {
				    types.getTypeDetails()
					    .forEach(
						    detail -> {
							detail.setPreConstructionTypeId(types);
							if (detail
								.getFilename() != null
								&& detail
									.getFilename()
									.getId() != null) {
							    detail.setFilename(fileRepository
								    .findOne(detail
									    .getFilename()
									    .getId()));
							} else {
							    detail.setFilename(null);
							}
						    });
				}
			    });
	    projectId.setPreConstruction(null);
	    return preConstructionMapper.entityToBean(preconstructionRepository
		    .save(preConstruction));
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.ProjectManagementService#
     * addPreConstructionDetailFile(com.maverik.realestate.view.bean.FileBean,
     * java.lang.Long)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public FileBean addPreConstructionDetailFile(FileBean fileBean,
	    Long preConstructionDetailId) throws GenericException {
	LOGGER.info("addPreConstructionDetailFile({}{})", fileBean,
		preConstructionDetailId);

	Filename file = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    file = fileRepository.save(file);
	    PreConstructionTypeDetails entity = preConstructionDetailsRepository
		    .findOne(preConstructionDetailId);
	    if (entity == null) {
		throw new NoRecordFoundException(
			"Unable to save file and preconstruction detail records, following data has been submitted => ["
				+ preConstructionDetailId + "," + file + "]",
			"No record found related with id "
				+ preConstructionDetailId, "-1");
	    }
	    entity.setFilename(file);
	    preConstructionDetailsRepository.save(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return fileMapper.fileToFileBean(file);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.ProjectManagementService#
     * addPreConstructionPermitFile(com.maverik.realestate.view.bean.FileBean,
     * java.lang.Long)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public FileBean addPreConstructionPermitFile(FileBean fileBean,
	    Long preConstructionId) throws GenericException {
	LOGGER.info("addPreConstructionPermitFile({}{})", fileBean,
		preConstructionId);

	Filename file = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    file = fileRepository.save(file);
	    ProjectPreConstruction entity = preconstructionRepository
		    .findOne(preConstructionId);
	    if (entity == null) {
		throw new NoRecordFoundException(
			"Unable to save file and preconstruction detail records, following data has been submitted => ["
				+ preConstructionId + "," + file + "]",
			"No record found related with id " + preConstructionId,
			"-1");
	    }
	    entity.setPermitFilename(file);
	    preconstructionRepository.save(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return fileMapper.fileToFileBean(file);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.ProjectManagementService#getArchitectDrawing
     * (java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public List<ArchitectDrawingBean> getArchitectDrawing(Long preConstructionId)
	    throws GenericException {
	LOGGER.info("getArchitectDrawing({})", preConstructionId);

	try {
	    ProjectPreConstruction preConstruction = preconstructionRepository
		    .findOne(preConstructionId);
	    List<ArchitectDrawingBean> architectDrawings = architectMapper
		    .entitiesToBeans(preConstruction.getDrawings());
	    return architectDrawings;
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
     * com.maverik.realestate.service.ProjectManagementService#saveDrawingDetails
     * (com.maverik.realestate.view.bean.ArchitectDrawingBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public List<ArchitectDrawingBean> saveDrawingDetails(
	    ProjectPreConstructionBean bean) throws GenericException {
	LOGGER.info("saveDrawingDetails({})", bean);

	try {
	    ProjectPreConstruction preConstruction = preconstructionRepository
		    .findOne(bean.getId());
	    if (preConstruction == null) {
		throw new NoRecordFoundException(
			"Unable to find preConstruction object, following data has been submitted => ["
				+ bean.getId() + "]", NO_RECORD_FOUND_ID, "-1");
	    }
	    List<ArchitectDrawing> drawings = architectMapper
		    .beansToEntities(bean.getDrawings());
	    drawings.forEach(drawing -> drawing
		    .setPreConstruction(preConstruction));
	    drawings.stream()
		    .filter(drawing -> drawing.getDrawingDetails() != null)
		    .forEach(
			    drawing -> drawing.getDrawingDetails().forEach(
				    details -> details
					    .setArchitectDrawing(drawing)));
	    preConstruction.setDrawings(drawings);
	    preconstructionRepository.save(preConstruction);
	    return architectMapper.entitiesToBeans(preConstruction
		    .getDrawings());
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
     * com.maverik.realestate.service.ProjectManagementService#getProjectManagement
     * (java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public ProjectManagementBean getProjectManagement(Long projectId)
	    throws GenericException {
	LOGGER.info("getProjectManagement({})", projectId);

	try {
	    Project project = new Project();
	    project.setId(projectId);
	    ProjectManagement management = managementRepository
		    .findByProject(project);
	    return managementMapper.entityToBean(management);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
    }
}
