/**
 * 
 */
package com.maverik.realestate.service.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.maverik.realestate.constants.RealEstateConstants.Roles;
import com.maverik.realestate.exception.DBException;
import com.maverik.realestate.service.RoleManagementService;
import com.maverik.realestate.test.Base;
import com.maverik.realestate.view.bean.RoleBean;

/**
 * @author jorge
 *
 */
public class RoleServiceTest extends Base {
    
    @Autowired
    private RoleManagementService roleManagementService;
    
    @Test
    public void testFindAllRoles() throws DBException {
	List<RoleBean> lst = roleManagementService.findAllRoles();
	Assert.assertNotNull(roleManagementService);
	Assert.assertNotNull(lst);
	Assert.assertNotEquals(0, lst.size());
    }
    
    @Test
    public void testFindAllRolesExceptDefaultRole() throws DBException {
	List<RoleBean> lst = roleManagementService.findAllRolesNotDefault(Roles.USER.toString());
	Assert.assertNotNull(roleManagementService);
	Assert.assertNotNull(lst);
	Assert.assertNotEquals(0, lst.size());
    }
    
    @Test
    public void testGetSpecificRole() {
	Set<String> roles = new HashSet<String>();
	roles.add(Roles.USER.toString());
	roles.add(Roles.ADMIN.toString());
	String r = roleManagementService.getUserRoleSelected(roles);
	Assert.assertNotNull(roleManagementService);
	Assert.assertNotNull(r);
	Assert.assertEquals(Roles.ADMIN.toString(), r);
    }
}
