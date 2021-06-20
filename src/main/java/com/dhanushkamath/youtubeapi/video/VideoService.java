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
import com.dhanushkamath.youtubeapi.utility.videoclient.IVideoSearchPageableClient;

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
	
	@Autowired
	@Qualifier("databaseVideoClient")
	IVideoSearchPageableClient videoSearchPageableClient;
	
	public Map<String, Object> getVideos(int maxResults){
		List<Video> videoList = videoFetchClient.getLatestVideos(maxResults);
		Map<String, Object> response = prepareResponse(videoList);
		return response;
	}
	
	public Map<String, Object> getVideos(int page, int size){
		Map<String, Object> videoPageMap = videoFetchPageableClient.getLatestVideos(page, size);
		return videoPageMap;
	}
	
	public Map<String, Object> getVideos(String text, int maxResults){
		List<Video> videoList = videoSearchClient.searchVideosByText(text, maxResults);
		Map<String, Object> response = prepareResponse(videoList);
		return response;
	}
	
	public Map<String, Object> getVideos(String text, int page, int size){
		Map<String, Object> videoPageMap = videoSearchPageableClient.searchVideosByText(text, page, size);
		return videoPageMap;
	}
	
	private Map<String, Object> prepareResponse(List<Video> videoList){
		Map<String, Object> response = new HashMap();
		response.put("videos", videoList);
		response.put("totalVideos", videoList.size());
		return response;
	}
}
