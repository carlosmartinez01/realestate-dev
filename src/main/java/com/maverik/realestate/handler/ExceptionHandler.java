/**
 * 
 */
package com.maverik.realestate.handler;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.LockAcquisitionException;
import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Component;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.exception.DBException;

/**
 * @author jorge
 *
 */
@Component
@SonarClassExclusion
public class ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(ExceptionHandler.class);

    public static final String ERROR_CODES = "Error Codes: ";

    public static final String GENERIC_ERR_MSG = "exception.db.generic";

    public static final String AND = " and ";

    @Autowired
    private Environment env;

    public DBException getException(DataAccessException ex) {
	LOGGER.info("Getting exception for DataAccessException: " + ex);

	Throwable t = ex.getCause();
	if (t instanceof ConstraintViolationException) {
	    return getConstraintException(t);
	} else if (t instanceof DataException) {
	    return getDataException(t);
	} else if (t instanceof GenericJDBCException) {
	    return getGenericJDBCException(t);
	} else if (t instanceof JDBCConnectionException) {
	    return getJDBCConnectionException(t);
	} else if (t instanceof LockAcquisitionException) {
	    return getLockAcquisitionException(t);
	} else if (t instanceof SQLGrammarException) {
	    return getSQLGrammarException(t);
	} else if (t instanceof JpaSystemException) {
	    return getJpaSystemException(t);
	} else {
	    return new DBException(env.getProperty(GENERIC_ERR_MSG),
		    env.getProperty(GENERIC_ERR_MSG), "-1");
	}
    }

    private DBException getJpaSystemException(Throwable t) {
	LOGGER.info("Getting exception for JpaSystemException");

	return new DBException(t.getMessage(),
		env.getProperty(GENERIC_ERR_MSG), "-1");
    }

    private DBException getConstraintException(Throwable t) {
	LOGGER.info("Getting exception for ConstraintViolationException");

	return new DBException(((ConstraintViolationException) t)
		.getSQLException().toString(),
		env.getProperty("exception.db.constraint"), ERROR_CODES
			+ ((ConstraintViolationException) t).getErrorCode()
			+ AND
			+ ((ConstraintViolationException) t).getSQLState());
    }

    private DBException getDataException(Throwable t) {
	LOGGER.info("Getting exception for DataException");

	return new DBException(
		((DataException) t).getSQLException().toString(),
		env.getProperty("exception.db.data.mismatch"), ERROR_CODES
			+ ((DataException) t).getErrorCode() + AND
			+ ((DataException) t).getSQLState());
    }

    private DBException getJDBCConnectionException(Throwable t) {
	LOGGER.info("Getting exception for JDBCConnectionException");

	return new DBException(((JDBCConnectionException) t).getSQLException()
		.toString(), env.getProperty("exception.db.communication"),
		ERROR_CODES + ((JDBCConnectionException) t).getErrorCode()
			+ AND + ((JDBCConnectionException) t).getSQLState());
    }

    private DBException getLockAcquisitionException(Throwable t) {
	LOGGER.info("Getting exception for LockAcquisitionException");

	return new DBException(((LockAcquisitionException) t).getSQLException()
		.toString(), env.getProperty("exception.db.lock"), ERROR_CODES
		+ ((LockAcquisitionException) t).getErrorCode() + AND
		+ ((LockAcquisitionException) t).getSQLState());
    }

    private DBException getSQLGrammarException(Throwable t) {
	LOGGER.info("Getting exception for SQLGrammarException");

	return new DBException(((SQLGrammarException) t).getSQLException()
		.toString(), env.getProperty("exception.db.grammar"),
		ERROR_CODES + ((SQLGrammarException) t).getErrorCode() + AND
			+ ((SQLGrammarException) t).getSQLState());
    }

    private DBException getGenericJDBCException(Throwable t) {
	LOGGER.info("Getting exception for GenericJDBCException");

	return new DBException(((GenericJDBCException) t).getSQLException()
		.toString(), env.getProperty(GENERIC_ERR_MSG), ERROR_CODES
		+ ((GenericJDBCException) t).getErrorCode() + AND
		+ ((GenericJDBCException) t).getSQLState());
    }
}
