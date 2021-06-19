package com.dhanushkamath.youtubeapi.video;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dhanushkamath.youtubeapi.utility.dbclient.DatabaseService;
import com.dhanushkamath.youtubeapi.utility.videoclient.IVideoFetchClient;
import com.dhanushkamath.youtubeapi.utility.videoclient.IVideoSearchClient;

@Service
public class VideoService {
	
	@Autowired
	@Qualifier("youtubeVideoClient")
	IVideoFetchClient videoFetchClient;
	
	@Autowired
	@Qualifier("databaseVideoClient")
	IVideoSearchClient videoSearchClient;
	
	// TODO remove this.
	@Autowired
	DatabaseService dbService;
	
	public List<Video> getVideos(){
		List<Video> videoList = videoFetchClient.getLatestVideos();
		dbService.saveVideoList(videoList);
		return videoList;
	}
	
	public List<Video> getVideosSearchByText(String text){
		List<Video> videoList = videoSearchClient.searchVideosByText(text);
		return videoList;
	}
}
