package com.dhanushkamath.youtubeapi.video;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dhanushkamath.youtubeapi.constants.Constants;

@RestController
public class VideoController {
	
	@Autowired
	VideoService videoService;
	
	@RequestMapping("/videos")
	public ResponseEntity<Map<String, Object>> getVideos(@RequestParam(required=false) Integer page, @RequestParam(required=false) Integer size){
		Map<String, Object> videos = new HashMap<>();
		size = (size == null || size <= 0) ? Constants.VIDEOCONTROLLER_DEFAULT_VIDEO_SIZE : size;
		
		if(page == null || page <= 0) {
			videos = videoService.getVideos(size);
		} else {
			videos = videoService.getVideos(page, size);
		}
		
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(videos);
	}
	
	@RequestMapping("/videos/search")
	public ResponseEntity<Map<String, Object>> getVideosSearchByText(@RequestParam String text){
		Map<String, Object> videos  = videoService.getVideosSearchByText(text);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(videos);
	}
}
