package com.dhanushkamath.youtubeapi.utility.db;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.dhanushkamath.youtubeapi.utility.apikey.ApiKeyService;
import com.dhanushkamath.youtubeapi.utility.videoclient.IVideoFetchClient;
import com.dhanushkamath.youtubeapi.video.Video;

@Component
public class DatabasePopulator {
	@Autowired
	@Qualifier("youtubeVideoClient")
	private IVideoFetchClient videoFetchClient;

	@Autowired
	private DatabaseService dbService;
	
	@Autowired
	ApiKeyService apiKeyService;

	@Value("${video.fetch.maxresults:25}")
	private int videoFetchMaxResults;

	private Logger logger = LoggerFactory.getLogger(DatabasePopulator.class);

	@Scheduled(fixedDelayString = "${video.fetch.interval}")
	public void fetchAndPopulate() {
		try {
			List<Video> videoList = videoFetchClient.getLatestVideos(videoFetchMaxResults);
			dbService.saveVideoList(videoList);
			logger.info("Saved {} in database.", videoList.size());
		} catch (HttpClientErrorException e) {
			HttpStatus statusCode = e.getStatusCode();
			logger.warn("The HTTP client returned a {} response", statusCode);
			if( statusCode == HttpStatus.FORBIDDEN ) {
				apiKeyService.changeKey();
			}
		}
	}
}
