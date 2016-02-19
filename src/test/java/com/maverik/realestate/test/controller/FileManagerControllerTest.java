/**
 * 
 */
package com.maverik.realestate.test.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.maverik.realestate.service.PropertyManagementService;
import com.maverik.realestate.test.MockSecurityContext;
import com.maverik.realestate.view.bean.ActiveUser;
import com.maverik.realestate.view.bean.PropertyBean;
import com.maverik.realestate.view.bean.PropertyContractBean;
import com.maverik.realestate.view.bean.PropertyContractViewBean;

/**
 * @author jorge
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml",
	"classpath:spring-security.xml" })
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileManagerControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private UserDetailsService userDetailsService;

    private MockMvc mockMvc;

    private static MockHttpSession session;

    @Autowired
    private PropertyManagementService propertyService;

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(FileManagerControllerTest.class);

    private static PropertyBean property;

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
	LOGGER.info("Setting dummy data");
	PropertyBean p = new PropertyBean();
	p.setName("Dummy property File Controller Test");
	p.setAddress("dummy");
	property = propertyService.insertProperty(p);
	LOGGER.info("Inserting contract");
	PropertyContractBean contract = new PropertyContractBean();
	contract.setPropertyId(property.getId());
	property.setContractType(contract);
	property = propertyService.createContractAndUpdateProperty(property);
	LOGGER.info("Inserting permitting");
	PropertyContractViewBean wrapper = new PropertyContractViewBean();
	wrapper.setProperty(property);
	wrapper.setPropertyContract(contract);
	property = propertyService
		.createLandPermittingAndUpdateProperty(wrapper);
	Assert.assertNotNull(property);
    }

    @Test
    public void testZDeleteDummyData() throws Exception {
	LOGGER.info("Deleting dummy data");
	propertyService.deleteProperty(property);
	Assert.assertNull(propertyService.findByProperty(property.getId()));
    }

    @Test
    public void testUploadPropertyFile() throws Exception {
	BufferedInputStream input = new BufferedInputStream(
		new FileInputStream("testUploadFile.txt"));
	MockMultipartFile file = new MockMultipartFile("file",
		"testUploadFile.txt", "text/plain", input);
	mockMvc.perform(
		MockMvcRequestBuilders
			.fileUpload(
				"/filemanager/property/picture/"
					+ property.getId() + "/upload")
			.file(file).session(session))
		.andExpect(status().isOk());
    }

    @Test
    public void testUploadPropertyLOI() throws Exception {
	BufferedInputStream input = new BufferedInputStream(
		new FileInputStream("testUploadFile.txt"));
	MockMultipartFile file = new MockMultipartFile("file",
		"testUploadFile.txt", "text/plain", input);
	mockMvc.perform(
		MockMvcRequestBuilders
			.fileUpload(
				"/filemanager/property/" + property.getId()
					+ "/loi/file/upload").file(file)
			.session(session)).andExpect(status().isOk());
    }

    @Test
    public void testUploadPropertyLease() throws Exception {
	BufferedInputStream input = new BufferedInputStream(
		new FileInputStream("testUploadFile.txt"));
	MockMultipartFile file = new MockMultipartFile("file",
		"testUploadFile.txt", "text/plain", input);
	mockMvc.perform(
		MockMvcRequestBuilders
			.fileUpload(
				"/filemanager/property/" + property.getId()
					+ "/lease/file/upload").file(file)
			.session(session)).andExpect(status().isOk());
    }

    @Test
    public void testUploadPropertyPurchase() throws Exception {
	BufferedInputStream input = new BufferedInputStream(
		new FileInputStream("testUploadFile.txt"));
	MockMultipartFile file = new MockMultipartFile("file",
		"testUploadFile.txt", "text/plain", input);
	mockMvc.perform(
		MockMvcRequestBuilders
			.fileUpload(
				"/filemanager/property/" + property.getId()
					+ "/purchase/file/upload").file(file)
			.session(session)).andExpect(status().isOk());
    }

    @Test
    public void testUploadContractRecsoFile() throws Exception {
	BufferedInputStream input = new BufferedInputStream(
		new FileInputStream("testUploadFile.txt"));
	MockMultipartFile file = new MockMultipartFile("file",
		"testUploadFile.txt", "text/plain", input);
	mockMvc.perform(
		MockMvcRequestBuilders
			.fileUpload(
				"/filemanager/property/recso/"
					+ property.getContractType().getId()
					+ "/upload").file(file)
			.session(session)).andExpect(status().isOk());
    }

    @Test
    public void testUploadContractTittleComm() throws Exception {
	BufferedInputStream input = new BufferedInputStream(
		new FileInputStream("testUploadFile.txt"));
	MockMultipartFile file = new MockMultipartFile("file",
		"testUploadFile.txt", "text/plain", input);
	mockMvc.perform(
		MockMvcRequestBuilders
			.fileUpload(
				"/filemanager/property/titleCommitment/"
					+ property.getContractType().getId()
					+ "/upload").file(file)
			.session(session)).andExpect(status().isOk());
    }

    @Test
    public void testUploadContractTittlePolicy() throws Exception {
	BufferedInputStream input = new BufferedInputStream(
		new FileInputStream("testUploadFile.txt"));
	MockMultipartFile file = new MockMultipartFile("file",
		"testUploadFile.txt", "text/plain", input);
	mockMvc.perform(
		MockMvcRequestBuilders
			.fileUpload(
				"/filemanager/property/titlePolicy/"
					+ property.getContractType().getId()
					+ "/upload").file(file)
			.session(session)).andExpect(status().isOk());
    }

    @Test
    public void testUploadContractSettlement() throws Exception {
	BufferedInputStream input = new BufferedInputStream(
		new FileInputStream("testUploadFile.txt"));
	MockMultipartFile file = new MockMultipartFile("file",
		"testUploadFile.txt", "text/plain", input);
	mockMvc.perform(
		MockMvcRequestBuilders
			.fileUpload(
				"/filemanager/property/settlement/"
					+ property.getContractType().getId()
					+ "/upload").file(file)
			.session(session)).andExpect(status().isOk());
    }

    @Test
    public void testUploadPermittingCommitment() throws Exception {
	LOGGER.info("Test permitting Upload Commitment file");
	LOGGER.info("Property id " + property.getId());
	LOGGER.info("Permitting id " + property.getPermitting().getId());
	BufferedInputStream input = new BufferedInputStream(
		new FileInputStream("testUploadFile.txt"));
	MockMultipartFile file = new MockMultipartFile("file",
		"testUploadFile.txt", "text/plain", input);
	LOGGER.info("File to be upload type " + file.getContentType());
	LOGGER.info("File to be upload name " + file.getOriginalFilename());
	LOGGER.info("File to be upload size " + file.getSize());
	mockMvc.perform(
		MockMvcRequestBuilders
			.fileUpload(
				"/filemanager/permitting/commitment/"
					+ property.getPermitting().getId()
					+ "/upload").file(file)
			.session(session)).andExpect(status().isOk());
    }

    @Test
    public void testUploadPermittingException() throws Exception {
	BufferedInputStream input = new BufferedInputStream(
		new FileInputStream("testUploadFile.txt"));
	MockMultipartFile file = new MockMultipartFile("file",
		"testUploadFile.txt", "text/plain", input);
	mockMvc.perform(
		MockMvcRequestBuilders
			.fileUpload(
				"/filemanager/permitting/exception/"
					+ property.getPermitting().getId()
					+ "/upload").file(file)
			.session(session)).andExpect(status().isOk());
    }

    @Test
    public void testUploadPermittingSurvey() throws Exception {
	BufferedInputStream input = new BufferedInputStream(
		new FileInputStream("testUploadFile.txt"));
	MockMultipartFile file = new MockMultipartFile("file",
		"testUploadFile.txt", "text/plain", input);
	mockMvc.perform(
		MockMvcRequestBuilders
			.fileUpload(
				"/filemanager/permitting/survey/"
					+ property.getPermitting().getId()
					+ "/upload").file(file)
			.session(session)).andExpect(status().isOk());
    }

    @Test
    public void testUploadPermittingSitePlan() throws Exception {
	BufferedInputStream input = new BufferedInputStream(
		new FileInputStream("testUploadFile.txt"));
	MockMultipartFile file = new MockMultipartFile("file",
		"testUploadFile.txt", "text/plain", input);
	mockMvc.perform(
		MockMvcRequestBuilders
			.fileUpload(
				"/filemanager/permitting/sitePlan/"
					+ property.getPermitting().getId()
					+ "/upload").file(file)
			.session(session)).andExpect(status().isOk());
    }

    @Test
    public void testUploadPermittingGeotechnical() throws Exception {
	BufferedInputStream input = new BufferedInputStream(
		new FileInputStream("testUploadFile.txt"));
	MockMultipartFile file = new MockMultipartFile("file",
		"testUploadFile.txt", "text/plain", input);
	mockMvc.perform(
		MockMvcRequestBuilders
			.fileUpload(
				"/filemanager/permitting/geotechnical/"
					+ property.getPermitting().getId()
					+ "/upload").file(file)
			.session(session)).andExpect(status().isOk());
    }

    @Test
    public void testUploadPermittingTraffic() throws Exception {
	BufferedInputStream input = new BufferedInputStream(
		new FileInputStream("testUploadFile.txt"));
	MockMultipartFile file = new MockMultipartFile("file",
		"testUploadFile.txt", "text/plain", input);
	mockMvc.perform(
		MockMvcRequestBuilders
			.fileUpload(
				"/filemanager/permitting/traffic/"
					+ property.getPermitting().getId()
					+ "/upload").file(file)
			.session(session)).andExpect(status().isOk());
    }
}
