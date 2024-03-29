/**
 * 
 */
package com.maverik.realestate.response;

/**
 * @author jorge
 *
 */
public abstract class AbstractResponse {

    private String successMessage;

    private String errorMessage;

    public String getSuccessMessage() {
	return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
	this.successMessage = successMessage;
    }

    public String getErrorMessage() {
	return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
    }
}
