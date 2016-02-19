/**
 * 
 */
package com.maverik.realestate.service.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.service.NoteManagementService;
import com.maverik.realestate.service.PropertyManagementService;
import com.maverik.realestate.view.bean.LeaseAndExtensionBean;
import com.maverik.realestate.view.bean.NoteBean;
import com.maverik.realestate.view.bean.PropertyBean;
import com.maverik.realestate.view.bean.PropertyLOIBean;
import com.maverik.realestate.view.bean.PurchaseAndExtensionWrapBean;
import com.maverik.realestate.view.bean.UserBean;

/**
 * @author jorge
 *
 */
@ContextConfiguration(locations = { "classpath:application-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PropertyServiceTest {

    /*
     * 
     * Integration Test for Property Service
     * 
     * CRUD operations
     */

    @Autowired
    private PropertyManagementService propertyService;

    private static PropertyBean property;

    @Autowired
    private NoteManagementService noteManagementService;

    private static NoteBean propertyNote;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAInsertProperty() throws GenericException {
	PropertyBean p = new PropertyBean();
	p.setName("Dummy Property");
	p.setAddress("Dummy address field");
	Assert.assertNotNull(propertyService);
	property = propertyService.insertProperty(p);
	Assert.assertNotNull(property);
	Assert.assertNotNull(property.getId());
    }

    @Test
    public void testBFindAll() throws GenericException {
	List<PropertyBean> lst = propertyService.findAllProperties();
	Assert.assertNotNull(lst);
	Assert.assertNotEquals(0, lst.size());
    }

    private void deleteNote(NoteBean note) throws GenericException {
	noteManagementService.deleteNote(note);
    }

    @Test
    public void testAInsertPropertyNote() throws GenericException {
	UserBean user = new UserBean();
	user.setId(1L);
	user.setUsername("homer");
	propertyNote = noteManagementService.insertNoteByProperty(
		property.getId(), "Testing notes for property", user, 1L);
	Assert.assertNotNull(propertyNote);
	Assert.assertEquals("Testing notes for property",
		propertyNote.getNotes());
    }

    @Test
    public void testBFindAllPropertyNotes() throws GenericException {
	List<NoteBean> notes = noteManagementService.findNotesByProperty(
		property.getId(), 1L);
	Assert.assertNotNull(notes);
	deleteNote(propertyNote);
    }

    @Test
    public void testCFindPropertyByResearchPhase() throws GenericException {
	property.setStatus((byte) 0);
	propertyService.updateProperty(property);
	List<PropertyBean> lst = propertyService
		.findPropertiesByResearchPhase();
	Assert.assertNotNull(lst);
	Assert.assertNotEquals(0, lst.size());
    }

    @Test
    public void testDFindAndUpdate() throws GenericException {
	PropertyBean p = propertyService.findByProperty(property.getId());
	p.setAddress("dummy address udpated");
	p = propertyService.updateProperty(p);
	Assert.assertNotEquals("Dummy address field", p.getAddress());
	Assert.assertEquals(property.getId(), p.getId());
    }

    @Test
    public void testESavePropertyLOI() throws GenericException {
	Integer result = propertyService.saveLOIPrice(1L, new BigDecimal(13));
	Assert.assertNotNull(result);
    }

    @Test
    public void testFFindPropertyLOI() throws GenericException {
	Assert.assertNotNull(propertyService.findLOIByProperty(property.getId()));
    }

    @Test
    public void testGFindLOIAccepted() throws GenericException {
	List<PropertyLOIBean> loiBeans = new ArrayList<PropertyLOIBean>();
	loiBeans = propertyService.findLOIByAcceptance(1L, (byte) 1);
	Assert.assertNotNull(loiBeans);
    }

    @Test
    public void testGFindLOINonAccepted() throws GenericException {
	List<PropertyLOIBean> loiBeans = new ArrayList<PropertyLOIBean>();
	loiBeans = propertyService.findLOIByAcceptance(1L, (byte) 0);
	Assert.assertNotNull(loiBeans);
    }

    @Test
    public void testHAcceptLOI() throws GenericException {
	Integer result = propertyService.acceptLOIPrice(1L, new BigDecimal(1),
		1l);
	result = 1;
	Assert.assertNotNull(result);
    }

    @Test
    public void testIFindAcceptedLease() throws GenericException {
	List<LeaseAndExtensionBean> beans = new ArrayList<LeaseAndExtensionBean>();
	propertyService.findAcceptedLease(1L, 1L);
	Assert.assertNotNull(beans);
    }

    @Test
    public void testIFindNonAcceptedLease() throws GenericException {
	List<LeaseAndExtensionBean> beans = new ArrayList<LeaseAndExtensionBean>();
	propertyService.findNonAcceptedLease(1L, 1L);
	Assert.assertNotNull(beans);
    }

    @Test
    public void testIFindNonAcceptedPurchase() throws GenericException {
	List<PurchaseAndExtensionWrapBean> beans = new ArrayList<PurchaseAndExtensionWrapBean>();
	propertyService.findNonAcceptedPurchase(1L, 1L);
	Assert.assertNotNull(beans);
    }

    @Test
    public void testIFindAcceptedPurchase() throws GenericException {
	List<PurchaseAndExtensionWrapBean> beans = new ArrayList<PurchaseAndExtensionWrapBean>();
	propertyService.findAcceptedPurchase(1L, 1L);
	Assert.assertNotNull(beans);
    }

    @Test
    public void testZDeleteProperty() throws GenericException {
	propertyService.deleteProperty(property);
	Assert.assertNull(propertyService.findByProperty(property.getId()));
    }
}
