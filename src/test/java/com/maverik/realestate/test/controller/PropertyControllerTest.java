/**
 * Property Controller integration test
 */
package com.maverik.realestate.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.maverik.realestate.domain.entity.Property;
import com.maverik.realestate.domain.entity.PropertyContract;
import com.maverik.realestate.mapper.PropertyMapper;
import com.maverik.realestate.repository.PropertyRepository;
import com.maverik.realestate.service.PropertyManagementService;
import com.maverik.realestate.test.MockSecurityContext;
import com.maverik.realestate.view.bean.ActiveUser;
import com.maverik.realestate.view.bean.PermittingMeetingsViewBean;
import com.maverik.realestate.view.bean.PropertyBean;
import com.maverik.realestate.view.bean.PropertyContractBean;
import com.maverik.realestate.view.bean.PropertyContractViewBean;
import com.maverik.realestate.view.bean.PropertyMeetingBean;

/**
 * @author jorge
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml",
	"classpath:spring-security.xml" })
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PropertyControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PropertyManagementService propertyService;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyMapper propertyMapper;

    private static PropertyBean propertyBean;

    private MockMvc mockMvc;

    private static MockHttpSession session;

    @Before
    public void setUp() throws Exception {
	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
		.addFilter(springSecurityFilterChain).build();
	UserDetails ud = userDetailsService.loadUserByUsername("dummy");
	ActiveUser activeUser = new ActiveUser(ud.getUsername(),
		ud.getPassword(), true, true, true, true, ud.getAuthorities());
	Authentication auth = new UsernamePasswordAuthenticationToken(
		activeUser, activeUser.getUsername(),
		activeUser.getAuthorities());
	session = new MockHttpSession();
	MockSecurityContext msc = new MockSecurityContext(auth);
	session.setAttribute(
		HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
		msc);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAAAInsertDummyData() throws Exception {
	Property p = new Property();
	p.setName("Dummy Controller Test");
	p.setAddress("dummy");
	PropertyContract c = new PropertyContract();
	c.setContractType("Lease");
	c.setPropertyId(p);
	p.setContractType(c);
	p = propertyRepository.save(p);
	propertyBean = propertyMapper.propertyToPropertyBean(p);
	Assert.assertNotNull(propertyBean);
    }

    @Test
    public void testZZZDeleteDummyData() throws Exception {
	propertyService.deleteProperty(propertyBean);
	Assert.assertNull(propertyService.findByProperty(propertyBean.getId()));
    }

    @Test
    public void testGetProperties() throws Exception {
	mockMvc.perform(get("/property").session(session))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("lstProperties"))
		.andExpect(view().name("/secured/propertyMgmt"));
    }

    @Test
    public void testGetAllResearchProperties() throws Exception {
	mockMvc.perform(get("/properties/all").session(session))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("newProperties"))
		.andExpect(view().name("/secured/viewAllProjects"));
    }

    @Test
    public void testAAddPropertyBefore() throws Exception {
	mockMvc.perform(get("/property/add").session(session))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("propertyView"))
		.andExpect(view().name("/secured/viewPropertyDetails"));
    }

    @Test
    public void testBAddProperty() throws Exception {
	PropertyBean p = new PropertyBean();
	p.setName("Dummy property");
	p.setAddress("Address field");
	mockMvc.perform(
		post("/property/add/addOrUpdate").session(session)
			.flashAttr("propertyView", p).param("propertyId", "1"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("messageForm"))
		.andExpect(view().name("/secured/viewPropertyDetails"));
    }

    @Test
    public void testCUpdateBeforeProperty() throws Exception {
	PropertyBean p = propertyService.findByPropertyName("Dummy property");
	Assert.assertNotNull(p);
	mockMvc.perform(
		get("/property/" + p.getId() + "/update").session(session))
		.andExpect(status().isOk())
		.andExpect(model().attribute("propertyOID", p.getId()))
		.andExpect(view().name("/secured/viewPropertyDetails"));
    }

    @Test
    public void testCUpdateProperty() throws Exception {
	PropertyBean p = propertyService.findByPropertyName("Dummy property");
	Assert.assertNotNull(p);
	p.setName("Dummy property");
	p.setAddress("Updated Address field");
	mockMvc.perform(
		post("/property/update/addOrUpdate").session(session)
			.flashAttr("propertyView", p)
			.param("propertyId", p.getId() + ""))
		.andExpect(status().isOk())
		.andExpect(
			model().attribute("messageForm",
				"Update property has been successful"))
		.andExpect(view().name("/secured/viewPropertyDetails"));
    }

    @Test
    public void testEShowPropertySummary() throws Exception {
	PropertyBean p = propertyService.findByPropertyName("Dummy property");
	Assert.assertNotNull(p);
	mockMvc.perform(
		get("/properties/" + p.getId() + "/summary").session(session))
		.andExpect(status().isOk())
		.andExpect(model().attribute("PROPERTY_SUMMARY", p))
		.andExpect(view().name("/secured/viewPropertySummary"));
    }

    @Test
    public void testFShowLease() throws Exception {
	PropertyBean p = propertyService.findByPropertyName("Dummy property");
	Assert.assertNotNull(p);
	mockMvc.perform(
		post("/propertyLease/showLease/" + p.getId()).session(session))
		.andExpect(status().isOk());
    }

    public void testGSavePropertyContract() throws Exception {
	PropertyBean p = propertyService.findByPropertyName("Dummy property");
	PropertyContractBean c = new PropertyContractBean();
	c.setEmDeposited("yes");
	c.setEscrowHolder("yes");
	c.setPropertyId(p.getId());
	c.setGeoTechOrdered("yes");
	c.setContractType("lease");
	p.setContractType(c);
	Assert.assertNotNull(p);
	propertyService.createContractAndUpdateProperty(p);
	PropertyContractViewBean propertyContract = new PropertyContractViewBean(
		p, c);
	mockMvc.perform(
		post("/properties/saveOrMoveContract/save").session(session)
			.flashAttr("propertyView", propertyContract)
			.param("propertyId", "" + p.getId())
			.param("currentStatus", "" + 1)).andExpect(
		status().isOk());
    }

    @Test
    public void testDUpdateProperty() throws Exception {
	PropertyBean p = propertyService.findByPropertyName("Dummy property");
	mockMvc.perform(
		post("/properties/addOrUpdateResearch/update/1")
			.session(session).flashAttr("propertyView", p)
			.param("currentStatus", "" + 0)
			.param("propertyId", "" + p.getId()))
		.andExpect(status().isOk())
		.andExpect(view().name("/secured/propertyResearch"));
    }

    @Test
    public void testJShowLOI() throws Exception {
	PropertyBean p = propertyService.findByPropertyName("Dummy property");
	Assert.assertNotNull(p);
	mockMvc.perform(
		post("/propertyLOI/showLOI/" + p.getId()).session(session))
		.andExpect(status().isOk());
    }

    @Test
    public void testZDeleteProperty() throws Exception {
	PropertyBean p = propertyService.findByPropertyName("Dummy property");
	Assert.assertNotNull(p);
	// the delete method from controller just do a logical delete so after
	// that we need to properly remove the entity
	mockMvc.perform(
		get("/property/" + p.getId() + "/delete").session(session))
		.andExpect(
			flash().attribute("messageForm",
				"Property has been deleted successful"))
		.andExpect(redirectedUrl("/property"));
    }

    @Test
    public void testAddPropertyResearchPreview() throws Exception {
	mockMvc.perform(get("/properties/add").session(session))
		.andExpect(status().isOk())
		.andExpect(view().name("/secured/propertyResearch"));
    }

    @Test
    public void testPropertyResearch() throws Exception {
	mockMvc.perform(
		get("/properties/" + propertyBean.getId() + "/research")
			.session(session)).andExpect(status().isOk())
		.andExpect(view().name("/secured/propertyResearch"));
    }

    @Test
    public void testPropertyContract() throws Exception {
	mockMvc.perform(
		get("/properties/" + propertyBean.getId() + "/contract")
			.session(session)).andExpect(status().isOk())
		.andExpect(view().name("/secured/propertyContract"));
    }

    @Test
    public void testPropertySaveContract() throws Exception {
	PropertyContractViewBean propertyContract = new PropertyContractViewBean();
	propertyContract.setProperty(propertyBean);
	propertyContract.setPropertyContract(propertyBean.getContractType());
	mockMvc.perform(
		post(
			"/properties/saveContract/" + propertyBean.getId()
				+ "/" + propertyBean.getContractType().getId())
			.session(session)
			.flashAttr("propertyView", propertyContract)
			.param("currentStatus", "" + 1))
		.andExpect(status().isOk())
		.andExpect(view().name("/secured/propertyContract"));
    }

    @Test
    public void testPropertyMovePhaseContract() throws Exception {
	PropertyContractViewBean propertyContract = new PropertyContractViewBean();
	propertyContract.setProperty(propertyBean);
	propertyContract.setPropertyContract(propertyBean.getContractType());
	mockMvc.perform(
		post(
			"/properties/moveContract/" + propertyBean.getId()
				+ "/" + propertyBean.getContractType().getId())
			.session(session)
			.flashAttr("propertyView", propertyContract)
			.param("currentStatus", "" + 1)).andExpect(
		redirectedUrl("/properties/" + propertyBean.getId()
			+ "/permitting"));
    }

    @Test
    public void testWGetPropertyPermitting() throws Exception {
	mockMvc.perform(
		get("/properties/" + propertyBean.getId() + "/permitting")
			.session(session)).andExpect(status().isOk())
		.andExpect(view().name("/secured/propertyPermitting"));
    }

    @Test
    public void testWSavePropertyPermitting() throws Exception {
	PropertyBean p = propertyService.findByProperty(propertyBean.getId());
	PermittingMeetingsViewBean propertyPermitting = new PermittingMeetingsViewBean();
	List<PropertyMeetingBean> meetings = new ArrayList<PropertyMeetingBean>();
	for (int i = 0; i < 9; i++) {
	    PropertyMeetingBean meeting = new PropertyMeetingBean();
	    meeting.setMeeting("meeting " + i);
	    meetings.add(meeting);
	}
	propertyPermitting.setPermitting(p.getPermitting());
	propertyPermitting.setMeetings(meetings);
	mockMvc.perform(
		post(
			"/properties/savePermitting/" + propertyBean.getId()
				+ "/" + p.getPermitting().getId()).session(
			session)
			.flashAttr("permittingView", propertyPermitting))
		.andExpect(status().isOk())
		.andExpect(view().name("/secured/propertyPermitting"));
    }
}
