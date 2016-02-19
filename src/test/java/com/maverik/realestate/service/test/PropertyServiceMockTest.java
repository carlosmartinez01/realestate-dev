/**
 * 
 */
package com.maverik.realestate.service.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.maverik.realestate.domain.entity.LeaseExtension;
import com.maverik.realestate.domain.entity.Project;
import com.maverik.realestate.domain.entity.Property;
import com.maverik.realestate.domain.entity.PropertyContract;
import com.maverik.realestate.domain.entity.PropertyLease;
import com.maverik.realestate.domain.entity.PropertyPurchase;
import com.maverik.realestate.domain.entity.PurchaseExtension;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.mapper.LeaseExtensionMapper;
import com.maverik.realestate.mapper.ProjectMapper;
import com.maverik.realestate.mapper.PropertyContractMapper;
import com.maverik.realestate.mapper.PropertyLeaseMapper;
import com.maverik.realestate.mapper.PropertyMapper;
import com.maverik.realestate.mapper.PropertyPurchaseMapper;
import com.maverik.realestate.mapper.PurchaseExtensionMapper;
import com.maverik.realestate.repository.LeaseExtensionRepository;
import com.maverik.realestate.repository.PropertyContractRepository;
import com.maverik.realestate.repository.PropertyLeaseRepository;
import com.maverik.realestate.repository.PropertyPurchaseRepository;
import com.maverik.realestate.repository.PropertyRepository;
import com.maverik.realestate.service.PropertyManagementService;
import com.maverik.realestate.service.PropertyManagementServiceImpl;
import com.maverik.realestate.view.bean.LeaseAndExtensionBean;
import com.maverik.realestate.view.bean.LeaseExtensionBean;
import com.maverik.realestate.view.bean.ProjectBean;
import com.maverik.realestate.view.bean.PropertyBean;
import com.maverik.realestate.view.bean.PropertyContractBean;
import com.maverik.realestate.view.bean.PropertyContractViewBean;
import com.maverik.realestate.view.bean.PropertyLeaseBean;
import com.maverik.realestate.view.bean.PropertyPurchaseBean;
import com.maverik.realestate.view.bean.PurchaseExtensionBean;

/**
 * @author jorge
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PropertyServiceMockTest {

    @InjectMocks
    private PropertyManagementService propertyService = new PropertyManagementServiceImpl();

    @Mock
    private PropertyRepository repository;

    @Mock
    private PropertyMapper propertyMapper;

    @Mock
    private PropertyContractMapper contractMapper;

    @Mock
    private PropertyContractRepository contractRepository;

    @Mock
    private PropertyLeaseRepository propertyLeaseRepository;

    @Mock
    private PropertyLeaseMapper propertyLeaseMapper;

    @Mock
    private LeaseExtensionMapper leaseExensionMapper;

    @Mock
    private LeaseExtensionRepository leaseExtRepository;

    @Mock
    private ProjectMapper projectMapper;

    @Mock
    private PropertyPurchaseRepository purchaseRepository;

    @Mock
    private PropertyPurchaseMapper purchaseMapper;

    @Mock
    private PurchaseExtensionMapper purchaseExtensionMapper;

    @Test
    public void testFindContract() throws GenericException {
	PropertyContractViewBean bean = new PropertyContractViewBean();
	PropertyBean p = new PropertyBean();
	p.setId(1L);
	p.setName("dummy property");
	bean.setProperty(p);
	PropertyContractBean c = new PropertyContractBean();
	c.setId(1L);
	c.setPropertyId(p.getId());
	c.setContractType("Lease");
	p.setContractType(c);
	bean.setPropertyContract(c);
	Property pe = new Property();
	pe.setId(1L);
	PropertyContract ce = new PropertyContract();
	ce.setId(1L);
	ce.setPropertyId(pe);
	ce.setContractType("Lease");
	pe.setContractType(ce);
	Mockito.when(repository.findOne(1L)).thenReturn(pe);
	Mockito.when(propertyMapper.propertyToPropertyBean(pe)).thenReturn(p);
	Mockito.when(contractRepository.findByPropertyId(pe)).thenReturn(ce);
	Mockito.when(contractMapper.propertyContractToPropertyContractBean(ce))
		.thenReturn(c);

	PropertyContractViewBean result = propertyService
		.findPropertyContract(1L);
	Assert.assertNotNull(bean);
	Assert.assertEquals(bean.getProperty().getId(), result.getProperty()
		.getId());
	Assert.assertEquals(bean.getPropertyContract().getContractType(),
		result.getPropertyContract().getContractType());
    }

    @Test
    public void testSaveLease() throws GenericException {
	LeaseAndExtensionBean leaseAndExtension = new LeaseAndExtensionBean();
	PropertyLeaseBean p_ui = new PropertyLeaseBean();
	p_ui.setId(2L);
	p_ui.setPropertyId(1L);
	leaseAndExtension.setLease(p_ui);
	List<LeaseExtensionBean> beans_ui = new ArrayList<LeaseExtensionBean>();
	LeaseExtensionBean e_ui = new LeaseExtensionBean();
	e_ui.setExtensionDays(20);
	beans_ui.add(e_ui);
	leaseAndExtension.setExtension(beans_ui);
	PropertyLease pl = new PropertyLease();
	pl.setId(2L);
	Property p = new Property();
	p.setId(1L);
	p.setName("dummy");
	pl.setPropertyId(p);
	List<LeaseExtension> ext = new ArrayList<LeaseExtension>();
	LeaseExtension e = new LeaseExtension();
	e.setExtensionDays(20);
	ext.add(e);
	PropertyLeaseBean leaseBean = p_ui;
	Mockito.when(repository.findOne(1L)).thenReturn(p);
	Mockito.when(propertyLeaseRepository.findOne(2L)).thenReturn(pl);
	Mockito.when(propertyLeaseMapper.propertyLeaseBeanToPropertyLease(p_ui))
		.thenReturn(pl);
	Mockito.when(
		leaseExensionMapper
			.leaseExtensionBeansToLeaseExtensions(beans_ui))
		.thenReturn(ext);
	Mockito.when(propertyLeaseRepository.save(pl)).thenReturn(pl);
	Mockito.when(propertyLeaseMapper.propertyLeaseToPropertyLeaseBean(pl))
		.thenReturn(leaseBean);

	PropertyLeaseBean result = propertyService.saveLease(
		leaseAndExtension.getLease(), leaseAndExtension.getExtension());
	Assert.assertNotNull(result);
	Assert.assertEquals(1L, result.getPropertyId().longValue());
    }

    @Test
    public void testFindLeaseExtensionsByLease() throws GenericException {
	List<LeaseExtensionBean> lstExtensionsBean_exp = new ArrayList<LeaseExtensionBean>();
	LeaseExtensionBean extBean_exp = new LeaseExtensionBean();
	extBean_exp.setId(10L);
	extBean_exp.setExtensionDays(20);
	lstExtensionsBean_exp.add(extBean_exp);
	PropertyLease pl = new PropertyLease();
	pl.setId(10L);
	List<LeaseExtension> lstExt_out = new ArrayList<LeaseExtension>();
	LeaseExtension ext_out = new LeaseExtension();
	ext_out.setId(10L);
	ext_out.setExtensionDays(20);
	lstExt_out.add(ext_out);
	Mockito.when(leaseExtRepository.findByPropertyLeaseId(pl)).thenReturn(
		lstExt_out);
	Mockito.when(
		leaseExensionMapper
			.leaseExtensionsToLeaseExtensionBeans(lstExt_out))
		.thenReturn(lstExtensionsBean_exp);
	List<LeaseExtensionBean> result = propertyService
		.findLeaseExtensionsByLease(10L);
	Assert.assertNotNull(result);
	Assert.assertEquals(20, result.get(0).getExtensionDays().intValue());
    }

    @Test
    public void testInsertPropertyAndProjects() throws GenericException {
	PropertyBean property_ui = new PropertyBean();
	property_ui.setName("dummy property");
	property_ui.setAddress("153 W 256 S");
	property_ui.setCity("SLC");
	Property p = new Property();
	p.setName(property_ui.getName());
	p.setAddress(property_ui.getAddress());
	p.setCity(property_ui.getCity());
	List<ProjectBean> projects_ui = new ArrayList<ProjectBean>();
	ProjectBean project_ui = new ProjectBean();
	project_ui.setProjectName("dummy project 1");
	project_ui.setDescription("Project description");
	projects_ui.add(project_ui);
	List<Project> projectsLst = new ArrayList<Project>();
	Project project = new Project();
	project.setProjectName(project_ui.getProjectName());
	project.setDescription(project_ui.getDescription());
	projectsLst.add(project);
	Mockito.when(propertyMapper.propertyBeanToProperty(property_ui))
		.thenReturn(p);
	Mockito.when(
		projectMapper.listOfProjectBeansToListOfProjects(projects_ui))
		.thenReturn(projectsLst);
	Mockito.when(repository.save(p)).thenReturn(p);
	Mockito.when(propertyMapper.propertyToPropertyBean(p)).thenReturn(
		property_ui);
	PropertyBean result = propertyService.insertPropertyWithProjects(
		property_ui, projects_ui);
	Assert.assertNotNull(result);
	Assert.assertEquals("dummy property", result.getName());
    }

    @Test
    public void testAcceptLease() throws Exception {
	Property property = new Property();
	property.setName("dummy property");
	property.setId(1L);
	PropertyLease entity = new PropertyLease();
	entity.setId(1L);
	entity.setLandlord("Dummy landlord");
	PropertyLeaseBean lease_ui = new PropertyLeaseBean();
	lease_ui.setId(1L);
	lease_ui.setLandlord("Dummy landlord");
	lease_ui.setPurchaseOption("Yes");
	lease_ui.setExtension(2);
	PropertyLease entity_ = new PropertyLease();
	entity_.setId(1L);
	entity_.setLandlord("Dummy landlord");
	entity_.setPurchaseOption("Yes");
	entity_.setExtension(1);
	lease_ui.setPropertyId(property.getId());
	Mockito.when(repository.findOne(1L)).thenReturn(property);
	Mockito.when(propertyLeaseRepository.findOne(1L)).thenReturn(entity);
	Mockito.when(
		propertyLeaseMapper.propertyLeaseBeanToPropertyLease(lease_ui))
		.thenReturn(entity_);
	List<LeaseExtensionBean> extensions = new ArrayList<LeaseExtensionBean>();
	LeaseExtensionBean extension = new LeaseExtensionBean();
	extension.setExtensionDays(20);
	extension.setPropertyLeaseId(2L);
	extensions.add(extension);
	List<LeaseExtension> extensionsEntity = new ArrayList<LeaseExtension>();
	LeaseExtension extensionEntity = new LeaseExtension();
	extensionEntity.setExtensionDays(20);
	extensionEntity.setPropertyLeaseId(entity);
	extensionsEntity.add(extensionEntity);
	Mockito.when(
		leaseExensionMapper
			.leaseExtensionBeansToLeaseExtensions(extensions))
		.thenReturn(extensionsEntity);
	Mockito.when(propertyLeaseRepository.rejectLease(1L)).thenReturn(1);
	entity.setAccepted((byte) 1);
	Mockito.when(propertyLeaseRepository.save(entity)).thenReturn(entity);
	lease_ui.setAccepted((byte) 1);
	Mockito.when(
		propertyLeaseMapper.propertyLeaseToPropertyLeaseBean(entity))
		.thenReturn(lease_ui);
	PropertyLeaseBean result = propertyService.acceptLease(lease_ui,
		extensions);
	Assert.assertNotNull(result);
	Assert.assertEquals(new Byte((byte) 1), result.getAccepted());
    }

    @Test
    public void testSavePurchase() throws Exception {
	Property property = new Property();
	property.setName("dummy property");
	property.setId(1L);
	PropertyPurchase entity = new PropertyPurchase();
	entity.setId(1L);
	entity.setPrice(new BigDecimal(30000.2));
	PropertyPurchaseBean purchase_ui = new PropertyPurchaseBean();
	purchase_ui.setId(1L);
	purchase_ui.setPrice(new BigDecimal(30000.2));
	PropertyPurchase entity_ = new PropertyPurchase();
	entity_.setId(1L);
	entity_.setPrice(new BigDecimal(30000.2));
	purchase_ui.setPropertyId(property.getId());
	Mockito.when(repository.findOne(1L)).thenReturn(property);
	Mockito.when(purchaseRepository.findOne(1L)).thenReturn(entity);
	Mockito.when(
		purchaseMapper
			.propertyPurchaseBeanToPropertyPurchase(purchase_ui))
		.thenReturn(entity_);
	List<PurchaseExtensionBean> extensions = new ArrayList<PurchaseExtensionBean>();
	PurchaseExtensionBean extension = new PurchaseExtensionBean();
	extension.setExtensionDays(20);
	extension.setPropertyPurchaseId(2L);
	extensions.add(extension);
	List<PurchaseExtension> extensionsEntity = new ArrayList<PurchaseExtension>();
	PurchaseExtension extensionEntity = new PurchaseExtension();
	extensionEntity.setExtensionDays(20);
	extensionEntity.setPropertyPurchaseId(entity);
	extensionsEntity.add(extensionEntity);
	Mockito.when(
		purchaseExtensionMapper
			.purchaseExtensionBeansToPurchaseExtensions(extensions))
		.thenReturn(extensionsEntity);
	Mockito.when(purchaseRepository.rejectPurchase(1L)).thenReturn(1);
	entity.setAccepted((byte) 1);
	Mockito.when(purchaseRepository.save(entity)).thenReturn(entity);
	Mockito.when(
		purchaseMapper.propertyPurchaseToPropertyPurchaseBean(entity))
		.thenReturn(purchase_ui);
	PropertyPurchaseBean result = propertyService.acceptPurchase(
		purchase_ui, extensions);
	Assert.assertNotNull(result);
	Assert.assertEquals(new BigDecimal(30000.2), result.getPrice());
    }

    @Test
    public void testAcceptPurchase() throws Exception {
	Property property = new Property();
	property.setName("dummy property");
	property.setId(1L);
	PropertyPurchase entity = new PropertyPurchase();
	entity.setId(1L);
	entity.setPrice(new BigDecimal(30000.2));
	PropertyPurchaseBean purchase_ui = new PropertyPurchaseBean();
	purchase_ui.setId(1L);
	purchase_ui.setPrice(new BigDecimal(30000.2));
	PropertyPurchase entity_ = new PropertyPurchase();
	entity_.setId(1L);
	entity_.setPrice(new BigDecimal(30000.2));
	purchase_ui.setPropertyId(property.getId());
	Mockito.when(repository.findOne(1L)).thenReturn(property);
	Mockito.when(purchaseRepository.findOne(1L)).thenReturn(entity);
	Mockito.when(
		purchaseMapper
			.propertyPurchaseBeanToPropertyPurchase(purchase_ui))
		.thenReturn(entity_);
	List<PurchaseExtensionBean> extensions = new ArrayList<PurchaseExtensionBean>();
	PurchaseExtensionBean extension = new PurchaseExtensionBean();
	extension.setExtensionDays(20);
	extension.setPropertyPurchaseId(2L);
	extensions.add(extension);
	List<PurchaseExtension> extensionsEntity = new ArrayList<PurchaseExtension>();
	PurchaseExtension extensionEntity = new PurchaseExtension();
	extensionEntity.setExtensionDays(20);
	extensionEntity.setPropertyPurchaseId(entity);
	extensionsEntity.add(extensionEntity);
	Mockito.when(
		purchaseExtensionMapper
			.purchaseExtensionBeansToPurchaseExtensions(extensions))
		.thenReturn(extensionsEntity);
	Mockito.when(purchaseRepository.rejectPurchase(1L)).thenReturn(1);
	entity.setAccepted((byte) 1);
	Mockito.when(purchaseRepository.save(entity)).thenReturn(entity);
	purchase_ui.setAccepted((byte) 1);
	Mockito.when(
		purchaseMapper.propertyPurchaseToPropertyPurchaseBean(entity))
		.thenReturn(purchase_ui);
	PropertyPurchaseBean result = propertyService.acceptPurchase(
		purchase_ui, extensions);
	Assert.assertNotNull(result);
	Assert.assertEquals(new Byte((byte) 1), result.getAccepted());
    }
}
