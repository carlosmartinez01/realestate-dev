package com.maverik.realestate.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.constants.RealEstateConstants.Meetings;
import com.maverik.realestate.domain.entity.Company;
import com.maverik.realestate.domain.entity.Filename;
import com.maverik.realestate.domain.entity.LeaseExtension;
import com.maverik.realestate.domain.entity.PermittingAssignmentTask;
import com.maverik.realestate.domain.entity.PermittingContact;
import com.maverik.realestate.domain.entity.Project;
import com.maverik.realestate.domain.entity.Property;
import com.maverik.realestate.domain.entity.PropertyContract;
import com.maverik.realestate.domain.entity.PropertyLOI;
import com.maverik.realestate.domain.entity.PropertyLease;
import com.maverik.realestate.domain.entity.PropertyMeeting;
import com.maverik.realestate.domain.entity.PropertyPermitting;
import com.maverik.realestate.domain.entity.PropertyPurchase;
import com.maverik.realestate.domain.entity.PurchaseExtension;
import com.maverik.realestate.exception.DBException;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.handler.ExceptionHandler;
import com.maverik.realestate.mapper.FileMapper;
import com.maverik.realestate.mapper.LeaseExtensionMapper;
import com.maverik.realestate.mapper.MeetingsMapper;
import com.maverik.realestate.mapper.PermittingContactMapper;
import com.maverik.realestate.mapper.PermittingTaskMapper;
import com.maverik.realestate.mapper.ProjectMapper;
import com.maverik.realestate.mapper.PropertyContractMapper;
import com.maverik.realestate.mapper.PropertyLOIMapper;
import com.maverik.realestate.mapper.PropertyLeaseMapper;
import com.maverik.realestate.mapper.PropertyMapper;
import com.maverik.realestate.mapper.PropertyPermittingMapper;
import com.maverik.realestate.mapper.PropertyPurchaseMapper;
import com.maverik.realestate.mapper.PurchaseExtensionMapper;
import com.maverik.realestate.mapper.UserMapper;
import com.maverik.realestate.repository.CompanyRepository;
import com.maverik.realestate.repository.FilenameRepository;
import com.maverik.realestate.repository.LeaseExtensionRepository;
import com.maverik.realestate.repository.MeetingsRepository;
import com.maverik.realestate.repository.PermittingAssignmentTaskRepository;
import com.maverik.realestate.repository.PermittingContactRepository;
import com.maverik.realestate.repository.PropertyContractRepository;
import com.maverik.realestate.repository.PropertyLOIRepository;
import com.maverik.realestate.repository.PropertyLeaseRepository;
import com.maverik.realestate.repository.PropertyPermittingRepository;
import com.maverik.realestate.repository.PropertyPurchaseRepository;
import com.maverik.realestate.repository.PropertyRepository;
import com.maverik.realestate.repository.PurchaseExtensionRepository;
import com.maverik.realestate.view.bean.FileBean;
import com.maverik.realestate.view.bean.LeaseAndExtensionBean;
import com.maverik.realestate.view.bean.LeaseExtensionBean;
import com.maverik.realestate.view.bean.PermittingAssignmentTaskBean;
import com.maverik.realestate.view.bean.PermittingContactBean;
import com.maverik.realestate.view.bean.PermittingMeetingsViewBean;
import com.maverik.realestate.view.bean.ProjectBean;
import com.maverik.realestate.view.bean.PropertyBean;
import com.maverik.realestate.view.bean.PropertyContractBean;
import com.maverik.realestate.view.bean.PropertyContractViewBean;
import com.maverik.realestate.view.bean.PropertyLOIBean;
import com.maverik.realestate.view.bean.PropertyLeaseBean;
import com.maverik.realestate.view.bean.PropertyPermittingBean;
import com.maverik.realestate.view.bean.PropertyPurchaseBean;
import com.maverik.realestate.view.bean.PurchaseAndExtensionWrapBean;
import com.maverik.realestate.view.bean.PurchaseExtensionBean;
import com.maverik.realestate.view.bean.TaskStatusBean;

@Service
@SonarClassExclusion
public class PropertyManagementServiceImpl implements PropertyManagementService {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(PropertyManagementServiceImpl.class);

    @Autowired
    private PropertyRepository propertyRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PropertyMapper propertyMapper;

    @Autowired
    private NoteManagementService noteManagementService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ExceptionHandler exceptionHandler;

    @Autowired
    private PropertyLOIRepository propertyLOIRepository;

    @Autowired
    private PropertyLOIMapper propertyLOIMapper;

    @Autowired
    private PropertyLeaseMapper propertyLeaseMapper;

    @Autowired
    private PropertyLeaseRepository propertyLeaseRepository;

    @Autowired
    private LeaseExtensionMapper leaseExensionMapper;

    @Autowired
    private LeaseExtensionRepository leaseExtensionRepository;

    @Autowired
    private PropertyLeaseRepository propertyLeaseModalRepository;

    @Autowired
    private PropertyPurchaseRepository purchaseRepository;

    @Autowired
    private PropertyPurchaseMapper propertyPurchaseMapper;

    @Autowired
    private PurchaseExtensionRepository purchaseExtensionRepository;

    @Autowired
    private PurchaseExtensionMapper purchaseExtensionMapper;

    @Autowired
    private PropertyContractRepository contractRepository;

    @Autowired
    private PropertyContractMapper contractMapper;

    @Autowired
    private FilenameRepository fileRepository;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private PropertyPermittingRepository permittingRepository;

    @Autowired
    private PropertyPermittingMapper permittingMapper;

    @Autowired
    private MeetingsRepository meetingsRepository;

    @Autowired
    private MeetingsMapper meetingsMapper;

    @Autowired
    private PermittingAssignmentTaskRepository taskRepository;

    @Autowired
    private PermittingTaskMapper taskMapper;

    @Autowired
    private PermittingContactMapper contactMapper;

    @Autowired
    private PermittingContactRepository contactRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public PropertyBean insertProperty(PropertyBean property)
	    throws DBException {
	LOGGER.info("Insert property {}" + property);

	PropertyBean view = null;
	try {
	    Property entity = propertyMapper.propertyBeanToProperty(property);
	    LOGGER.info("Looking for Maverik Company - It should Maverik to match it");
	    Company company = companyRepository.findByCompanyName("Maverik");
	    if (company != null) {
		Set<Company> c = new HashSet<Company>();
		c.add(company);
		entity.setCompanies(c);
	    }
	    view = propertyMapper.propertyToPropertyBean(propertyRepository
		    .save(entity));
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
	return view;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public PropertyBean updateProperty(PropertyBean property)
	    throws DBException {
	LOGGER.info("Update property {}" + property);
	PropertyBean result = null;
	try {
	    Property entity = propertyRepository.findOne(property.getId());
	    Property view = propertyMapper.propertyBeanToProperty(property);
	    view.setPictureFileName(entity.getPictureFileName());
	    view.setCompanies(entity.getCompanies());
	    entity = null;
	    result = propertyMapper.propertyToPropertyBean(propertyRepository
		    .save(view));
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
	return result;
    }

    @Override
    @Transactional(readOnly = true)
    public PropertyBean findByProperty(Long id) throws DBException {
	LOGGER.info("Find property {}" + id);
	PropertyBean property = null;
	try {
	    Property entity = propertyRepository.findOne(id);
	    property = propertyMapper.propertyToPropertyBean(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return property;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public void deleteProperty(PropertyBean property) throws DBException {
	LOGGER.info("Delete property {}" + property);
	try {
	    Property entity = propertyRepository.findOne(property.getId());
	    propertyRepository.delete(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
    }

    @Override
    @Transactional(readOnly = true)
    public List<PropertyBean> findAllProperties() throws DBException {
	LOGGER.info("findAllProperties()");

	List<PropertyBean> listProperties = null;
	try {
	    List<Property> listEntities = propertyRepository.findAll();
	    listProperties = propertyMapper
		    .listOfPropertiesToListOfPropertyBeans(listEntities);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return listProperties;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.PropertyManagementService#
     * findPropertiesByResearchPhase()
     */
    @Override
    @Transactional(readOnly = true)
    public List<PropertyBean> findPropertiesByResearchPhase()
	    throws GenericException {
	LOGGER.info("findPropertiesByResearchPhase()");

	List<PropertyBean> listProperties = null;
	try {
	    List<Property> listEntities = propertyRepository
		    .findByStatus((byte) 0);
	    listProperties = propertyMapper
		    .listOfPropertiesToListOfPropertyBeans(listEntities);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return listProperties;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.PropertyManagementService#findByPropertyName
     * (java.lang.String)
     */
    @Override
    @Transactional(readOnly = true)
    public PropertyBean findByPropertyName(String name) throws GenericException {
	LOGGER.info("Find property name {}" + name);
	PropertyBean property = null;
	try {
	    Property entity = propertyRepository.findByName(name);
	    property = propertyMapper.propertyToPropertyBean(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return property;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.PropertyManagementService#
     * insertPropertyWithProjects(com.maverik.realestate.view.bean.PropertyBean,
     * java.util.List)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public PropertyBean insertPropertyWithProjects(PropertyBean property,
	    List<ProjectBean> projects) throws GenericException {
	LOGGER.info("insertPropertyWithProjects({})" + property);

	Property propertyEntity = null;
	List<Project> projectsLst = null;
	try {
	    propertyEntity = propertyMapper.propertyBeanToProperty(property);
	    projectsLst = projectMapper
		    .listOfProjectBeansToListOfProjects(projects);
	    projectsLst.get(0).setProperty(propertyEntity);
	    Set<Project> setOfProjects = new HashSet<Project>(projectsLst);
	    propertyEntity.setProjects(setOfProjects);
	    propertyEntity = propertyRepository.save(propertyEntity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return propertyMapper.propertyToPropertyBean(propertyEntity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.PropertyManagementService#
     * findPropertiesByContractPhase()
     */
    @Override
    @Transactional(readOnly = true)
    public List<PropertyBean> findPropertiesByContractPhase()
	    throws GenericException {
	LOGGER.info("findPropertiesByContractPhase()");

	List<PropertyBean> listProperties = null;
	try {
	    List<Property> listEntities = propertyRepository
		    .findByStatus((byte) 1);
	    listProperties = propertyMapper
		    .listOfPropertiesToListOfPropertyBeans(listEntities);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return listProperties;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.PropertyManagementService#findLOIByProperty
     * (com.maverik.realestate.domain.entity.Property)
     */
    @Override
    @Transactional(readOnly = true)
    public List<PropertyLOIBean> findLOIByProperty(Long propertyId)
	    throws GenericException {
	LOGGER.info("findLOIByProperty({})", propertyId);

	List<PropertyLOIBean> lstPropertyLOIs = null;
	Property p = new Property();
	p.setId(propertyId);
	try {
	    lstPropertyLOIs = propertyLOIMapper
		    .listOfPropertiesLOIToListOfPropertyLOIBeans(propertyLOIRepository
			    .findByPropertyId(p));
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return lstPropertyLOIs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.PropertyManagementService#findLOIByAcceptance
     * (java.lang.Byte)
     */
    @Override
    @Transactional(readOnly = true)
    public List<PropertyLOIBean> findLOIByAcceptance(Long propertyId,
	    Byte accepted) throws GenericException {
	LOGGER.info("findLOIByAcceptance({},{})", propertyId, accepted);

	List<PropertyLOIBean> lstPropertyLOIs = null;
	Property p = new Property();
	p.setId(propertyId);
	try {
	    lstPropertyLOIs = propertyLOIMapper
		    .listOfPropertiesLOIToListOfPropertyLOIBeans(propertyLOIRepository
			    .findByPropertyIdAndAccepted(p, accepted));
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return lstPropertyLOIs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.PropertyManagementService#saveLOIPrice
     * (java.lang.Long, java.math.BigDecimal)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public Integer saveLOIPrice(Long loiID, BigDecimal price)
	    throws GenericException {
	LOGGER.info("saveLOIPrice({},{})", loiID, price);

	Integer result = null;

	try {
	    result = propertyLOIRepository.updatePrice(price, loiID);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.PropertyManagementService#acceptLOIPrice
     * (java.lang.Long, java.math.BigDecimal)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public Integer acceptLOIPrice(Long loiID, BigDecimal price, Long propertyId)
	    throws GenericException {
	LOGGER.info("acceptLOIPrice({},{})", loiID, price);

	Integer result = null;

	try {
	    result = propertyLOIRepository.rejectPrice(propertyId);
	    result = result + propertyLOIRepository.acceptPrice(price, loiID);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.PropertyManagementService#
     * findLeaseByAcceptance(java.lang.Long, java.lang.Byte)
     */
    @Override
    public List<PropertyLeaseBean> findLeaseByAcceptance(Long propertyId,
	    Byte accepted) throws GenericException {
	LOGGER.info("findLeaseByAcceptance({},{})", propertyId, accepted);

	List<PropertyLeaseBean> lstPropertyLease = null;
	Property p = new Property();
	p.setId(propertyId);
	try {
	    lstPropertyLease = propertyLeaseMapper
		    .listOfPropertiesLeaseToListOfPropertyLeaseBeans(propertyLeaseRepository
			    .findByPropertyIdAndAccepted(p, accepted));
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return lstPropertyLease;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.PropertyManagementService#
     * findLeaseExtensionsByLease(java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public List<LeaseExtensionBean> findLeaseExtensionsByLease(Long leaseId)
	    throws GenericException {
	LOGGER.info("findLeaseExtensionsByLease({})", leaseId);

	List<LeaseExtensionBean> lstExtensionsBean = null;
	PropertyLease p = new PropertyLease();
	p.setId(leaseId);
	try {
	    lstExtensionsBean = leaseExensionMapper
		    .leaseExtensionsToLeaseExtensionBeans(leaseExtensionRepository
			    .findByPropertyLeaseId(p));
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return lstExtensionsBean;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.PropertyManagementService#saveLease(com
     * .maverik.realestate.view.bean.PropertyLeaseBean, java.util.List)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public PropertyLeaseBean saveLease(PropertyLeaseBean lease,
	    List<LeaseExtensionBean> extensions) throws GenericException {
	LOGGER.info("saveLease({},{})", lease, extensions);

	PropertyLease entity = null;
	try {
	    Property property = propertyRepository.findOne(lease
		    .getPropertyId());
	    entity = propertyLeaseRepository.findOne(lease.getId());
	    PropertyLease entity_ = propertyLeaseMapper
		    .propertyLeaseBeanToPropertyLease(lease);
	    entity = setMissingLeaseFields(entity_, entity);
	    Set<PropertyLease> leases = new HashSet<PropertyLease>();
	    leases.add(entity);
	    property.setLeases(leases);
	    entity.setPropertyId(property);
	    if (extensions != null && !extensions.isEmpty()) {
		List<LeaseExtension> extensionsEntity = leaseExensionMapper
			.leaseExtensionBeansToLeaseExtensions(extensions);
		entity.setExtensions(extensionsEntity);
		for (LeaseExtension e : extensionsEntity) {
		    e.setPropertyLeaseId(entity);
		}
	    }
	    entity = propertyLeaseRepository.save(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return propertyLeaseMapper.propertyLeaseToPropertyLeaseBean(entity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.PropertyManagementService#findAcceptedLease
     * (java.lang.Long, java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public List<LeaseAndExtensionBean> findAcceptedLease(Long propertyId,
	    Long leaseId) throws GenericException {
	LOGGER.info("findAcceptedLease({},{})", propertyId, leaseId);

	List<PropertyLeaseBean> lstPropertyLease = new ArrayList<PropertyLeaseBean>();
	lstPropertyLease = findLeaseByAcceptance(propertyId, (byte) 1);
	List<LeaseAndExtensionBean> beans = new ArrayList<LeaseAndExtensionBean>();
	for (PropertyLeaseBean p : lstPropertyLease) {
	    LeaseAndExtensionBean bean = new LeaseAndExtensionBean();
	    bean.setLease(p);
	    List<LeaseExtensionBean> lstExtension = new ArrayList<LeaseExtensionBean>();
	    lstExtension = findLeaseExtensionsByLease(p.getId());
	    bean.setExtension(lstExtension);
	    beans.add(bean);
	}

	return beans;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.PropertyManagementService#findNonAcceptedLease
     * (java.lang.Long, java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public List<LeaseAndExtensionBean> findNonAcceptedLease(Long propertyId,
	    Long leaseId) throws GenericException {
	LOGGER.info("findNonAcceptedLease({},{})", propertyId, leaseId);

	List<PropertyLeaseBean> lstPropertyLease = new ArrayList<PropertyLeaseBean>();
	lstPropertyLease = findLeaseByAcceptance(propertyId, (byte) 0);
	List<LeaseAndExtensionBean> beans = new ArrayList<LeaseAndExtensionBean>();
	for (PropertyLeaseBean p : lstPropertyLease) {
	    LeaseAndExtensionBean bean = new LeaseAndExtensionBean();
	    bean.setLease(p);
	    List<LeaseExtensionBean> lstExtension = new ArrayList<LeaseExtensionBean>();
	    lstExtension = findLeaseExtensionsByLease(p.getId());
	    bean.setExtension(lstExtension);
	    beans.add(bean);
	}

	return beans;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.PropertyManagementService#acceptLease(
     * com.maverik.realestate.view.bean.PropertyLeaseBean, java.util.List)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public PropertyLeaseBean acceptLease(PropertyLeaseBean lease,
	    List<LeaseExtensionBean> extensions) throws GenericException {
	LOGGER.info("acceptLease({},{})", lease, extensions);

	PropertyLease entity = null;
	try {
	    Property property = propertyRepository.findOne(lease
		    .getPropertyId());
	    entity = propertyLeaseRepository.findOne(lease.getId());
	    PropertyLease entity_ = propertyLeaseMapper
		    .propertyLeaseBeanToPropertyLease(lease);
	    entity = setMissingLeaseFields(entity_, entity);
	    entity.setAccepted((byte) 1);
	    Set<PropertyLease> leases = new HashSet<PropertyLease>();
	    leases.add(entity);
	    property.setLeases(leases);
	    entity.setPropertyId(property);
	    if (extensions != null && !extensions.isEmpty()) {
		List<LeaseExtension> extensionsEntity = leaseExensionMapper
			.leaseExtensionBeansToLeaseExtensions(extensions);
		entity.setExtensions(extensionsEntity);
		for (LeaseExtension e : extensionsEntity) {
		    e.setPropertyLeaseId(entity);
		}
	    }
	    Integer result = propertyLeaseRepository.rejectLease(entity
		    .getPropertyId().getId());
	    LOGGER.info("# Lease rejected " + result);
	    entity = propertyLeaseRepository.save(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return propertyLeaseMapper.propertyLeaseToPropertyLeaseBean(entity);
    }

    private PropertyLease setMissingLeaseFields(PropertyLease source,
	    PropertyLease target) {
	target.setLandlord(source.getLandlord());
	target.setLeaseAmount(source.getLeaseAmount());
	target.setRentCommencementDate(source.getRentCommencementDate());
	target.setDueDiligenceTerm(source.getDueDiligenceTerm());
	target.setDeadLineDate(source.getDeadLineDate());
	target.setNumberOfExtensions(source.getNumberOfExtensions());
	target.setExtensionLengthOfYears(source.getExtensionLengthOfYears());
	target.setInitialTerm(source.getInitialTerm());
	target.setRofr(source.getRofr());
	target.setRofo(source.getRofo());
	target.setPurchaseOption(source.getPurchaseOption());
	target.setPurchaseOptionAfter(source.getPurchaseOptionAfter());

	return target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.PropertyManagementService#savePurchase
     * (com.maverik.realestate.view.bean.PropertyPurchaseBean, java.util.List)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public PropertyPurchaseBean savePurchase(PropertyPurchaseBean purchase,
	    List<PurchaseExtensionBean> extensions) throws GenericException {
	LOGGER.info("savePurchase({},{})", purchase, extensions);

	PropertyPurchase entity = null;
	try {
	    Property property = propertyRepository.findOne(purchase
		    .getPropertyId());
	    entity = purchaseRepository.findOne(purchase.getId());
	    PropertyPurchase entity_ = propertyPurchaseMapper
		    .propertyPurchaseBeanToPropertyPurchase(purchase);
	    entity = setMissingPurchaseFields(entity_, entity);
	    Set<PropertyPurchase> purchases = new HashSet<PropertyPurchase>();
	    purchases.add(entity);
	    property.setPurchases(purchases);
	    entity.setPropertyId(property);
	    if (extensions != null && !extensions.isEmpty()) {
		List<PurchaseExtension> extensionsEntity = purchaseExtensionMapper
			.purchaseExtensionBeansToPurchaseExtensions(extensions);
		entity.setExtensions(extensionsEntity);
		for (PurchaseExtension e : extensionsEntity) {
		    e.setPropertyPurchaseId(entity);
		}
	    }
	    entity = purchaseRepository.save(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return propertyPurchaseMapper
		.propertyPurchaseToPropertyPurchaseBean(entity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.PropertyManagementService#acceptPurchase
     * (com.maverik.realestate.view.bean.PropertyPurchaseBean, java.util.List)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public PropertyPurchaseBean acceptPurchase(PropertyPurchaseBean purchase,
	    List<PurchaseExtensionBean> extensions) throws GenericException {
	LOGGER.info("acceptPurchase({},{})", purchase, extensions);

	PropertyPurchase entity = null;
	try {
	    Property property = propertyRepository.findOne(purchase
		    .getPropertyId());
	    entity = purchaseRepository.findOne(purchase.getId());
	    PropertyPurchase entity_ = propertyPurchaseMapper
		    .propertyPurchaseBeanToPropertyPurchase(purchase);
	    entity = setMissingPurchaseFields(entity_, entity);
	    entity.setAccepted((byte) 1);
	    Set<PropertyPurchase> purchases = new HashSet<PropertyPurchase>();
	    purchases.add(entity);
	    property.setPurchases(purchases);
	    entity.setPropertyId(property);
	    if (extensions != null && !extensions.isEmpty()) {
		List<PurchaseExtension> extensionsEntity = purchaseExtensionMapper
			.purchaseExtensionBeansToPurchaseExtensions(extensions);
		entity.setExtensions(extensionsEntity);
		for (PurchaseExtension e : extensionsEntity) {
		    e.setPropertyPurchaseId(entity);
		}
	    }
	    Integer result = purchaseRepository.rejectPurchase(entity
		    .getPropertyId().getId());
	    LOGGER.info("# Purchase rejected " + result);
	    entity = purchaseRepository.save(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return propertyPurchaseMapper
		.propertyPurchaseToPropertyPurchaseBean(entity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.PropertyManagementService#findAcceptedPurchase
     * (java.lang.Long, java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public List<PurchaseAndExtensionWrapBean> findAcceptedPurchase(
	    Long propertyId, Long purchaseId) throws GenericException {
	LOGGER.info("findAcceptedPurchase({},{})", propertyId, purchaseId);

	List<PurchaseAndExtensionWrapBean> beans = null;
	List<PropertyPurchaseBean> lstPropertyPurchase = new ArrayList<PropertyPurchaseBean>();
	Property property = new Property();
	property.setId(propertyId);
	try {
	    lstPropertyPurchase = propertyPurchaseMapper
		    .listOfPropertiesPurchaseToListOfPropertyPurchaseBeans(purchaseRepository
			    .findByPropertyIdAndAccepted(property, (byte) 1));
	    beans = new ArrayList<PurchaseAndExtensionWrapBean>();
	    for (PropertyPurchaseBean p : lstPropertyPurchase) {
		PurchaseAndExtensionWrapBean bean = new PurchaseAndExtensionWrapBean();
		bean.setPurchase(p);
		List<PurchaseExtensionBean> lstExtension = new ArrayList<PurchaseExtensionBean>();
		lstExtension = purchaseExtensionMapper
			.purchaseExtensionsToPurchaseExtensionBeans(purchaseExtensionRepository
				.findByPropertyPurchaseId(propertyPurchaseMapper
					.propertyPurchaseBeanToPropertyPurchase(p)));
		bean.setExtensions(lstExtension);
		beans.add(bean);
	    }
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return beans;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.PropertyManagementService#
     * findNonAcceptedPurchase(java.lang.Long, java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public List<PurchaseAndExtensionWrapBean> findNonAcceptedPurchase(
	    Long propertyId, Long purchaseId) throws GenericException {
	LOGGER.info("findNonAcceptedPurchase({},{})", propertyId, purchaseId);

	List<PurchaseAndExtensionWrapBean> beans = null;
	List<PropertyPurchaseBean> lstPropertyPurchase = new ArrayList<PropertyPurchaseBean>();
	Property property = new Property();
	property.setId(propertyId);
	try {
	    lstPropertyPurchase = propertyPurchaseMapper
		    .listOfPropertiesPurchaseToListOfPropertyPurchaseBeans(purchaseRepository
			    .findByPropertyIdAndAccepted(property, (byte) 0));
	    beans = new ArrayList<PurchaseAndExtensionWrapBean>();
	    for (PropertyPurchaseBean p : lstPropertyPurchase) {
		PurchaseAndExtensionWrapBean bean = new PurchaseAndExtensionWrapBean();
		bean.setPurchase(p);
		List<PurchaseExtensionBean> lstExtension = new ArrayList<PurchaseExtensionBean>();
		lstExtension = purchaseExtensionMapper
			.purchaseExtensionsToPurchaseExtensionBeans(purchaseExtensionRepository
				.findByPropertyPurchaseId(propertyPurchaseMapper
					.propertyPurchaseBeanToPropertyPurchase(p)));
		bean.setExtensions(lstExtension);
		beans.add(bean);
	    }
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return beans;
    }

    private PropertyPurchase setMissingPurchaseFields(PropertyPurchase source,
	    PropertyPurchase target) {
	target.setEffectiveDate(source.getEffectiveDate());
	target.setPrice(source.getPrice());
	target.setDueDiligenceTerm(source.getDueDiligenceTerm());
	target.setDeadLineDate(source.getDeadLineDate());
	target.setClosingTerm(source.getClosingTerm());
	target.setClosingDate(source.getClosingDate());

	return target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.PropertyManagementService#findPropertyContract
     * (java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public PropertyContractViewBean findPropertyContract(Long id)
	    throws GenericException {
	LOGGER.info("findPropertyContract({})", id);

	PropertyBean property = null;
	PropertyContractViewBean propertyContract = null;
	PropertyContractBean contract = null;
	try {
	    property = propertyMapper.propertyToPropertyBean(propertyRepository
		    .findOne(id));
	    Property p = new Property();
	    p.setId(property.getId());
	    contract = contractMapper
		    .propertyContractToPropertyContractBean(contractRepository
			    .findByPropertyId(p));
	    propertyContract = new PropertyContractViewBean(property, contract);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return propertyContract;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.PropertyManagementService#savePropertyContract
     * (com.maverik.realestate.view.bean.PropertyContractViewBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public PropertyContractViewBean savePropertyContract(
	    PropertyContractViewBean propertyContract) throws GenericException {
	LOGGER.info("savePropertyContract({})", propertyContract);

	try {
	    Property property = propertyRepository.findOne(propertyContract
		    .getProperty().getId());
	    property.setStatus(propertyContract.getProperty().getStatus());
	    PropertyContract contract = contractMapper
		    .propertyContractBeanToPropertyContract(propertyContract
			    .getPropertyContract());
	    contract.setPropertyId(property);
	    contract.setReCommitteeSOFileName(property.getContractType()
		    .getReCommitteeSOFileName());
	    contract.setTitlePolicyFileName(property.getContractType()
		    .getTitlePolicyFileName());
	    contract.setTitleCommitmentFileName(property.getContractType()
		    .getTitleCommitmentFileName());
	    contract.setSettlementFileName(property.getContractType()
		    .getSettlementFileName());
	    property.setContractType(contract);
	    // property = propertyRepository.save(property);
	    contract = contractRepository.save(contract);
	    propertyContract.setProperty(propertyMapper
		    .propertyToPropertyBean(property));
	    propertyContract.setPropertyContract(contractMapper
		    .propertyContractToPropertyContractBean(contract));
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return propertyContract;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.PropertyManagementService#
     * uploadPropertyPicture(com.maverik.realestate.response.FileResponse)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public FileBean uploadPropertyPicture(FileBean fileBean, Long propertyId)
	    throws GenericException {
	LOGGER.info("uploadPropertyPicture({},{})", fileBean, propertyId);

	Filename file = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    fileRepository.save(file);
	    Property p = propertyRepository.findOne(propertyId);
	    p.setPictureFileName(file);
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
     * @see com.maverik.realestate.service.PropertyManagementService#
     * uploadPropertyLOIFile(com.maverik.realestate.view.bean.FileBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public FileBean createLOIAndAddFile(FileBean fileBean, Long propertyId)
	    throws GenericException {
	LOGGER.info("createLOIAndAddFile({}{})", fileBean, propertyId);

	Filename file = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    file = fileRepository.save(file);
	    PropertyLOI loi = new PropertyLOI();
	    loi.setFileId(file);
	    Property property = propertyRepository.findOne(propertyId);
	    loi.setPropertyId(property);
	    loi.setAccepted((byte) 0);
	    propertyLOIRepository.save(loi);
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
     * com.maverik.realestate.service.PropertyManagementService#createContract()
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public PropertyBean createContractAndUpdateProperty(PropertyBean property)
	    throws GenericException {
	LOGGER.info("createContract({})", property);

	Property result = null;
	try {
	    Property entity = propertyRepository.findOne(property.getId());
	    result = propertyMapper.propertyBeanToProperty(property);
	    PropertyContract contract = new PropertyContract();
	    contract.setPropertyId(result);
	    result.setContractType(contract);
	    result.setPictureFileName(entity.getPictureFileName());
	    result.setCompanies(entity.getCompanies());
	    entity = null;
	    result = propertyRepository.save(result);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
	return propertyMapper.propertyToPropertyBean(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.PropertyManagementService#uploadRECSOFile
     * (com.maverik.realestate.view.bean.FileBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public FileBean addRECSOFile(FileBean fileBean, Long contractId)
	    throws GenericException {
	LOGGER.info("uploadRECSOFile({})", fileBean);

	Filename file = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    file = fileRepository.save(file);
	    PropertyContract c = contractRepository.findOne(contractId);
	    c.setReCommitteeSOFileName(file);
	    contractRepository.save(c);
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
     * @see com.maverik.realestate.service.PropertyManagementService#
     * addTitleCommitmentFile(com.maverik.realestate.view.bean.FileBean,
     * java.lang.Long)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public FileBean addTitleCommitmentFile(FileBean fileBean, Long contractId)
	    throws GenericException {
	LOGGER.info("addTitleCommitmentFile({})", fileBean);

	Filename file = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    file = fileRepository.save(file);
	    PropertyContract c = contractRepository.findOne(contractId);
	    c.setTitleCommitmentFileName(file);
	    contractRepository.save(c);
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
     * com.maverik.realestate.service.PropertyManagementService#addTitlePolicyFile
     * (com.maverik.realestate.view.bean.FileBean, java.lang.Long)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public FileBean addTitlePolicyFile(FileBean fileBean, Long contractId)
	    throws GenericException {
	LOGGER.info("addTitlePolicyFile({})", fileBean);

	Filename file = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    file = fileRepository.save(file);
	    PropertyContract c = contractRepository.findOne(contractId);
	    c.setTitlePolicyFileName(file);
	    contractRepository.save(c);
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
     * @see com.maverik.realestate.service.PropertyManagementService#
     * createLeaseAndAddFile(com.maverik.realestate.view.bean.FileBean,
     * java.lang.Long)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public FileBean createLeaseAndAddFile(FileBean fileBean, Long propertyId)
	    throws GenericException {
	LOGGER.info("createLeaseAndAddFile({}{})", fileBean, propertyId);

	Filename file = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    file = fileRepository.save(file);
	    PropertyLease lease = new PropertyLease();
	    lease.setFileId(file);
	    Property property = propertyRepository.findOne(propertyId);
	    lease.setPropertyId(property);
	    lease.setAccepted((byte) 0);
	    propertyLeaseRepository.save(lease);
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
     * @see com.maverik.realestate.service.PropertyManagementService#
     * createPurchaseAndAddFile(com.maverik.realestate.view.bean.FileBean,
     * java.lang.Long)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public FileBean createPurchaseAndAddFile(FileBean fileBean, Long propertyId)
	    throws GenericException {
	LOGGER.info("createPurchaseAndAddFile({}{})", fileBean, propertyId);

	Filename file = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    file = fileRepository.save(file);
	    PropertyPurchase purchase = new PropertyPurchase();
	    purchase.setFileId(file);
	    Property property = propertyRepository.findOne(propertyId);
	    purchase.setPropertyId(property);
	    purchase.setAccepted((byte) 0);
	    purchaseRepository.save(purchase);
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
     * com.maverik.realestate.service.PropertyManagementService#addSettlementFile
     * (com.maverik.realestate.view.bean.FileBean, java.lang.Long)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public FileBean addSettlementFile(FileBean fileBean, Long contractId)
	    throws GenericException {
	LOGGER.info("addSettlementFile({})", fileBean);

	Filename file = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    file = fileRepository.save(file);
	    PropertyContract c = contractRepository.findOne(contractId);
	    c.setSettlementFileName(file);
	    contractRepository.save(c);
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
     * @see com.maverik.realestate.service.PropertyManagementService#
     * createLandPermittingAndUpdateProperty
     * (com.maverik.realestate.view.bean.PropertyBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public PropertyBean createLandPermittingAndUpdateProperty(
	    PropertyContractViewBean propertyContract) throws GenericException {
	LOGGER.info("createLandPermittingAndUpdateProperty({})",
		propertyContract);

	Property entity = null;
	try {
	    entity = propertyRepository.findOne(propertyContract.getProperty()
		    .getId());
	    PropertyPermitting permitting = new PropertyPermitting();
	    List<PropertyMeeting> meetings = buildListOfMeetings(permitting);
	    permitting
		    .setPropertyMeeting(new HashSet<PropertyMeeting>(meetings));
	    permitting.setPropertyId(entity);
	    entity.setPermitting(permitting);
	    entity = propertyRepository.save(entity);
	    // meetingsRepository.save(meetings);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
	return propertyMapper.propertyToPropertyBean(entity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.PropertyManagementService#findLandPermitting
     * (java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public PermittingMeetingsViewBean findLandPermittingByProperty(Long id)
	    throws GenericException {
	LOGGER.info("findLandPermitting({})", id);

	PropertyPermittingBean permittingBean = null;
	PermittingMeetingsViewBean view = null;
	PropertyPermitting entity = null;
	try {
	    Property property = propertyRepository.findOne(id);
	    // property.setId(id);
	    view = new PermittingMeetingsViewBean();
	    // entity = permittingRepository.findByPropertyId(property);
	    entity = property.getPermitting();
	    permittingBean = permittingMapper
		    .propertyPermittingToPropertyPermittingBean(entity);
	    view.setPermitting(permittingBean);
	    List<PropertyMeeting> meetings = new ArrayList<PropertyMeeting>(
		    entity.getPropertyMeeting());
	    Collections.sort(meetings);
	    view.setMeetings(meetingsMapper
		    .listOfPropertiesMeetingToListOfPropertyMeetingBeans(meetings));
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return view;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.PropertyManagementService#
     * savePropertyPermitting
     * (com.maverik.realestate.view.bean.PropertyPermittingBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public PermittingMeetingsViewBean savePropertyPermitting(
	    PermittingMeetingsViewBean permitting) throws GenericException {
	LOGGER.info("savePropertyPermitting({})", permitting);

	try {
	    Property property = propertyRepository.findOne(permitting
		    .getPermitting().getPropertyId());
	    PropertyPermitting view = permittingMapper
		    .propertyPermittingBeanToPropertyPermitting(permitting
			    .getPermitting());
	    view.setId(property.getPermitting().getId());
	    view.setCommitmentFileName(property.getPermitting()
		    .getCommitmentFileName());
	    view.setUploadLetterFileName(property.getPermitting()
		    .getUploadLetterFileName());
	    view.setGeotechnicalFileName(property.getPermitting()
		    .getGeotechnicalFileName());
	    view.setPreSitePlanReceivedFileName(property.getPermitting()
		    .getPreSitePlanReceivedFileName());
	    view.setSurveyFileName(property.getPermitting().getSurveyFileName());
	    view.setTrafficStudyFileName(property.getPermitting()
		    .getTrafficStudyFileName());
	    view.setPropertyId(property);
	    List<PropertyMeeting> meetingsView = new ArrayList<PropertyMeeting>(
		    property.getPermitting().getPropertyMeeting());
	    List<PropertyMeeting> meetings = meetingsMapper
		    .listOfPropertyMeetingBeansToListOfPropertiesMeeting(permitting
			    .getMeetings());
	    int meetingIndex = 0;
	    Collections.sort(meetingsView);
	    Collections.sort(meetings);
	    for (PropertyMeeting meeting : meetingsView) {
		meeting.setMeeting(meetings.get(meetingIndex).getMeeting());
		meeting.setMeetingName(meetings.get(meetingIndex)
			.getMeetingName());
		meeting.setMeetingSubDeadline(meetings.get(meetingIndex)
			.getMeetingSubDeadline());
		meeting.setMeetingDate(meetings.get(meetingIndex)
			.getMeetingDate());
		meeting.setMeetingApprovalDate(meetings.get(meetingIndex)
			.getMeetingApprovalDate());
		meeting.setMeetingNotes(meetings.get(meetingIndex)
			.getMeetingNotes());
		meetingIndex++;
	    }
	    permitting
		    .setMeetings(meetingsMapper
			    .listOfPropertiesMeetingToListOfPropertyMeetingBeans(meetingsView));
	    permitting.setPermitting(permittingMapper
		    .propertyPermittingToPropertyPermittingBean(view));
	    property.setPermitting(view);
	    view = permittingRepository.save(view);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return permitting;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.PropertyManagementService#
     * createPermittingTaskAndUpdateProperty
     * (com.maverik.realestate.view.bean.PropertyPermittingBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public PropertyBean updatePropertyToPermittingTaskPhase(
	    PropertyPermittingBean permitting) throws GenericException {
	LOGGER.info("updatePropertyToPermittingTaskPhase({})", permitting);

	try {
	    Property property = propertyRepository.findOne(permitting
		    .getPropertyId());
	    property.setStatus((byte) 3);
	    return propertyMapper.propertyToPropertyBean(propertyRepository
		    .save(property));
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
     * addPermittingCommitmentFile(com.maverik.realestate.view.bean.FileBean,
     * java.lang.Long)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public FileBean addPermittingCommitmentFile(FileBean fileBean,
	    Long permittingId) throws GenericException {
	LOGGER.info("addPermittingCommitmentFile({},{})", fileBean,
		permittingId);

	Filename file = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    file = fileRepository.save(file);
	    PropertyPermitting p = permittingRepository.findOne(permittingId);
	    p.setCommitmentFileName(file);
	    permittingRepository.save(p);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return fileMapper.fileToFileBean(file);
    }

    private List<PropertyMeeting> buildListOfMeetings(
	    PropertyPermitting permitting) {
	LOGGER.info("buildListOfMeetings({})", permitting);

	List<PropertyMeeting> meetings = new ArrayList<PropertyMeeting>();
	Integer numberOfMeetings = new Integer(
		Meetings.TOTAL_MEETINGS.toString());
	for (int i = 0; i < numberOfMeetings; i++) {
	    PropertyMeeting meeting = new PropertyMeeting();
	    meeting.setMeetingId(i + 1);
	    meeting.setPropertyPermittingId(permitting);
	    meetings.add(meeting);
	}

	LOGGER.info("buildListOfMeetings return - {}", meetings);
	return meetings;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.PropertyManagementService#
     * findPropertiesByPermittingPhase()
     */
    @Override
    @Transactional(readOnly = true)
    public List<PropertyBean> findPropertiesByPermittingPhase()
	    throws GenericException {
	LOGGER.info("findPropertiesByPermittingPhase()");

	List<PropertyBean> listProperties = null;
	try {
	    List<Property> listEntities = propertyRepository
		    .findByStatus((byte) 2);
	    listProperties = propertyMapper
		    .listOfPropertiesToListOfPropertyBeans(listEntities);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return listProperties;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.PropertyManagementService#
     * addPermittingExceptionFile(com.maverik.realestate.view.bean.FileBean,
     * java.lang.Long)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public FileBean addPermittingExceptionFile(FileBean fileBean,
	    Long permittingId) throws GenericException {
	LOGGER.info("addPermittingExceptionFile({},{})", fileBean, permittingId);

	Filename file = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    file = fileRepository.save(file);
	    PropertyPermitting p = permittingRepository.findOne(permittingId);
	    p.setUploadLetterFileName(file);
	    permittingRepository.save(p);
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
     * @see com.maverik.realestate.service.PropertyManagementService#
     * addPermittingSurveryFile(com.maverik.realestate.view.bean.FileBean,
     * java.lang.Long)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public FileBean addPermittingSurveryFile(FileBean fileBean,
	    Long permittingId) throws GenericException {
	LOGGER.info("addPermittingSurveryFile({},{})", fileBean, permittingId);

	Filename file = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    file = fileRepository.save(file);
	    PropertyPermitting p = permittingRepository.findOne(permittingId);
	    p.setSurveyFileName(file);
	    permittingRepository.save(p);
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
     * @see com.maverik.realestate.service.PropertyManagementService#
     * addPermittingSitePlanFile(com.maverik.realestate.view.bean.FileBean,
     * java.lang.Long)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public FileBean addPermittingSitePlanFile(FileBean fileBean,
	    Long permittingId) throws GenericException {
	LOGGER.info("addPermittingSitePlanFile({},{})", fileBean, permittingId);

	Filename file = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    file = fileRepository.save(file);
	    PropertyPermitting p = permittingRepository.findOne(permittingId);
	    p.setPreSitePlanReceivedFileName(file);
	    permittingRepository.save(p);
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
     * @see com.maverik.realestate.service.PropertyManagementService#
     * addPermittingGeotechnicalFile(com.maverik.realestate.view.bean.FileBean,
     * java.lang.Long)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public FileBean addPermittingGeotechnicalFile(FileBean fileBean,
	    Long permittingId) throws GenericException {
	LOGGER.info("addPermittingGeotechnicalFile({},{})", fileBean,
		permittingId);

	Filename file = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    file = fileRepository.save(file);
	    PropertyPermitting p = permittingRepository.findOne(permittingId);
	    p.setGeotechnicalFileName(file);
	    permittingRepository.save(p);
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
     * @see com.maverik.realestate.service.PropertyManagementService#
     * addPermittingTrafficFile(com.maverik.realestate.view.bean.FileBean,
     * java.lang.Long)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public FileBean addPermittingTrafficFile(FileBean fileBean,
	    Long permittingId) throws GenericException {
	LOGGER.info("addPermittingTrafficFile({},{})", fileBean, permittingId);

	Filename file = null;
	try {
	    file = fileMapper.fileBeanToFile(fileBean);
	    file = fileRepository.save(file);
	    PropertyPermitting p = permittingRepository.findOne(permittingId);
	    p.setTrafficStudyFileName(file);
	    permittingRepository.save(p);
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
     * com.maverik.realestate.service.PropertyManagementService#getAllTaskByProperty
     * (java.lang.Long)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public List<PermittingAssignmentTaskBean> getAllTaskByProperty(
	    Long propertyId) throws GenericException {
	LOGGER.info("getAllTaskByProperty({})", propertyId);

	try {
	    Property p = new Property();
	    p.setId(propertyId);
	    return taskMapper.entitiesToBeans(taskRepository
		    .findByPropertyId(p));
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
     * com.maverik.realestate.service.PropertyManagementService#saveAssignmentTask
     * (com.maverik.realestate.view.bean.PermittingAssignmentTaskBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public PermittingAssignmentTaskBean saveAssignmentTask(
	    PermittingAssignmentTaskBean task) throws GenericException {
	LOGGER.info("saveAssignmentTask({})", task);

	try {
	    PermittingAssignmentTask entity = taskMapper.beanToEntity(task);
	    Property p = propertyRepository.findOne(entity.getPropertyId()
		    .getId());
	    List<PermittingAssignmentTask> tasks = new ArrayList<PermittingAssignmentTask>();
	    tasks.add(entity);
	    p.setPermittingTasks(tasks);
	    entity.setPropertyId(p);
	    return taskMapper.entityToBean(taskRepository.save(entity));
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
     * com.maverik.realestate.service.PropertyManagementService#updateAssignmentTask
     * (com.maverik.realestate.view.bean.PermittingAssignmentTaskBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public void markAssignmentTask(TaskStatusBean tasks)
	    throws GenericException {
	LOGGER.info("updateAssignmentTask({})", tasks);

	try {
	    List<PermittingAssignmentTask> markComplete = taskRepository
		    .findAll(tasks.getCompletedTasksId());
	    for (PermittingAssignmentTask task : markComplete) {
		task.setIsComplete((byte) 1);
		taskRepository.save(task);
	    }
	    List<PermittingAssignmentTask> markIncomplete = taskRepository
		    .findAll(tasks.getIncompletedTasksId());
	    for (PermittingAssignmentTask task : markIncomplete) {
		task.setIsComplete((byte) 0);
		taskRepository.save(task);
	    }
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
     * taskAvailableUsersByProperty(java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public List<String> getTaskAvailableUsersByProperty(Long propertyId)
	    throws GenericException {
	LOGGER.info("taskAvailableUsersByProperty({})", propertyId);

	try {
	    Property property = propertyRepository.findOne(propertyId);
	    List<String> userFullnames = new ArrayList<String>();
	    property.getCompanies()
		    .stream()
		    .filter(company -> company.getCompanyName()
			    .equalsIgnoreCase("Maverik"))
		    .forEach(
			    maverik -> maverik.getUsers().forEach(
				    user -> userFullnames.add(user
					    .getFirstName()
					    + " "
					    + user.getLastName())));
	    return userFullnames;
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
     * com.maverik.realestate.service.PropertyManagementService#saveContact(
     * com.maverik.realestate.view.bean.PermittingContactBean)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public PermittingContactBean saveContact(PermittingContactBean contact)
	    throws GenericException {
	LOGGER.info("LOGGER.info(saveContact({})", contact);

	try {
	    PermittingContact entity = contactMapper.beanToEntity(contact);
	    Property p = propertyRepository.findOne(entity.getPropertyId()
		    .getId());
	    List<PermittingContact> contacts = new ArrayList<PermittingContact>();
	    contacts.add(entity);
	    p.setPermittingContacts(contacts);
	    entity.setPropertyId(p);
	    return contactMapper.entityToBean(contactRepository.save(entity));
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
     * getAllContactsByProperty(java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public List<PermittingContactBean> getAllContactsByProperty(Long propertyId)
	    throws GenericException {
	LOGGER.info("getAllContactsByProperty({})", propertyId);

	try {
	    Property p = new Property();
	    p.setId(propertyId);
	    return contactMapper.entitiesToBeans(contactRepository
		    .findByPropertyId(p));
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
     * findPropertiesByPermittingTasksPhase()
     */
    @Override
    @Transactional(readOnly = true)
    public List<PropertyBean> findPropertiesByPermittingTasksPhase()
	    throws GenericException {
	LOGGER.info("findPropertiesByPermittingTasksPhase()");

	List<PropertyBean> listProperties = null;
	try {
	    List<Property> listEntities = propertyRepository
		    .findByStatus((byte) 3);
	    listProperties = propertyMapper
		    .listOfPropertiesToListOfPropertyBeans(listEntities);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return listProperties;
    }
}