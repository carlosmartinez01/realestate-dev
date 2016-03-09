package com.maverik.realestate.exception;

public class GenericException extends Exception {

    /**
	 * 
	 */
    private static final long serialVersionUID = -5077369499378405505L;

    private final String errorCode;
    private final String message;
    private final String fullStackMsg;

    /**
     * @param fullStackMsg
     * @param message
     * @param errorCode
     */
    public GenericException(String fullStackMsg, String message,
	    String errorCode) {
	this.fullStackMsg = fullStackMsg;
	this.errorCode = errorCode;
	this.message = message;
    }

    public String getErrorCode() {
	return errorCode;
    }

    @Override
    public String getMessage() {
	return message;
    }

    public String getFullStackMsg() {
	return fullStackMsg;
    }
}
