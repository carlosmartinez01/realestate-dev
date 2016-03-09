/**
 * 
 */
package com.maverik.realestate.service.test;

import static org.mockito.Mockito.when;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.GenericJDBCException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.RecoverableDataAccessException;

import com.maverik.realestate.domain.entity.User;
import com.maverik.realestate.exception.DBException;
import com.maverik.realestate.handler.ExceptionHandler;
import com.maverik.realestate.repository.UserRepository;
import com.maverik.realestate.service.UserManagementServiceImpl;
import com.maverik.realestate.utils.SendMailUtil;

/**
 * @author jorge
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class UserPasswordServiceUnitTest {

    @Mock
    private SendMailUtil client;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Environment env;

    @Mock
    private ExceptionHandler exceptionHandler;

    @InjectMocks
    private UserManagementServiceImpl userService;

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testResetPasswordForUser() throws DBException {
	User u = new User();
	u.setId(1L);
	u.setUsername("homer");
	u.setPassword("123456");
	u.setEmail("homer@maverik.com");
	// when
	when(userRepository.findByUsername("homer")).thenReturn(u);
	when(userRepository.save(u)).thenReturn(u);
	when(client.sendMail("homer@maverik.com", "homer", "abez123"))
		.thenReturn(true);
	// then
	userService.resetPasswordForUser("homer");
	Assert.assertNotNull(u.getPassword());
	Assert.assertNotEquals("123456", u.getPassword());
    }

    @Test(expected = DBException.class)
    public void testFailResetPasswordWhenSave() throws Exception {
	User u = new User();
	u.setId(1L);
	u.setUsername("homer");
	u.setPassword("123456");
	u.setEmail("homer@maverik.com");
	// when
	GenericJDBCException c = new GenericJDBCException("DB error", null, "");
	DataAccessException e = new RecoverableDataAccessException("error", c);
	when(userRepository.findByUsername("homer")).thenReturn(u);
	when(userRepository.save(u)).thenThrow(e);
	when(exceptionHandler.getException(e)).thenReturn(
		new DBException("DB error: Unable to connect", "DB error",
			"SQL 200"));
	// then
	userService.resetPasswordForUser("homer");
	Assert.fail();
    }

    @Test(expected = DBException.class)
    public void testFailResetPasswordForUser() throws Exception {
	User u = new User();
	u.setId(1L);
	u.setUsername("homer");
	u.setPassword("123456");
	u.setEmail("homer@maverik.com");
	// when
	ConstraintViolationException c = new ConstraintViolationException(
		"constraint error", null, "");
	DataAccessException e = new RecoverableDataAccessException("error", c);
	when(userRepository.findByUsername("homer")).thenThrow(e);
	when(exceptionHandler.getException(e)).thenReturn(
		new DBException("Constraint error: Record already exists",
			"DB error", "SQL 200"));
	// then
	userService.resetPasswordForUser("homer");
	Assert.fail();
    }

    @Test(expected = DBException.class)
    public void testResetPasswordNoUserFound() throws Exception {
	User u = new User();
	u.setId(1L);
	u.setUsername("homer");
	u.setPassword("123456");
	u.setEmail("homer@maverik.com");
	// when
	when(userRepository.findByUsername("homer")).thenReturn(u);
	when(userRepository.save(u)).thenReturn(u);
	when(env.getProperty("exception.no.user")).thenReturn("No user found");
	// then
	userService.resetPasswordForUser("other");
	Assert.fail();
    }
}
