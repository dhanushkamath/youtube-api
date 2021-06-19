package com.dhanushkamath.youtubeapi.utility.videoclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dhanushkamath.youtubeapi.video.Video;

@Service
public class YoutubeVideoClient implements IVideoClient {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public List<Video> getLatestVideos() {		
		return null;
	}
	
	
}
