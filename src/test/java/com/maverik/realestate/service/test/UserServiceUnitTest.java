package com.maverik.realestate.service.test;

import static org.mockito.Mockito.when;

import org.hibernate.exception.GenericJDBCException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.maverik.realestate.domain.entity.User;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.mapper.UserMapper;
import com.maverik.realestate.repository.UserRepository;
import com.maverik.realestate.service.UserManagementService;
import com.maverik.realestate.service.UserManagementServiceImpl;
import com.maverik.realestate.view.bean.UserBean;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceUnitTest {

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private UserMapper mapperMock;

    @InjectMocks
    private UserManagementService userService = new UserManagementServiceImpl();

    @Before
    public void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAMockInstances() {
	Assert.assertNotNull(userRepositoryMock);
	Assert.assertNotNull(mapperMock);
    }

    @Test
    public void testGetActiveUser() throws GenericException {
	User u = new User();
	u.setId(1L);
	u.setUsername("homer");
	u.setActive(new Byte("0"));
	UserBean ub = new UserBean();
	ub.setId(u.getId());
	ub.setUsername(u.getUsername());
	ub.setActive(true);
	when(
		userRepositoryMock.findByUsernameAndActive("homer",
			Byte.valueOf("0"))).thenReturn(u);
	when(mapperMock.userToUserBean(u)).thenReturn(ub);
	UserBean user = userService.getActiveUserAndRoles("homer");
	Assert.assertNotNull(user);
	Assert.assertEquals("homer", user.getUsername());
	Assert.assertTrue(user.getActive());
    }

    @Test
    public void testGetInactiveUser() throws GenericException {
	// Refactor the get user method
	User u = new User();
	u.setId(1L);
	u.setUsername("homer");
	u.setActive(new Byte("1"));
	UserBean ub = new UserBean();
	ub.setId(u.getId());
	ub.setUsername(u.getUsername());
	ub.setActive(false);
	when(
		userRepositoryMock.findByUsernameAndActive("homer",
			Byte.valueOf("1"))).thenReturn(u);
	when(mapperMock.userToUserBean(u)).thenReturn(ub);
	UserBean user = userService.getActiveUserAndRoles("homer");
	Assert.assertNull(user);
    }

    @Test
    public void testGetUserProfile() throws GenericException {
	User u = new User();
	u.setId(2L);
	u.setUsername("homer");
	UserBean ub = new UserBean();
	ub.setId(u.getId());
	ub.setUsername(u.getUsername());
	when(userRepositoryMock.findOne(1L)).thenReturn(u);
	when(mapperMock.userToUserBean(u)).thenReturn(ub);
	UserBean user = userService.getUserProfile(1L);
	Assert.assertNotNull(user);
	Assert.assertEquals(Long.valueOf(2L), user.getId());
	Assert.assertNotEquals(Long.valueOf(1L), user.getId());
	Assert.assertEquals("homer", user.getUsername());
    }

    @Test(expected = GenericJDBCException.class)
    public void testFailGetActiveUser() throws GenericException {
	User u = new User();
	u.setId(1L);
	u.setUsername("homer");
	u.setActive(new Byte("0"));
	UserBean ub = new UserBean();
	ub.setId(u.getId());
	ub.setUsername(u.getUsername());
	ub.setActive(true);
	Mockito.when(
		userRepositoryMock.findByUsernameAndActive("homer",
			Byte.valueOf("0"))).thenThrow(
		new GenericJDBCException("DB exception", null));
	UserBean user = userService.getActiveUserAndRoles("homer");
	Assert.assertNotNull(user);
    }

//    @Test
//    public void testUpdateUserProfile() {
//	when(userRepositoryMock)
//	User entity = userRepository.findByUsername("maverikUser012");
//	entity.setPassword("0000");
//	entity.setFirstName("Updated");
//	entity.setLastName("My Lastname");
//	entity.setActive(Byte.valueOf("0"));
//	entity.setEmail("updated@u.com");
//	Company company = new Company();
//	company.setId(9L);
//	company.setCompanyName("Fake Company");
//	entity.setCompany(company);
//
//	entity = userRepository.save(entity);
//
//	Assert.notNull(entity);
//	org.junit.Assert.assertEquals("Updated", entity.getFirstName());
//
//	System.out.println(entity.getId());
//	System.out.println(entity.getCompany().getCompanyName());
//    }

    // @Test
    // @Ignore
    // public void testInsertUser() {
    // User entity = new User();
    // entity.setUsername("maverikUser0016");
    // entity.setPassword("1234");
    // entity.setFirstName("Mr Maverik");
    // entity.setLastName("My Lastname");
    // entity.setActive(Byte.valueOf("0"));
    // entity.setEmail("user@u.com");
    // Company company = new Company();
    // company.setId(1L);
    // entity.setCompany(company);
    //
    // entity = userRepository.save(entity);
    //
    // Assert.notNull(entity);
    // Assert.notNull(entity.getId());
    //
    // entity.getCompany().setCompanyName("Whatever");
    //
    // System.out.println(entity.getCompany().getCompanyName());
    //
    // entity.setEmail("updated@updated");
    // userRepository.save(entity);
    // }

    // @Test
    // @Ignore
    // public void testInsertUserAndUserRole() throws DBException {
    // User entity = new User();
    // entity.setUsername("test002");
    // entity.setPassword("0000");
    // entity.setFirstName("Jorge");
    // entity.setLastName("My Lastname");
    // entity.setActive(Byte.valueOf("0"));
    // entity.setEmail("updated@u.com");
    // Company company = new Company();
    // company.setId(3L);
    // company.setCompanyName("Fake Company");
    // entity.setCompany(company);
    // UserBean user = userMapper.userToUserBean(entity);
    //
    // user = userService.addUserToCompany(user, "ROLE_ADMINISTRATOR");
    //
    // Assert.notNull(user);
    // org.junit.Assert.assertEquals("test002", entity.getUsername());
    // }
    //
    // @Test(expected = DBException.class)
    // @Ignore
    // public void testInsertUserAndUserRoleAndFail() throws DBException {
    // User entity = new User();
    // entity.setUsername("test003");
    // entity.setPassword("0000");
    // entity.setFirstName("Jorge");
    // entity.setLastName("My Lastname");
    // entity.setActive(Byte.valueOf("0"));
    // entity.setEmail("updated@u.com");
    // Company company = new Company();
    // company.setId(3L);
    // company.setCompanyName("Fake Company");
    // entity.setCompany(company);
    // UserBean user = userMapper.userToUserBean(entity);
    //
    // userService.addUserToCompany(user, "ROLE_NONE");
    // }
    //
    // @Test
    // @Ignore
    // public void testDeleteUserHavingCompany() throws DBException {
    // User entity = userRepository.findByUsername("maverikUser0014");
    // UserBean user = userMapper.userToUserBean(entity);
    // // user.setCompany(null);
    // userService.deleteUser(user);
    // }
    //

    // @Test
    // @Ignore
    // public void generateRandomPassword() {
    // String password = RandomPasswordGenerator.generateKey();
    // org.junit.Assert.assertNotNull(password);
    // System.out.println(password);
    // }
    //
    // @Test
    // @Ignore
    // public void sendMailResetPassword() {
    // String user = "jorgem";
    // String toAddress = "jorgem@maverik.com";
    // String unmaskPassword = RandomPasswordGenerator.generateKey();
    //
    // SendMailUtil.sendMail(toAddress, user, unmaskPassword);
    // }
}
