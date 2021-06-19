package com.dhanushkamath.youtubeapi.utility.videoclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhanushkamath.youtubeapi.utility.dbclient.DatabaseService;
import com.dhanushkamath.youtubeapi.video.Video;

@Service("databaseVideoClient")
public class DatabaseVideoClient implements IVideoFetchClient, IVideoSearchClient{
	
	@Autowired
	DatabaseService databaseService;
	
	@Override
	public List<Video> getLatestVideos() {
		List<Video> videoList = databaseService.getVideosInReverseChronologicOrder();
		return videoList;
	}

	@Override
	public List<Video> searchVideosByText(String text) {
		List<Video> videoList = databaseService.getVideosSearchByText(text);
		return videoList;
	}

}
