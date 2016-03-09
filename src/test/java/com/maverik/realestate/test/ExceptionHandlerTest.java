/**
 * 
 */
package com.maverik.realestate.test;

import java.sql.SQLException;

import org.hibernate.exception.DataException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.LockAcquisitionException;
import org.hibernate.exception.SQLGrammarException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.maverik.realestate.exception.DBException;
import com.maverik.realestate.handler.ExceptionHandler;

/**
 * @author jorge
 *
 */
@ContextConfiguration(locations = { "classpath:application-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ExceptionHandlerTest {

    @Autowired
    private ExceptionHandler exceptionHandler;

    @Test
    public void testDataException() throws Exception {
	DBException err = null;
	DataAccessException e = new DataIntegrityViolationException("",
		new DataException("", new SQLException("")));
	err = exceptionHandler.getException(e);
	Assert.assertNotNull(err);
    }

    @Test
    public void testGenericJDBCException() throws Exception {
	DBException err = null;
	DataAccessException e = new DataIntegrityViolationException("",
		new GenericJDBCException("", new SQLException("")));
	err = exceptionHandler.getException(e);
	Assert.assertNotNull(err);
    }

    @Test
    public void testJDBConnectionException() throws Exception {
	DBException err = null;
	DataAccessException e = new DataIntegrityViolationException("",
		new JDBCConnectionException("", new SQLException("")));
	err = exceptionHandler.getException(e);
	Assert.assertNotNull(err);
    }

    @Test
    public void testLockAcquisitionException() throws Exception {
	DBException err = null;
	DataAccessException e = new DataIntegrityViolationException("",
		new LockAcquisitionException("", new SQLException("")));
	err = exceptionHandler.getException(e);
	Assert.assertNotNull(err);
    }

    @Test
    public void testSQLGrammarException() throws Exception {
	DBException err = null;
	DataAccessException e = new DataIntegrityViolationException("",
		new SQLGrammarException("", new SQLException("")));
	err = exceptionHandler.getException(e);
	Assert.assertNotNull(err);
    }
}
