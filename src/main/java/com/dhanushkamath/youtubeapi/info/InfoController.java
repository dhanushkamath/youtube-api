package com.dhanushkamath.youtubeapi.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Controller for /info endpoint
 * */
@RestController
public class InfoController {

	@Autowired
	private InfoService infoService;

	@RequestMapping("/info")
	public ResponseEntity<Info> getInfo() {
		Info info = infoService.getInfo();
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(info);
	}
}
