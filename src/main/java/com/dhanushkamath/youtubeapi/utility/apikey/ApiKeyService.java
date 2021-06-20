package com.dhanushkamath.youtubeapi.utility.apikey;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dhanushkamath.youtubeapi.exception.GlobalControllerExceptionHandler;

/*
 * Utility that provides API Key for interacting with YouTube API.
 * */
@Service
public class ApiKeyService {
	@Value("${api.key.list}")
	private List<String> keyList;
	
	private int currentKeyIndex = 0;
	
	private Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
	
	/** Fetches current API key.
	 * @param lastName A double containing the employee’s
	 * @return String representing API Key.
	*/
	public String getCurrentApiKey() {
		return keyList.get(this.currentKeyIndex);
	}
	/** Switches to the next API key.
	*/
	public synchronized void changeKey() {
		this.currentKeyIndex = ( (this.currentKeyIndex + 1) % keyList.size() );
		logger.debug("Switched to API Key #{} : {}", currentKeyIndex, this.getCurrentApiKey());
	}
	
}
