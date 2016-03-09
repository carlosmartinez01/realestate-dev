/**
 * 
 */
package com.maverik.realestate.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author jorge
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml",
	"classpath:spring-security.xml" })
@WebAppConfiguration
public class ControllerTest {
    
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;
    
    private MockMvc mockMvc;
    
    @Before
    public void setUp() throws Exception {
	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
		.addFilter(springSecurityFilterChain).build();
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testRequiresAuthentication() throws Exception {
	mockMvc.perform(get("/")).andExpect(
		redirectedUrl("http://localhost/login"));
    }
}
