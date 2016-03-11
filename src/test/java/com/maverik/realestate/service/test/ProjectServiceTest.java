/**
 * 
 */
package com.maverik.realestate.service.test;

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

import com.maverik.realestate.domain.entity.Property;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.exception.NoRecordFoundException;
import com.maverik.realestate.repository.ProjectRepository;
import com.maverik.realestate.repository.PropertyRepository;
import com.maverik.realestate.service.NoteManagementService;
import com.maverik.realestate.service.ProjectManagementService;
import com.maverik.realestate.view.bean.NoteBean;
import com.maverik.realestate.view.bean.ProjectBean;
import com.maverik.realestate.view.bean.PropertyBean;
import com.maverik.realestate.view.bean.UserBean;

/**
 * @author jorge
 *
 */
@ContextConfiguration(locations = { "classpath:application-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectServiceTest {

    /*
     * 
     * Integration Test for Project Service
     * 
     * CRUD operations
     */

    @Autowired
    private ProjectManagementService projectService;

    private static ProjectBean project;

    @Autowired
    private NoteManagementService noteManagementService;

    @Autowired
    private ProjectRepository projectRepository;

    private static NoteBean projectNote;

    @Autowired
    private PropertyRepository propertyRepository;

    private static Property property;

    @Before
    public void setUp() throws Exception {
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
	propertyRepository.delete(property);
	Assert.assertNull(propertyRepository.findOne(property.getId()));
    }

    @Test
    public void testAInsertProject() throws GenericException {
	ProjectBean p = new ProjectBean();
	p.setProjectName("Dummy project");
	p.setStatus((byte) 1);
	p.setDescription("project description");
	PropertyBean prop = new PropertyBean();
	prop.setId(property.getId());
	p.setProperty(prop);
	Assert.assertNotNull(projectService);
	project = projectService.insertProject(p);
	Assert.assertNotNull(project);
	Assert.assertNotNull(project.getId());
    }

    // @Test(expected = DBException.class)
    // public void testAZInsertDuplicatedProject() throws GenericException {
    // ProjectBean p = new ProjectBean();
    // p.setProjectName("Dummy project");
    // p.setStatus((byte) 1);
    // p.setDescription("project description");
    // PropertyBean prop = new PropertyBean();
    // prop.setId(property.getId());
    // p.setProperty(prop);
    // Assert.assertNotNull(projectService);
    // ProjectBean p1 = projectService.insertProject(p);
    // Assert.assertNull(p1);
    // }

    @Test
    public void testBFindAll() throws GenericException {
	List<ProjectBean> lst = projectService.findAllProjects();
	Assert.assertNotNull(lst);
	Assert.assertNotEquals(0, lst.size());
    }

    @Test
    public void testAInsertProjectNote() throws GenericException {
	UserBean user = new UserBean();
	user.setId(2L);
	user.setUsername("homer");
	projectNote = noteManagementService.insertNoteByProject(
		project.getId(), "Testing notes for property", user, 1L);
	Assert.assertNotNull(projectNote);
	Assert.assertEquals("Testing notes for property",
		projectNote.getNotes());
    }

    @Test
    public void testBFindAllProjectNotes() throws GenericException {
	List<NoteBean> notes = noteManagementService.findNotesByProject(
		project.getId(), 1L);
	Assert.assertNotNull(notes);
	deleteNote(projectNote);
    }

    private void deleteNote(NoteBean note) throws GenericException {
	noteManagementService.deleteNote(note);
    }

    @Test
    public void testCFindProjectByResearchPhase() throws GenericException {
	List<ProjectBean> lst = projectService.findProjectsByResearchPhase();
	Assert.assertNotNull(lst);
	Assert.assertNotEquals(0, lst.size());
    }

    @Test
    public void testDFindAndUpdate() throws GenericException {
	ProjectBean p = projectService.findByProject(project.getId());
	p.setDescription("new project description");
	p = projectService.updateProject(p);
	Assert.assertEquals(project.getId(), p.getId());
    }

    @Test(expected = NoRecordFoundException.class)
    public void testZDeleteProject() throws GenericException {
	projectService.deleteProject(project);
	Assert.assertNull(projectService.findByProject(project.getId()));
    }
}
