package com.maverik.realestate.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

import com.maverik.realestate.service.CompanyManagementService;
import com.maverik.realestate.test.MockSecurityContext;
import com.maverik.realestate.view.bean.ActiveUser;
import com.maverik.realestate.view.bean.CompanyBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml",
	"classpath:spring-security.xml" })
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CompanyControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CompanyManagementService companyService;

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
    public void testGetCompanies() throws Exception {
	mockMvc.perform(get("/company").session(session))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("lstCompany"))
		.andExpect(view().name("/secured/companyMgmt"));
    }

    @Test
    public void testAAddCompanyBefore() throws Exception {
	mockMvc.perform(get("/company/add").session(session))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("companyView"))
		.andExpect(view().name("/secured/viewCompanyDetails"));
    }

    @Test
    public void testBAddCompany() throws Exception {
	CompanyBean c = new CompanyBean();
	c.setCompanyName("Dummy company");
	c.setAddress("Address field");
	c.setCity("SLC");
	c.setState("UT");
	mockMvc.perform(
		post("/company/add/addOrUpdate").session(session)
			.flashAttr("companyView", c).param("companyId", "1"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("messageForm"))
		.andExpect(view().name("/secured/viewCompanyDetails"));
    }

    @Test
    public void testCUpdateCompanyBefore() throws Exception {
	CompanyBean c = companyService.findByCompanyName("Dummy company");
	Assert.assertNotNull(c);
	mockMvc.perform(
		get("/company/" + c.getId() + "/update").session(session))
		.andExpect(status().isOk())
		.andExpect(model().attribute("companyOID", c.getId()))
		.andExpect(view().name("/secured/viewCompanyDetails"));
    }

    @Test
    public void testDUpdateCompany() throws Exception {
	CompanyBean c = companyService.findByCompanyName("Dummy company");
	Assert.assertNotNull(c);
	c.setCompanyName("Dummy company");
	c.setAddress("Updated Address field");
	c.setCity("Houston");
	c.setState("UT");
	mockMvc.perform(
		post("/company/update/addOrUpdate").session(session)
			.flashAttr("companyView", c)
			.param("companyId", c.getId() + ""))
		.andExpect(status().isOk())
		.andExpect(
			model().attribute("messageForm",
				"Update Company has been successful"))
		.andExpect(view().name("/secured/viewCompanyDetails"));
    }

    @Test
    public void testEDeleteCompany() throws Exception {
	CompanyBean c = companyService.findByCompanyName("Dummy company");
	Assert.assertNotNull(c);
	// the delete method from controller just do a logical delete so after
	// that we need to properly remove the entity
	mockMvc.perform(
		get("/company/" + c.getId() + "/delete").session(session))
		.andExpect(
			flash().attribute("messageForm",
				"Company has been deleted successful"))
		.andExpect(redirectedUrl("/company"));

	companyService.deleteCompany(c);
    }
}
