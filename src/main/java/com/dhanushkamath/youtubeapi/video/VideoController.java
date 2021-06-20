package com.dhanushkamath.youtubeapi.video;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dhanushkamath.youtubeapi.constants.Constants;
import com.dhanushkamath.youtubeapi.utility.videoclient.YoutubeVideoClient;

/*
 * Controller for /videos endpoint
 * */
@RestController
public class VideoController {
	
	private Logger logger = LoggerFactory.getLogger(VideoController.class);
	
	@Autowired
	VideoService videoService;
	
	/** handles Get requests to /videos endpoint
	 * @param page the page number
	 * @param size the page size
	 * @param text text to be searched for within title and description of stored collection of videos.
	 * */
	@RequestMapping("/videos")
	public ResponseEntity<Map<String, Object>> getVideos(@RequestParam(required=false) Integer page, @RequestParam(required=false) Integer size, @RequestParam(required=false) String text){
		Map<String, Object> videos = new HashMap<>();
		
		text = (text == null) ? null : text.trim();
		size = (size == null || size <= 0) ? Constants.VIDEOCONTROLLER_DEFAULT_VIDEO_SIZE : size;
		
		if(page == null) {
			if(text == null || text.isEmpty()) {
				videos = videoService.getVideos(size);
			} else {
				videos = videoService.getVideos(text, size);
			}
		} else {
			if(text == null || text.isEmpty()) {
				videos = videoService.getVideos(page, size);
			} else {
				videos = videoService.getVideos(text, page, size);
			}
		}
		
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(videos);
	}
	
}
