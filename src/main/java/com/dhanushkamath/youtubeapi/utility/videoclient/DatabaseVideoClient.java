package com.dhanushkamath.youtubeapi.utility.videoclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dhanushkamath.youtubeapi.utility.db.DatabaseService;
import com.dhanushkamath.youtubeapi.video.Video;

@Service("databaseVideoClient")
public class DatabaseVideoClient implements IVideoFetchClient, IVideoSearchClient, IVideoFetchPageableClient{
	
	@Autowired
	DatabaseService databaseService;
	
	@Override
	public List<Video> getLatestVideos(int maxVideos) {
		List<Video> videoList = databaseService.getVideosInReverseChronologicOrder(maxVideos);
		return videoList;
	}

	@Override
	public List<Video> searchVideosByText(String text) {
		List<Video> videoList = databaseService.getVideosSearchByText(text);
		return videoList;
	}

	@Override
	public Map<String, Object> getLatestVideos(int page, int size) {
		Map<String, Object> response = new HashMap();
		List<Video> videoList = new ArrayList<>();
		
		Page<Video> videoPage = databaseService.getVideosInReverseChronologicalOrder(page, size);
		videoList = videoPage.getContent();
		response.put("videos", videoList);
		response.put("currentPage", videoPage.getNumber());
		response.put("totalItems", videoPage.getTotalElements());
		response.put("totalPages", videoPage.getTotalPages());
		
		
		return response;
	}

}
