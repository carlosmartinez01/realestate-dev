/**
 * 
 */
package com.maverik.realestate.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.After;
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

import com.maverik.realestate.domain.entity.User;
import com.maverik.realestate.repository.UserRepository;
import com.maverik.realestate.test.MockSecurityContext;
import com.maverik.realestate.view.bean.ActiveUser;

/**
 * @author jorge
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml",
	"classpath:spring-security.xml" })
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ResetPasswordControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

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
	User user = new User();
	user.setFirstName("Dummy");
	user.setLastName("dummy");
	user.setUsername("dummyUsername");
	user.setEmail("jorgem@maverik.com");
	user.setActive((byte) 0);
	user.setPassword("123");

	userRepository.save(user);
    }

    @After
    public void tearDown() throws Exception {
	User user = userRepository.findByUsername("dummyUsername");
	userRepository.delete(user);
    }

    @Test
    public void resetPageTest() throws Exception {
	mockMvc.perform(get("/reset").session(session))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("render"))
		.andExpect(view().name("resetPassword"));
    }

    @Test
    public void resetPasswordTest() throws Exception {
	mockMvc.perform(
		post("/resetUserPassword").session(session).param("username",
			"dummyUsername")).andExpect(status().isOk())
		.andExpect(model().attributeExists("resetPassMsg"))
		.andExpect(view().name("resetPassword"));
    }

}
