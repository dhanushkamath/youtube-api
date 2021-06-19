package com.dhanushkamath.youtubeapi.video;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {
	
	@Autowired
	VideoService videoService;
	
	@RequestMapping("/videos")
	public ResponseEntity<List<Video>> getVideos(){
		List<Video> videos = videoService.getVideos();
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(videos);
	}
}
