package com.maverik.realestate.service.test;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.maverik.realestate.domain.entity.Role;
import com.maverik.realestate.exception.DBException;
import com.maverik.realestate.mapper.RoleMapper;
import com.maverik.realestate.repository.RoleRepository;
import com.maverik.realestate.service.RoleManagementService;
import com.maverik.realestate.service.RoleManagementServiceImpl;
import com.maverik.realestate.view.bean.RoleBean;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleServiceUnitTest {

    @InjectMocks
    private RoleManagementService roleManagementService = new RoleManagementServiceImpl();

    @Mock
    private RoleRepository roleRepositoryMock;

    @Mock
    private RoleMapper roleMapperMock;

    @Test
    public void testInsertRole() throws DBException {
	RoleBean roleBean = new RoleBean();
	roleBean.setRoleName("dummy role");
	Role role = new Role();
	role.setRoleName(roleBean.getRoleName());
	Role newRole = role;
	newRole.setId(1L);
	Mockito.when(roleRepositoryMock.save(role)).thenReturn(newRole);
	Mockito.when(roleMapperMock.roleBeanToRole(roleBean)).thenReturn(role);
	RoleBean r = roleManagementService.insertRole(roleBean);
	Assert.assertNotNull(r.getId());
	Assert.assertEquals(1L, r.getId().longValue());
    }

    @Test
    public void testUpdateRole() throws DBException {
	RoleBean roleBean = new RoleBean();
	roleBean.setRoleName("dummy role");
	Role role = new Role();
	role.setRoleName(roleBean.getRoleName());
	Role newRole = role;
	newRole.setId(1L);
	Mockito.when(roleRepositoryMock.save(role)).thenReturn(newRole);
	Mockito.when(roleMapperMock.roleBeanToRole(roleBean)).thenReturn(role);
	RoleBean r = roleManagementService.updateRole(roleBean);
	Assert.assertNotNull(r.getId());
	Assert.assertEquals(1L, r.getId().longValue());
    }

    @Test
    public void testFindRole() throws DBException {
	Role role = new Role();
	role.setRoleName("dummy role");
	RoleBean roleBean = new RoleBean();
	roleBean.setRoleName(role.getRoleName());
	Mockito.when(roleRepositoryMock.findOne(1L)).thenReturn(role);
	Mockito.when(roleMapperMock.roleToRoleBean(role)).thenReturn(roleBean);
	RoleBean r = roleManagementService.findByRole(1L);
	Assert.assertNotNull(r);
	Assert.assertEquals("dummy role", r.getRoleName());
    }

    @Test
    public void testAMockInstances() {
	Assert.assertNotNull(roleRepositoryMock);
	Assert.assertNotNull(roleMapperMock);
    }
}
