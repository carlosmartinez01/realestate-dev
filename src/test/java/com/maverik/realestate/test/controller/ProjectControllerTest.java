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

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import com.maverik.realestate.domain.entity.Project;
import com.maverik.realestate.domain.entity.ProjectManagement;
import com.maverik.realestate.domain.entity.Property;
import com.maverik.realestate.repository.ProjectRepository;
import com.maverik.realestate.repository.PropertyRepository;
import com.maverik.realestate.service.ProjectManagementService;
import com.maverik.realestate.test.MockSecurityContext;
import com.maverik.realestate.view.bean.ActiveUser;
import com.maverik.realestate.view.bean.ProjectBean;
import com.maverik.realestate.view.bean.PropertyBean;

/**
 * @author jorge
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml",
	"classpath:spring-security.xml" })
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ProjectManagementService projectService;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private ProjectRepository projectRepository;

    private MockMvc mockMvc;

    private static MockHttpSession session;

    private static Property property;

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
    public void testAAASetupData() throws Exception {
	Property p = new Property();
	p.setName("Dummy prop project Controller test");
	p.setCity("SLC");
	p.setAddress("some address");
	property = propertyRepository.save(p);
	Assert.assertNotNull(property);
    }

    @Test
    public void testZZZDeleteData() throws Exception {
	property = propertyRepository.findOne(property.getId());
	propertyRepository.delete(property);
	Assert.assertNull(propertyRepository.findOne(property.getId()));
    }

    @Test
    public void testGetProjects() throws Exception {
	mockMvc.perform(get("/project").session(session))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("lstProject"))
		.andExpect(view().name("/secured/projectMgmt"));
    }

    @Test
    public void testAAddProjectBefore() throws Exception {
	mockMvc.perform(get("/project/add").session(session))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("projectView"))
		.andExpect(view().name("/secured/viewProjectDetails"));
    }

    @Test
    public void testBAddProject() throws Exception {
	ProjectBean p = new ProjectBean();
	p.setProjectName("Dummy project");
	p.setProjectPhase("New Store");
	PropertyBean prop = new PropertyBean();
	prop.setId(property.getId());
	p.setProperty(prop);
	mockMvc.perform(
		post("/project/add/addOrUpdate").session(session)
			.flashAttr("projectView", p).param("projectId", "1"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("messageForm"))
		.andExpect(view().name("/secured/viewProjectDetails"));
    }

    @Test
    public void testCUpdateProjectBefore() throws Exception {
	ProjectBean p = projectService.findByProjectName("Dummy project");
	Assert.assertNotNull(p);
	mockMvc.perform(
		get("/project/" + p.getId() + "/update").session(session))
		.andExpect(status().isOk())
		.andExpect(model().attribute("projectOID", p.getId()))
		.andExpect(view().name("/secured/viewProjectDetails"));
    }

    @Test
    public void testDUpdateProject() throws Exception {
	ProjectBean p = projectService.findByProjectName("Dummy project");
	Assert.assertNotNull(p);
	p.setProjectName("Dummy project");
	p.setProjectPhase("New Store");
	mockMvc.perform(
		post("/project/update/addOrUpdate").session(session)
			.flashAttr("projectView", p)
			.param("projectId", p.getId() + ""))
		.andExpect(status().isOk())
		.andExpect(
			model().attribute("messageForm",
				"Update Project has been successful"))
		.andExpect(view().name("/secured/viewProjectDetails"));
    }

    @Test
    public void testEAddProjectModal() throws Exception {
	ProjectBean p = new ProjectBean();
	p.setProjectName("Dummy project");
	p.setProjectPhase("New Store");
	PropertyBean prop = new PropertyBean();
	prop.setId(property.getId());
	p.setProperty(prop);
	String jsonString = "{\"property\":{\"id\":\""
		+ property.getId()
		+ "\"}, \"projectType\":\"Default type\",\"projectName\":\"Default name\",\"status\":0}";
	MvcResult result = mockMvc
		.perform(
			post("/projects/add/" + property.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString).session(session))
		.andExpect(status().isOk()).andReturn();
	Assert.assertEquals(
		"{\"successMessage\":\"Project successfully added\",\"errorMessage\":null}",
		result.getResponse().getContentAsString());
    }

    @Test
    public void testGetProjectManagement() throws Exception {
	ProjectBean projectBean = projectService
		.findByProjectName("Default name");
	setUpProjectManagement(projectBean);
	MvcResult result = mockMvc
		.perform(
			get("/projects/" + projectBean.getId() + "/management")
				.session(session)).andExpect(status().isOk())
		.andReturn();
	Assert.assertNotNull(result.getModelAndView().getModelMap()
		.get("managementForm"));
	Assert.assertEquals("/secured/management", result.getModelAndView()
		.getViewName());
    }

    public void setUpProjectManagement(ProjectBean projectBean)
	    throws Exception {
	Project entity = new Project();
	entity.setProjectName(projectBean.getProjectName());
	entity.setId(projectBean.getId());
	entity.setProjectPhase(projectBean.getProjectPhase());
	entity.setStatus(projectBean.getStatus());
	entity.setProperty(propertyRepository.findOne(projectBean.getProperty()
		.getId()));
	ProjectManagement management = new ProjectManagement();
	management.setApprovedConstructionBudget("yes");
	management.setBudgetDateSent(new Date());
	management.setGeneralContractor("Contractor");
	management.setProject(entity);
	entity.setManagement(management);
	projectRepository.save(entity);
    }

    @Test
    public void testZDeleteProject() throws Exception {
	ProjectBean p = projectService.findByProjectName("Dummy project");
	Assert.assertNotNull(p);
	mockMvc.perform(
		get("/project/" + p.getId() + "/delete").session(session))
		.andExpect(
			flash().attribute("messageForm",
				"Project has been deleted successful"))
		.andExpect(redirectedUrl("/project"));
    }
}
