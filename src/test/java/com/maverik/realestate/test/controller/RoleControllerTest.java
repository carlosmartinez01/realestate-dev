/**
 * 
 */
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

import com.maverik.realestate.service.RoleManagementService;
import com.maverik.realestate.test.MockSecurityContext;
import com.maverik.realestate.view.bean.ActiveUser;
import com.maverik.realestate.view.bean.RoleBean;

/**
 * @author jorge
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml",
	"classpath:spring-security.xml" })
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RoleManagementService roleService;

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
    public void testGetRoles() throws Exception {
	mockMvc.perform(get("/role").session(session))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("lstRoles"))
		.andExpect(view().name("/secured/roleMgmt"));
    }

    @Test
    public void testAAddRoleBefore() throws Exception {
	mockMvc.perform(get("/role/add").session(session))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("roleView"))
		.andExpect(view().name("/secured/viewRoleDetails"));
    }

    @Test
    public void testBAddRole() throws Exception {
	RoleBean r = new RoleBean();
	r.setRoleName("dummy role");
	mockMvc.perform(
		post("/role/add/addOrUpdate").session(session)
			.flashAttr("roleView", r).param("roleId", "1"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("messageForm"))
		.andExpect(view().name("/secured/viewRoleDetails"));
    }

    @Test
    public void testCUpdateRoleBefore() throws Exception {
	RoleBean r = roleService.findByRoleName("dummy role");
	Assert.assertNotNull(r);
	mockMvc.perform(get("/role/" + r.getId() + "/update").session(session))
		.andExpect(status().isOk())
		.andExpect(model().attribute("roleOID", r.getId()))
		.andExpect(view().name("/secured/viewRoleDetails"));
    }

    @Test
    public void testDUpdateRole() throws Exception {
	RoleBean r = roleService.findByRoleName("dummy role");
	Assert.assertNotNull(r);
	r.setDescription("dummy description");
	mockMvc.perform(
		post("/role/update/addOrUpdate").session(session)
			.flashAttr("roleView", r)
			.param("roleId", r.getId() + ""))
		.andExpect(status().isOk())
		.andExpect(
			model().attribute("messageForm",
				"Update Role has been successful"))
		.andExpect(view().name("/secured/viewRoleDetails"));
    }

    @Test
    public void testEDeleteRole() throws Exception {
	RoleBean r = roleService.findByRoleName("dummy role");
	Assert.assertNotNull(r);
	mockMvc.perform(get("/role/" + r.getId() + "/delete").session(session))
		.andExpect(
			flash().attribute("messageForm",
				"Role has been deleted successful"))
		.andExpect(redirectedUrl("/role"));
    }

}
