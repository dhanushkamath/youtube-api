package com.dhanushkamath.youtubeapi.utility.videoclient;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dhanushkamath.youtubeapi.utility.dbclient.DatabaseService;
import com.dhanushkamath.youtubeapi.video.Video;

@Component
public class DatabasePopulator {
	@Autowired
	@Qualifier("youtubeVideoClient")
	private IVideoFetchClient videoFetchClient;
	
	@Autowired
	private DatabaseService dbService;
	
	Logger logger = LoggerFactory.getLogger(DatabasePopulator.class);
	
	@Scheduled(fixedDelayString ="${video.fetch.interval}")
	public void fetchAndPopulate() {
		List<Video> videoList = videoFetchClient.getLatestVideos(25);
		dbService.saveVideoList(videoList);
		logger.info("Saved {} in database.", videoList.size());
	}
}

