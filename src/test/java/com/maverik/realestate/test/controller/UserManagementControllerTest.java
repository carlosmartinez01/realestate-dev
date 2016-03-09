/**
 * 
 */
package com.maverik.realestate.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.maverik.realestate.domain.entity.Company;
import com.maverik.realestate.domain.entity.User;
import com.maverik.realestate.mapper.CompanyMapper;
import com.maverik.realestate.mapper.UserMapper;
import com.maverik.realestate.repository.CompanyRepository;
import com.maverik.realestate.repository.UserRepository;
import com.maverik.realestate.service.UserManagementService;
import com.maverik.realestate.test.MockSecurityContext;
import com.maverik.realestate.view.bean.ActiveUser;
import com.maverik.realestate.view.bean.CompanyBean;
import com.maverik.realestate.view.bean.UserBean;

/**
 * @author jorge
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml",
	"classpath:spring-security.xml" })
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserManagementControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserManagementService userService;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private UserMapper userMapper;

    private MockMvc mockMvc;

    private static MockHttpSession session;

    private static UserBean user;

    private static CompanyBean company;

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
    public void testAInsertDummyData() throws Exception {
	User u = new User();
	u.setFirstName("Dummy");
	u.setLastName("dummy");
	u.setUsername("dummy");
	u.setEmail("jorgem@maverik.com");
	u.setActive((byte) 0);
	u.setPassword("123");

	u = userRepository.save(u);
	user = userMapper.userToUserBean(u);
	Assert.assertNotNull(user);

	Company c = new Company();
	c.setActive(true);
	c.setCompanyName("Dummy");
	c = companyRepository.save(c);
	company = companyMapper.companyToCompanyBean(c);
	Assert.assertNotNull(company);
    }

    @Test
    public void testZDeleteDummy() throws Exception {
	User user = userRepository.findByUsername("dummy");
	userRepository.delete(user);
	companyRepository.delete(company.getId());
    }

    @Test
    public void testGetCurrentUser() throws Exception {
	mockMvc.perform(get("/profile").session(session))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("userDetailsSession"))
		.andExpect(view().name("/secured/userProfileMgmt"));
    }

    @Test
    public void testBUpdateUserProfile() throws Exception {
	Map<String, Object> userDetails = new HashMap<String, Object>();
	userDetails.put("active", user.getActive());
	userDetails.put("password", user.getPassword());
	mockMvc.perform(
		post("/profile/updateProfile").session(session)
			.flashAttr("userProfileView", user)
			.sessionAttr("userDetailsSession", userDetails)
			.param("userId", "" + user.getId()))
		.andExpect(status().isOk())
		.andExpect(view().name("/secured/userProfileMgmt"));
    }

    @Test
    public void testAddUserToCompany() throws Exception {
	user.setCompany(company);
	mockMvc.perform(
		post("/profile/addUserToCompany").session(session)
			.flashAttr("userProfileView", user)
			.param("companyId", "" + company.getId())
			.param("role", "ROLE_USER")).andExpect(status().isOk())
		.andExpect(view().name("/secured/userProfileMgmt"));
    }

    @Test
    public void testGetAllUsersProfiles() throws Exception {
	mockMvc.perform(get("/profile/all").session(session)).andExpect(
		view().name("/secured/usersProfile"));
    }

    @Test
    public void testGetUserInfoDetails() throws Exception {
	mockMvc.perform(
		get("/profile/" + user.getId() + "/details").session(session))
		.andExpect(view().name("/secured/viewProfileDetails"));
    }

    @Test
    public void testEditUserProfile() throws Exception {
	mockMvc.perform(
		post("/profile/Update/addOrUpdate").session(session)
			.flashAttr("userView", user).param("role", "ROLE_USER"))
		.andExpect(status().isOk())
		.andExpect(view().name("/secured/viewProfileDetails"));
    }

    @Test
    public void testChangePassword() throws Exception {
	MvcResult result = mockMvc.perform(
		post("/profile/" + user.getId() + "/changePassword").session(
			session).param("r_password", "mySecretPassword"))
		.andReturn();
	Assert.assertEquals("Update successful", result.getResponse()
		.getContentAsString());
    }

    @Test
    public void testDeleteUser() throws Exception {
	mockMvc.perform(
		get(
			"/profile/" + user.getId() + "/" + user.getUsername()
				+ "/delete").session(session)).andExpect(
		redirectedUrl("/profile/all"));
    }
}
