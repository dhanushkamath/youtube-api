package com.dhanushkamath.youtubeapi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import com.dhanushkamath.youtubeapi.utility.apikey.ApiKeyService;
import com.dhanushkamath.youtubeapi.utility.videoclient.YoutubeVideoClient;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
		
	@Autowired
	ApiKeyService apiKeyService;
	
	@ExceptionHandler(value=HttpClientErrorException.class)
	public void handleClientError(HttpClientErrorException httpClientErrorException) {
		HttpStatus statusCode = httpClientErrorException.getStatusCode();
		logger.warn("The HTTP client returned a {} response", statusCode);
		
		if( statusCode == HttpStatus.FORBIDDEN ) {
			apiKeyService.changeKey();
		}
	}
	
}
