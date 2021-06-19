package com.dhanushkamath.youtubeapi.video;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhanushkamath.youtubeapi.utility.videoclient.IVideoFetchClient;

@Service
public class VideoService {
	
	@Autowired
	IVideoFetchClient videoFetchClient;
	
	public List<Video> getVideos(){
		List<Video> videoList = videoFetchClient.getLatestVideos();
		return videoList;
	}
}
