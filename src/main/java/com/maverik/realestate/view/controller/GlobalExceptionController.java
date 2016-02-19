package com.maverik.realestate.view.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.maverik.realestate.exception.GenericException;

@ControllerAdvice
public class GlobalExceptionController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionController.class);
	
	@ExceptionHandler({GenericException.class})
	public ModelAndView handleDBException(GenericException ex) {
		LOGGER.info("The view catch an error");
		LOGGER.info(ex.getErrorCode());
		LOGGER.debug("Full Stack= {}", ex.getFullStackMsg());
		ModelAndView model = new ModelAndView("/secured/error");
		model.addObject("errMsg", ex.getMessage());
		
		return model;
	}
}
