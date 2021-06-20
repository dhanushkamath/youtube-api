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
public class DatabaseVideoClient implements IVideoFetchClient, IVideoSearchClient, IVideoFetchPageableClient, IVideoSearchPageableClient{
	
	@Autowired
	DatabaseService databaseService;
	
	@Override
	public List<Video> getLatestVideos(int maxVideos) {
		List<Video> videoList = databaseService.getVideosInReverseChronologicOrder(maxVideos);
		return videoList;
	}

	@Override
	public List<Video> searchVideosByText(String text, int maxResults) {
		List<Video> videoList = databaseService.getVideosByText(text, maxResults);
		return videoList;
	}

	@Override
	public Map<String, Object> getLatestVideos(int page, int size) {
		Page<Video> videoPage = databaseService.getVideosInReverseChronologicalOrder(page, size);
		Map<String, Object> response = this.preparePaginatedReponse(videoPage);
		return response;
	}
	
	@Override
	public Map<String, Object> searchVideosByText(String text, int page, int size) {
		Page<Video> videoPage = databaseService.getVideosByText(text, page, size);
		Map<String, Object> response = this.preparePaginatedReponse(videoPage);
		return response;
	}

	
	private Map<String, Object> preparePaginatedReponse(Page<Video> videoPage){
		Map<String, Object> response = new HashMap();
		List<Video> videoList = new ArrayList<>();
		videoList = videoPage.getContent();
		response.put("videos", videoList);
		response.put("currentPage", videoPage.getNumber());
		response.put("totalVideos", videoPage.getTotalElements());
		response.put("totalPages", videoPage.getTotalPages());
		return response;
	}


}
