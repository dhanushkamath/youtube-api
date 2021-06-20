package com.dhanushkamath.youtubeapi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * Global Exception Handler for Spring MVC.
 * */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
		 
}
