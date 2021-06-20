package com.dhanushkamath.youtubeapi.video;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dhanushkamath.youtubeapi.utility.db.DatabaseService;
import com.dhanushkamath.youtubeapi.utility.videoclient.IVideoFetchClient;
import com.dhanushkamath.youtubeapi.utility.videoclient.IVideoFetchPageableClient;
import com.dhanushkamath.youtubeapi.utility.videoclient.IVideoSearchClient;

@Service
public class VideoService {
	
	@Autowired
	@Qualifier("databaseVideoClient")
	IVideoFetchClient videoFetchClient;
	
	@Autowired
	@Qualifier("databaseVideoClient")
	IVideoSearchClient videoSearchClient;
	
	@Autowired
	@Qualifier("databaseVideoClient")
	IVideoFetchPageableClient videoFetchPageableClient;
	
	public Map<String, Object> getVideos(int maxResults){
		List<Video> videoList = videoFetchClient.getLatestVideos(maxResults);
		
		Map<String, Object> response = new HashMap();
		response.put("videos", videoList);
		response.put("totalItems", videoList.size());
		
		return response;
	}
	
	public Map<String, Object> getVideosSearchByText(String text){
		List<Video> videoList = videoSearchClient.searchVideosByText(text);
		
		Map<String, Object> response = new HashMap();
		response.put("videos", videoList);
		response.put("totalItems", videoList.size());
		
		return response;
	}
	
	public Map<String, Object> getVideos(int page, int size){
		Map<String, Object> videoPageMap = videoFetchPageableClient.getLatestVideos(page, size);
		return videoPageMap;
	}
}
