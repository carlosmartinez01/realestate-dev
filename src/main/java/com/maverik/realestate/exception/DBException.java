package com.maverik.realestate.exception;

public class DBException extends GenericException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4634538077323498872L;
	
	public DBException(String fullStackMsg, String message, String errorCode) {
		super(fullStackMsg, message, errorCode);
	}	
}
