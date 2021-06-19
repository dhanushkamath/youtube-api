package com.dhanushkamath.youtubeapi.utility.videoclient;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.dhanushkamath.youtubeapi.constants.Constants;
import com.dhanushkamath.youtubeapi.video.Video;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

@Service("youtubeVideoClient")
public class YoutubeVideoClient implements IVideoFetchClient {
	
	Logger logger = LoggerFactory.getLogger(YoutubeVideoClient.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Override
	public List<Video> getLatestVideos() {		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Constants.YOUTUBE_URL_SEARCH)
		        .queryParam(Constants.YOUTUBE_QUERYPARAM_PART, Constants.YOUTUBE_QUERYPARAM_PART_VALUE)
		        .queryParam(Constants.YOUTUBE_QUERYPARAM_MAXRESULTS, "25")
		        .queryParam(Constants.YOUTUBE_QUERYPARAM_ORDER, Constants.YOUTUBE_QUERYPARAM_ORDER_VALUE)
		        .queryParam(Constants.YOUTUBE_QUERYPARAM_Q, "cricket")
		        .queryParam(Constants.YOUTUBE_QUERYPARAM_TYPE, Constants.YOUTUBE_QUERYPARAM_TYPE_VALUE)
		        .queryParam(Constants.YOUTUBE_QUERYPARAM_KEY, "AIzaSyAG27rXrmqvQxSaOmsKfULgUycj-xToiG4");
		
		HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
		
		List<Video> videoList = new ArrayList<>();
		try {
			ArrayNode itemsNode = (ArrayNode) objectMapper.readTree(response.getBody()).get("items");
			itemsNode.forEach(item -> {
				Video video = objectMapper.convertValue(item.get("snippet"), Video.class);
				video.setVideoId(item.get("id").get("videoId").asText());
				System.out.println(video.toString());
				videoList.add(video);
			});
		} catch (JsonProcessingException e) {
			logger.warn(e.getMessage());
		}
		
		return videoList;
	}
	
	
}
