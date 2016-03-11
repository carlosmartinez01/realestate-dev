/**
 * 
 */
package com.maverik.realestate.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.maverik.realestate.domain.entity.Project;
import com.maverik.realestate.domain.entity.ProjectManagement;
import com.maverik.realestate.mapper.ProjectManagementMapper;
import com.maverik.realestate.repository.ProjectManagementRepository;
import com.maverik.realestate.service.ProjectManagementService;
import com.maverik.realestate.service.ProjectManagementServiceImpl;
import com.maverik.realestate.view.bean.ProjectManagementBean;

/**
 * @author jorge
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceMockTest {

    @InjectMocks
    ProjectManagementService projectService = new ProjectManagementServiceImpl();

    @Mock
    ProjectManagementRepository managementRepository;

    @Mock
    ProjectManagementMapper managementMapper;

    @Test
    public void testGetProjectManagement() throws Exception {
	// given
	Project mockProject = new Project();
	mockProject.setId(1L);
	ProjectManagement mockEntity = new ProjectManagement();
	mockEntity.setId(1L);
	ProjectManagementBean mockBean = new ProjectManagementBean();
	mockBean.setId(mockEntity.getId());
	// when
	Mockito.when(managementRepository.findByProject(mockProject))
		.thenReturn(mockEntity);
	Mockito.when(managementMapper.entityToBean(mockEntity)).thenReturn(
		mockBean);
	// then
	ProjectManagementBean bean = new ProjectManagementBean();
	bean.setId(1L);
	ProjectManagementBean management = projectService
		.getProjectManagement(bean.getId());
	Assert.assertNotNull(management);
	Assert.assertEquals(1L, management.getId().longValue());
    }
}
