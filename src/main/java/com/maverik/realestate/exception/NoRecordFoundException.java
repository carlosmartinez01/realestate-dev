/**
 * 
 */
package com.maverik.realestate.exception;

/**
 * @author jorge
 *
 */
public class NoRecordFoundException extends GenericException {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -6716795004877582932L;

    public NoRecordFoundException(String fullStackMsg, String message,
	    String errorCode) {
	super(fullStackMsg, message, errorCode);
    }

}
