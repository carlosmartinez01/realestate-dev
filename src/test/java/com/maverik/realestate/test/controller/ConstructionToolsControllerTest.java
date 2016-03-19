/**
 * 
 */
package com.maverik.realestate.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maverik.realestate.domain.entity.Project;
import com.maverik.realestate.domain.entity.ProjectASI;
import com.maverik.realestate.domain.entity.ProjectRFI;
import com.maverik.realestate.domain.entity.Property;
import com.maverik.realestate.repository.PropertyRepository;
import com.maverik.realestate.test.MockSecurityContext;
import com.maverik.realestate.view.bean.ActiveUser;
import com.maverik.realestate.view.bean.ProjectASIBean;
import com.maverik.realestate.view.bean.ProjectBean;
import com.maverik.realestate.view.bean.ProjectRFIBean;

/**
 * @author jorge
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml",
	"classpath:spring-security.xml" })
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConstructionToolsControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PropertyRepository propertyRepository;

    private MockMvc mockMvc;

    private static MockHttpSession session;

    private static Property propertyEntity;

    private static Project projectEntity;

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

    @Test
    public void testGetAllProjectsByProperty() throws Exception {
	MvcResult result = mockMvc.perform(
		get("/tools/" + propertyEntity.getId() + "/get-projects")
			.session(session)).andReturn();
	Type listType = new TypeToken<ArrayList<ProjectBean>>() {
	}.getType();
	List<ProjectBean> projects = new Gson().fromJson(result.getResponse()
		.getContentAsString(), listType);
	Assert.assertNotNull(projects);
	Assert.assertEquals(2, projects.size());
    }

    @Test
    public void testGetRFIByProperty() throws Exception {
	mockMvc.perform(
		post("/tools/" + propertyEntity.getId() + "/rfi").session(
			session)).andExpect(status().isOk())
		.andExpect(view().name("/secured/construction-rfi"))
		.andExpect(model().attributeExists("rfiLst"));
    }

    @Test
    public void testGetASIByProperty() throws Exception {
	mockMvc.perform(
		post("/tools/" + propertyEntity.getId() + "/asi").session(
			session)).andExpect(status().isOk())
		.andExpect(view().name("/secured/construction-asi"))
		.andExpect(model().attributeExists("asiLst"));
    }

    @Test
    public void testSaveRFI() throws Exception {
	ProjectRFIBean bean = new ProjectRFIBean();
	bean.setDiscipline("discliple");
	bean.setQuestion("question here --------------------------------------------");
	bean.setProject(projectEntity.getId());
	mockMvc.perform(
		post("/tools/rfi/save").session(session).flashAttr("rfiForm",
			bean)).andExpect(status().isOk())
		.andExpect(view().name("/secured/construction-rfi"))
		.andExpect(model().attributeExists("rfiForm"));
    }

    @Test
    public void testSaveASI() throws Exception {
	ProjectASIBean bean = new ProjectASIBean();
	bean.setDescription("description");
	bean.setNotes("notes here --------------------------------------------");
	bean.setProject(projectEntity.getId());
	mockMvc.perform(
		post("/tools/asi/save").session(session).flashAttr("asiForm",
			bean)).andExpect(status().isOk())
		.andExpect(view().name("/secured/construction-asi"))
		.andExpect(model().attributeExists("asiForm"));
    }

    @Test
    public void testUploadRFI() throws Exception {
	BufferedInputStream input = new BufferedInputStream(
		new FileInputStream("testUploadFile.txt"));
	MockMultipartFile file = new MockMultipartFile("file",
		"testUploadFile.txt", "text/plain", input);
	mockMvc.perform(
		MockMvcRequestBuilders
			.fileUpload(
				"/filemanager/rfi/" + projectEntity.getId()
					+ "/file/upload").file(file)
			.param("rfiId", "").session(session)).andExpect(
		status().isOk());
    }

    @Test
    public void testUploadASI() throws Exception {
	BufferedInputStream input = new BufferedInputStream(
		new FileInputStream("testUploadFile.txt"));
	MockMultipartFile file = new MockMultipartFile("file",
		"testUploadFile.txt", "text/plain", input);
	mockMvc.perform(
		MockMvcRequestBuilders
			.fileUpload(
				"/filemanager/asi/" + projectEntity.getId()
					+ "/file/upload").file(file)
			.param("asiId", "").session(session)).andExpect(
		status().isOk());
    }

    @Test
    public void testAAAInsertData() throws Exception {
	Property property = new Property();
	property.setName("Dummy property Construction tools");
	property.setCity("SLC");
	property.setAddress("some address");
	Project projectA = new Project();
	projectA.setProjectName("Proj Dummy 01");
	projectA.setProperty(property);
	property.addProject(projectA);
	Project projectB = new Project();
	projectB.setProjectName("Proj Dummy 02");
	projectB.setProperty(property);
	property.addProject(projectB);
	ProjectRFI rfiA = new ProjectRFI();
	rfiA.setSubject("subject A");
	rfiA.setDateRequiredBy(new Date());
	rfiA.setProject(projectA);
	ProjectRFI rfiB = new ProjectRFI();
	rfiB.setSubject("subject B");
	rfiB.setDateRequiredBy(new Date());
	rfiB.setProject(projectA);
	projectA.addProjectRFI(rfiA);
	projectA.addProjectRFI(rfiB);
	ProjectRFI rfiC = new ProjectRFI();
	rfiC.setSubject("subject C");
	rfiC.setDateRequiredBy(new Date());
	rfiC.setProject(projectB);
	projectB.addProjectRFI(rfiC);
	ProjectASI asiA = new ProjectASI();
	ProjectASI asiB = new ProjectASI();
	ProjectASI asiC = new ProjectASI();
	asiA.setDescription("Description A");
	asiB.setDescription("description B");
	asiC.setDescription("description C");
	asiA.setProject(projectB);
	asiB.setProject(projectB);
	asiC.setProject(projectB);
	projectB.addProjectASI(asiA);
	projectB.addProjectASI(asiB);
	projectB.addProjectASI(asiC);
	projectEntity = projectA;
	propertyEntity = propertyRepository.save(property);
    }

    @Test
    public void testZZZDeleteData() throws Exception {
	propertyRepository.delete(propertyEntity);
    }
}
