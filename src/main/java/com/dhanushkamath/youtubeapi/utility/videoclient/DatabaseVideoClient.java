package com.dhanushkamath.youtubeapi.utility.videoclient;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dhanushkamath.youtubeapi.video.Video;


public class DatabaseVideoClient implements IVideoFetchClient, IVideoSearchClient{

	@Override
	public List<Video> getLatestVideos() {
		// TODO implement database video fetch
		return null;
	}

	@Override
	public List<Video> searchVideosByText(String text) {
		// TODO Auto-generated method stub
		return null;
	}

}
