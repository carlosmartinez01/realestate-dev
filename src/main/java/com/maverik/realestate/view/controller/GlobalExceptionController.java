package com.maverik.realestate.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.maverik.realestate.exception.GenericException;

@ControllerAdvice
public class GlobalExceptionController {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(GlobalExceptionController.class);

    private static final long FILE_MAX_SIZE = 8400000;

    @ExceptionHandler({ GenericException.class })
    public ModelAndView handleDBException(GenericException ex) {
	LOGGER.info("The view catch an GenericException");
	LOGGER.info(ex.getErrorCode());
	LOGGER.debug("Full Stack= {}", ex.getFullStackMsg());
	ModelAndView model = new ModelAndView("/secured/error");
	model.addObject("errMsg", ex.getMessage());

	return model;
    }

    @ExceptionHandler({ NoHandlerFoundException.class })
    public ModelAndView handleNotFoundEx(HttpServletRequest request,
	    Exception ex) {
	LOGGER.info("The view catch an NoHandlerException - Unable to match the URL");
	LOGGER.info("" + ex);
	ModelAndView model = new ModelAndView("/secured/error");
	model.addObject("errMsg",
		"Unable to find the resource, please contact support team");

	return model;
    }

    @ExceptionHandler({ MaxUploadSizeExceededException.class })
    @ResponseStatus(reason = "The file exceeds the maximium allowed!!", value = HttpStatus.BAD_REQUEST)
    public ModelAndView handleMaxUploadFileSizeError(Exception ex) {
	LOGGER.info("The view catch an Max File Exceed size error");
	LOGGER.info("" + ex);
	ModelAndView model = new ModelAndView("/secured/error");
	model.addObject("errMsg", "Maximum upload size of " + FILE_MAX_SIZE
		+ " bytes exceeded");

	return model;
    }

    @ExceptionHandler({ Exception.class })
    public ModelAndView handleOtherError(HttpServletRequest request,
	    Exception ex) {
	LOGGER.info("The view catch an error");
	LOGGER.info("" + ex);
	ModelAndView model = new ModelAndView("/secured/error");
	model.addObject("errMsg",
		"Unable to process your request, please try again later");

	return model;
    }
}
