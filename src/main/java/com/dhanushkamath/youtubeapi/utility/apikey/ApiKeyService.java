package com.dhanushkamath.youtubeapi.utility.apikey;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dhanushkamath.youtubeapi.exception.GlobalControllerExceptionHandler;

@Service
public class ApiKeyService {
	@Value("${api.key.list}")
	private List<String> keyList;
	
	private int currentKeyIndex = 0;
	
	private Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
	
	public String getCurrentApiKey() {
		logger.info("Current Key: {}", keyList.get(this.currentKeyIndex));
		return keyList.get(this.currentKeyIndex);
	}
	
	public synchronized void changeKey() {
		this.currentKeyIndex = ( (this.currentKeyIndex + 1) % keyList.size() );
		logger.info("Key index: {} New Key: {}", currentKeyIndex, getCurrentApiKey());
	}
	
}
