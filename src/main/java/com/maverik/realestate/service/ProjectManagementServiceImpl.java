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
import com.maverik.realestate.domain.entity.DailyReport;
import com.maverik.realestate.domain.entity.Filename;
import com.maverik.realestate.domain.entity.PreConstructionType;
import com.maverik.realestate.domain.entity.PreConstructionTypeDetails;
import com.maverik.realestate.domain.entity.Project;
import com.maverik.realestate.domain.entity.ProjectASI;
import com.maverik.realestate.domain.entity.ProjectCloseOut;
import com.maverik.realestate.domain.entity.ProjectManagement;
import com.maverik.realestate.domain.entity.ProjectPreConstruction;
import com.maverik.realestate.domain.entity.ProjectRFI;
import com.maverik.realestate.domain.entity.Property;
import com.maverik.realestate.domain.entity.PropertyPermitting;
import com.maverik.realestate.domain.entity.User;
import com.maverik.realestate.exception.DBException;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.exception.NoRecordFoundException;
import com.maverik.realestate.handler.ExceptionHandler;
import com.maverik.realestate.mapper.ArchitectDrawingMapper;
import com.maverik.realestate.mapper.DailyReportMapper;
import com.maverik.realestate.mapper.FileMapper;
import com.maverik.realestate.mapper.PreConstructionMapper;
import com.maverik.realestate.mapper.ProjectASIMapper;
import com.maverik.realestate.mapper.ProjectCloseOutMapper;
import com.maverik.realestate.mapper.ProjectManagementMapper;
import com.maverik.realestate.mapper.ProjectMapper;
import com.maverik.realestate.mapper.ProjectRFIMapper;
import com.maverik.realestate.repository.DailyReportRepository;
import com.maverik.realestate.repository.FilenameRepository;
import com.maverik.realestate.repository.PreConstructionDetailRepository;
import com.maverik.realestate.repository.PreConstructionRepository;
import com.maverik.realestate.repository.ProjectASIRepository;
import com.maverik.realestate.repository.ProjectCloseOutRepository;
import com.maverik.realestate.repository.ProjectManagementRepository;
import com.maverik.realestate.repository.ProjectRFIRepository;
import com.maverik.realestate.repository.ProjectRepository;
import com.maverik.realestate.repository.PropertyRepository;
import com.maverik.realestate.repository.UserRepository;
import com.maverik.realestate.view.bean.ArchitectDrawingBean;
import com.maverik.realestate.view.bean.DailyReportBean;
import com.maverik.realestate.view.bean.FileBean;
import com.maverik.realestate.view.bean.ProjectASIBean;
import com.maverik.realestate.view.bean.ProjectBean;
import com.maverik.realestate.view.bean.ProjectCloseOutBean;
import com.maverik.realestate.view.bean.ProjectManagementBean;
import com.maverik.realestate.view.bean.ProjectPreConstructionBean;
import com.maverik.realestate.view.bean.ProjectRFIBean;
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

    @Autowired
    private ProjectCloseOutRepository closeOutRepository;

    @Autowired
    private ProjectCloseOutMapper closeOutMapper;

    @Autowired
    private ProjectRFIRepository rfiRepository;

    @Autowired
    private ProjectRFIMapper rfiMapper;

    @Autowired
    private ProjectASIMapper asiMapper;

    @Autowired
    private ProjectASIRepository asiRepository;

    @Autowired
    private DailyReportMapper dailyReportMapper;

    @Autowired
    private DailyReportRepository dailyReportRepository;

    private static final Byte LAND_USE_PERMITTING_STATUS = 0;

    private static final Byte PRE_CONSTRUCTION_PERMITTING_STATUS = 1;

    private static final Byte PROJECT_MANAGEMENT_STATUS = 2;

    private static final Byte CLOSE_OUT_STATUS = 3;

    private static final Byte CLOSE_PROJECT = 4;

    private static final String NO_RECORD_FOUND_ID = "No record found related with id ";

    private static final String GENERIC_ERROR_CODE = "-1";

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
			"No project found for id " + id, GENERIC_ERROR_CODE);
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
			GENERIC_ERROR_CODE);
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
				+ preConstructionDetailId, GENERIC_ERROR_CODE);
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
			GENERIC_ERROR_CODE);
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
				+ bean.getId() + "]", NO_RECORD_FOUND_ID,
			GENERIC_ERROR_CODE);
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.ProjectManagementService#saveManagement
     * (com.maverik.realestate.view.bean.ProjectManagementBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public ProjectManagementBean saveManagement(ProjectManagementBean bean)
	    throws GenericException {
	LOGGER.info("saveManagement({})", bean);

	try {
	    Project project = projectRepository.findOne(bean.getProject());
	    if (project == null) {
		throw new NoRecordFoundException(
			"Unable to find Project object, following data has been submitted => ["
				+ bean.getProject() + "]", NO_RECORD_FOUND_ID
				+ " " + bean.getProject(), GENERIC_ERROR_CODE);
	    }
	    ProjectManagement management = managementMapper.beanToEntity(bean);
	    management.setProject(project);
	    management.setApprovedBudgetFile(project.getManagement()
		    .getApprovedBudgetFile());
	    project.setManagement(null);
	    return managementMapper.entityToBean(managementRepository
		    .save(management));
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
     * com.maverik.realestate.service.ProjectManagementService#moveToCloseOut
     * (com.maverik.realestate.view.bean.ProjectManagementBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public ProjectManagementBean moveToCloseOut(ProjectManagementBean bean)
	    throws GenericException {
	LOGGER.info("moveToCloseOut({})", bean);

	try {
	    Project project = projectRepository.findOne(bean.getProject());
	    if (project == null) {
		throw new NoRecordFoundException(
			"Unable to find Project object, following data has been submitted => ["
				+ bean.getProject() + "]", NO_RECORD_FOUND_ID
				+ " " + bean.getProject(), GENERIC_ERROR_CODE);
	    }
	    project.setStatus(CLOSE_OUT_STATUS);
	    project = projectRepository.save(project);
	    bean.setProject(project.getId());
	    return bean;
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
     * com.maverik.realestate.service.ProjectManagementService#getProjectCloseOut
     * (java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public ProjectCloseOutBean getProjectCloseOut(Long projectId)
	    throws GenericException {
	LOGGER.info("getProjectCloseOut({})", projectId);

	try {
	    Project project = new Project();
	    project.setId(projectId);
	    ProjectCloseOut closeOut = closeOutRepository
		    .findByProject(project);
	    return closeOutMapper.entityToBean(closeOut);
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
     * com.maverik.realestate.service.ProjectManagementService#saveCloseOut(
     * com.maverik.realestate.view.bean.ProjectCloseOutBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public ProjectCloseOutBean saveCloseOut(ProjectCloseOutBean bean)
	    throws GenericException {
	LOGGER.info("saveCloseOut({})", bean);

	try {
	    Project project = projectRepository.findOne(bean.getProject());
	    if (project == null) {
		throw new NoRecordFoundException(
			"Unable to find Project object, following data has been submitted => ["
				+ bean.getProject() + "]", NO_RECORD_FOUND_ID
				+ " " + bean.getProject(), GENERIC_ERROR_CODE);
	    }
	    ProjectCloseOut closeOut = closeOutMapper.beanToEntity(bean);
	    closeOut.setProject(project);
	    ProjectCloseOut entity = project.getCloseOut();
	    closeOut.setRedlinesFile(entity.getRedlinesFile());
	    closeOut.setGeneralContractorFile(entity.getGeneralContractorFile());
	    closeOut.setPunchListItemsFile(entity.getPunchListItemsFile());
	    project.setCloseOut(null);
	    entity = null;
	    return closeOutMapper.entityToBean(closeOutRepository
		    .save(closeOut));
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
     * com.maverik.realestate.service.ProjectManagementService#closeProject(
     * com.maverik.realestate.view.bean.ProjectCloseOutBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public ProjectBean closeProject(Long projectId) throws GenericException {
	LOGGER.info("closeProject({})", projectId);

	try {
	    Project project = projectRepository.findOne(projectId);
	    if (project == null) {
		throw new NoRecordFoundException(
			"Unable to find Project object, following data has been submitted => ["
				+ projectId + "]", NO_RECORD_FOUND_ID + " "
				+ projectId, GENERIC_ERROR_CODE);
	    }
	    project.setStatus(CLOSE_PROJECT);
	    project = projectRepository.save(project);
	    return projectMapper.projectToProjectBean(project);
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
     * com.maverik.realestate.service.PropertyManagementService#findRFIByProperty
     * (java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProjectRFIBean> findRFIByProperty(Long propertyId)
	    throws GenericException {
	LOGGER.info("findRFIByProperty({})", propertyId);

	try {
	    Property property = propertyRepository.findOne(propertyId);
	    if (property == null) {
		throw new NoRecordFoundException(
			"Unable to find Property object, following data has been submitted => ["
				+ propertyId + "]", NO_RECORD_FOUND_ID + " "
				+ propertyId, GENERIC_ERROR_CODE);
	    }
	    List<ProjectRFIBean> rfis = new ArrayList<ProjectRFIBean>();
	    property.getProjects().forEach(
		    project -> project.getProjectRFIs().forEach(
			    rfi -> rfis.add(rfiMapper.entityToBean(rfi))));
	    List<String> names = new ArrayList<String>();
	    property.getCompanies().forEach(
		    company -> company.getUsers().forEach(
			    user -> names.add(user.getFirstName() + " "
				    + user.getLastName())));
	    rfis.forEach(rfi -> rfi.setPeople(names));
	    return rfis;
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
     * com.maverik.realestate.service.PropertyManagementService#saveRFI(com.
     * maverik.realestate.view.bean.ProjectRFIBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public ProjectRFIBean saveRFI(ProjectRFIBean bean) throws GenericException {
	LOGGER.info("saveRFI({})", bean);

	try {
	    Project project = projectRepository.findOne(bean.getProject());
	    if (project == null) {
		throw new NoRecordFoundException(
			"Unable to find Property object, following data has been submitted => ["
				+ bean.getProject() + "]", NO_RECORD_FOUND_ID
				+ " " + bean.getProject(), GENERIC_ERROR_CODE);
	    }
	    ProjectRFI rfi = rfiMapper.beanToEntity(bean);
	    rfi.setProject(project);
	    FileBean rfiFile = bean.getRfiFile();
	    if (rfiFile != null) {
		Filename file = fileRepository.findOne(rfiFile.getId());
		rfi.setRfiFile(file);
	    }
	    return rfiMapper.entityToBean(rfiRepository.save(rfi));
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.PropertyManagementService#
     * getProjectsByProperty(com.maverik.realestate.view.bean.PropertyBean)
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProjectBean> getProjectsByProperty(Long propertyId)
	    throws GenericException {
	LOGGER.info("getProjectsByProperty({})", propertyId);

	try {
	    Property property = propertyRepository.findOne(propertyId);
	    if (property == null) {
		throw new NoRecordFoundException(
			"Unable to find Property object, following data has been submitted => ["
				+ propertyId + "]", NO_RECORD_FOUND_ID + " "
				+ propertyId, GENERIC_ERROR_CODE);
	    }
	    List<ProjectBean> projects = new ArrayList<ProjectBean>();
	    property.getProjects().forEach(
		    project -> projects.add(projectMapper
			    .projectToProjectBean(project)));
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
     * @see
     * com.maverik.realestate.service.PropertyManagementService#findASIByProperty
     * (java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProjectASIBean> findASIByProperty(Long propertyId)
	    throws GenericException {
	LOGGER.info("findASIByProperty({})", propertyId);

	try {
	    Property property = propertyRepository.findOne(propertyId);
	    if (property == null) {
		throw new NoRecordFoundException(
			"Unable to find Property object, following data has been submitted => ["
				+ propertyId + "]", NO_RECORD_FOUND_ID + " "
				+ propertyId, GENERIC_ERROR_CODE);
	    }
	    List<ProjectASIBean> asis = new ArrayList<ProjectASIBean>();
	    property.getProjects().forEach(
		    project -> project.getProjectASIs().forEach(
			    asi -> asis.add(asiMapper.entityToBean(asi))));
	    List<String> names = new ArrayList<String>();
	    property.getCompanies().forEach(
		    company -> company.getUsers().forEach(
			    user -> names.add(user.getFirstName() + " "
				    + user.getLastName())));
	    asis.forEach(asi -> asi.setPeople(names));
	    return asis;
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
     * com.maverik.realestate.service.PropertyManagementService#saveASI(com.
     * maverik.realestate.view.bean.ProjectASIBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public ProjectASIBean saveASI(ProjectASIBean bean) throws GenericException {
	LOGGER.info("saveASI({})", bean);

	try {
	    Project project = projectRepository.findOne(bean.getProject());
	    if (project == null) {
		throw new NoRecordFoundException(
			"Unable to find Property object, following data has been submitted => ["
				+ bean.getProject() + "]", NO_RECORD_FOUND_ID
				+ " " + bean.getProject(), GENERIC_ERROR_CODE);
	    }
	    ProjectASI asi = asiMapper.beanToEntity(bean);
	    asi.setProject(project);
	    FileBean asiFile = bean.getAsiFile();
	    if (asiFile != null) {
		Filename file = fileRepository.findOne(asiFile.getId());
		asi.setAsiFile(file);
	    }
	    return asiMapper.entityToBean(asiRepository.save(asi));
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
     * com.maverik.realestate.service.PropertyManagementService#addRFIFile(com
     * .maverik.realestate.view.bean.FileBean, java.lang.Long)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public FileBean addRFIFile(FileBean fileBean, Long rfiId, Long projectId)
	    throws GenericException {
	LOGGER.info("addRFIFile({},{})", fileBean, rfiId);

	Filename file = null;
	FileBean bean = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    file = fileRepository.save(file);
	    ProjectRFI rfi = null;
	    if (rfiId != null) {
		rfi = rfiRepository.findOne(rfiId);
	    } else {
		rfi = new ProjectRFI();
		rfi.setProject(projectRepository.findOne(projectId));
	    }
	    rfi.setRfiFile(file);
	    rfiRepository.save(rfi);
	    bean = fileMapper.fileToFileBean(file);
	    bean.setEntityRelatedId(rfiId);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return bean;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.PropertyManagementService#addASIFile(com
     * .maverik.realestate.view.bean.FileBean, java.lang.Long)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public FileBean addASIFile(FileBean fileBean, Long asiId, Long projectId)
	    throws GenericException {
	LOGGER.info("addASIFile({},{})", fileBean, asiId);

	Filename file = null;
	FileBean bean = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    file = fileRepository.save(file);
	    ProjectASI asi = null;
	    if (asiId != null) {
		asi = asiRepository.findOne(asiId);
	    } else {
		asi = new ProjectASI();
		asi.setProject(projectRepository.findOne(projectId));
	    }
	    asi.setAsiFile(file);
	    asiRepository.save(asi);
	    bean = fileMapper.fileToFileBean(file);
	    bean.setEntityRelatedId(asiId);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return bean;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.ProjectManagementService#
     * addConstructionBudgetFile(com.maverik.realestate.view.bean.FileBean,
     * java.lang.Long, java.lang.Long)
     */
    @Override
    public FileBean addConstructionBudgetFile(FileBean fileBean,
	    Long managementId, Long projectId) throws GenericException {
	LOGGER.info("addConstructionBudgetFile({},{})", fileBean, managementId);

	Filename file = null;
	FileBean bean = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    file = fileRepository.save(file);
	    ProjectManagement management = null;
	    if (managementId != null) {
		management = managementRepository.findOne(managementId);
	    } else {
		management = new ProjectManagement();
		management.setProject(projectRepository.findOne(projectId));
	    }
	    management.setApprovedBudgetFile(file);
	    managementRepository.save(management);
	    bean = fileMapper.fileToFileBean(file);
	    bean.setEntityRelatedId(managementId);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return bean;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.ProjectManagementService#addRedlinesFile
     * (com.maverik.realestate.view.bean.FileBean, java.lang.Long,
     * java.lang.Long)
     */
    @Override
    public FileBean addRedlinesFile(FileBean fileBean, Long closeOutId,
	    Long projectId) throws GenericException {
	LOGGER.info("addRedlinesFile({},{})", fileBean, closeOutId);

	Filename file = null;
	FileBean bean = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    file = fileRepository.save(file);
	    ProjectCloseOut closeOut = null;
	    if (closeOutId != null) {
		closeOut = closeOutRepository.findOne(closeOutId);
	    } else {
		closeOut = new ProjectCloseOut();
		closeOut.setProject(projectRepository.findOne(projectId));
	    }
	    closeOut.setRedlinesFile(file);
	    closeOutRepository.save(closeOut);
	    bean = fileMapper.fileToFileBean(file);
	    bean.setEntityRelatedId(closeOutId);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return bean;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.ProjectManagementService#
     * addGeneralContractorFile(com.maverik.realestate.view.bean.FileBean,
     * java.lang.Long, java.lang.Long)
     */
    @Override
    public FileBean addGeneralContractorFile(FileBean fileBean,
	    Long closeOutId, Long projectId) throws GenericException {
	LOGGER.info("addGeneralContractorFile({},{})", fileBean, closeOutId);

	Filename file = null;
	FileBean bean = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    file = fileRepository.save(file);
	    ProjectCloseOut closeOut = null;
	    if (closeOutId != null) {
		closeOut = closeOutRepository.findOne(closeOutId);
	    } else {
		closeOut = new ProjectCloseOut();
		closeOut.setProject(projectRepository.findOne(projectId));
	    }
	    closeOut.setGeneralContractorFile(file);
	    closeOutRepository.save(closeOut);
	    bean = fileMapper.fileToFileBean(file);
	    bean.setEntityRelatedId(closeOutId);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return bean;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.ProjectManagementService#addPunchListItemsFile
     * (com.maverik.realestate.view.bean.FileBean, java.lang.Long,
     * java.lang.Long)
     */
    @Override
    public FileBean addPunchListItemsFile(FileBean fileBean, Long closeOutId,
	    Long projectId) throws GenericException {
	LOGGER.info("addPunchListItemsFile({},{})", fileBean, closeOutId);

	Filename file = null;
	FileBean bean = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    file = fileRepository.save(file);
	    ProjectCloseOut closeOut = null;
	    if (closeOutId != null) {
		closeOut = closeOutRepository.findOne(closeOutId);
	    } else {
		closeOut = new ProjectCloseOut();
		closeOut.setProject(projectRepository.findOne(projectId));
	    }
	    closeOut.setPunchListItemsFile(file);
	    closeOutRepository.save(closeOut);
	    bean = fileMapper.fileToFileBean(file);
	    bean.setEntityRelatedId(closeOutId);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return bean;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.PropertyManagementService#saveDailyReport
     * (com.maverik.realestate.view.bean.DailyReportBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public DailyReportBean saveDailyReport(DailyReportBean bean)
	    throws GenericException {
	LOGGER.info("saveDailyReport({})", bean);

	try {
	    DailyReport dailyReport = dailyReportMapper.beanToEntity(bean);
	    Project project = projectRepository.findOne(bean.getProject());
	    if (project == null) {
		throw new NoRecordFoundException(
			"Unable to find Project object, following data has been submitted => ["
				+ bean.getProject() + "]", NO_RECORD_FOUND_ID
				+ " " + bean.getProject(), GENERIC_ERROR_CODE);
	    }
	    dailyReport.setProject(project);
	    return dailyReportMapper.entityToBean(dailyReportRepository
		    .save(dailyReport));
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
    }
}