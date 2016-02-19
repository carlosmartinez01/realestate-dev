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

import com.maverik.realestate.constants.RealEstateConstants.Roles;
import com.maverik.realestate.exception.DBException;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.service.UserManagementService;
//import com.maverik.realestate.test.Base;
import com.maverik.realestate.view.bean.UserBean;

@ContextConfiguration(locations = {"classpath:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {
    
    /*
     * 
     * Integration Test for User Service
     * 
     * CRUD operations
     * 
     * */

    @Autowired
    private UserManagementService userService;
    
    private static UserBean user;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /*
     * Every time a user is added, a default role is added
     * */
    @Test
    public void testAInsertUser() throws DBException {
	UserBean u = new UserBean();
	u.setActive(true);
	u.setUsername("test_user");
	u.setPassword("123456789");
	u.setFirstName("Dummy");
	u.setLastName("Dummy");
	u.setEmail("dummy@maverik.com");
	Assert.assertNotNull(userService);
	user = userService.addUserToCompany(u, Roles.USER.toString());	
	Assert.assertNotNull(user);
	Assert.assertNotNull(user.getId());
    }
    
    @Test
    public void testBFindAllUsers() throws DBException {
	List<UserBean> lst = userService.getAllUsers();
	Assert.assertNotNull(lst);
	Assert.assertNotEquals(0, lst.size());
    }
    
    /*
     * Every time a user is updated, the previous roles should be deleted 
     * to avoid Constraint issues.  The update function is going to add the default role
     * */
    @Test
    public void testCFindUserAndUpdate() throws DBException {
	UserBean u =  userService.getUserProfile(user.getId());
	userService.deleteUserRolesWhenUpdateProfile(u);
	Assert.assertNotNull(u);
	u.setFirstName("Update dummy name");
	u = userService.updateUserProfile(u, null);
	Assert.assertNotEquals("Dummy", u.getFirstName());
	String previousPassword = u.getPassword();
	u.setPassword("this is a new password");
	userService.deleteUserRolesWhenUpdateProfile(u);
	u = userService.updateUserProfile(u, previousPassword);
	Assert.assertNotEquals("this is a new password", u.getPassword()); //encrypted password
	Assert.assertEquals(user.getId(), u.getId());
    }
    
    @Test
    public void testDDeleteUser() throws GenericException {
	userService.deleteUser(user);
	Assert.assertNull(userService.getActiveUserAndRoles("test_user"));
    }
}
