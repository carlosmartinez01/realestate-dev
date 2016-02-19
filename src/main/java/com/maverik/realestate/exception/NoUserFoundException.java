package com.maverik.realestate.exception;

public class NoUserFoundException extends GenericException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8363917165412406755L;
	
	
	public NoUserFoundException(String fullStackMsg, String message, String errorCode) {
		super(fullStackMsg, message, errorCode);
	}
}
