package com.dhanushkamath.youtubeapi.utility.videoclient;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.dhanushkamath.youtubeapi.constants.Constants;
import com.dhanushkamath.youtubeapi.utility.apikey.ApiKeyService;
import com.dhanushkamath.youtubeapi.video.Video;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.time.format.DateTimeFormatter; 
import java.time.LocalDateTime;

@Service("youtubeVideoClient")
public class YoutubeVideoClient implements IVideoFetchClient {

	private Logger logger = LoggerFactory.getLogger(YoutubeVideoClient.class);
	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"); 

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Value("${video.fetch.topic:surfing}")
	private String videoFetchTopic;

	@Autowired
	private ApiKeyService apiKeyService;

	/**
	 * Get the latest results limited by maxResults
	 * 
	 * @param maxResults Maximum number of results to be fetched
	 * @return List of videos.
	 */
	@Override
	public List<Video> getLatestVideos(int maxResults) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Constants.YOUTUBE_URL_SEARCH)
				.queryParam(Constants.YOUTUBE_QUERYPARAM_PART, Constants.YOUTUBE_QUERYPARAM_PART_VALUE)
				.queryParam(Constants.YOUTUBE_QUERYPARAM_MAXRESULTS, maxResults)
				.queryParam(Constants.YOUTUBE_QUERYPARAM_ORDER, Constants.YOUTUBE_QUERYPARAM_ORDER_VALUE)
				.queryParam(Constants.YOUTUBE_QUERYPARAM_Q, videoFetchTopic)
				.queryParam(Constants.YOUTUBE_QUERYPARAM_TYPE, Constants.YOUTUBE_QUERYPARAM_TYPE_VALUE)
				.queryParam(Constants.YOUTUBE_QUERYPARAM_PUBLISHEDAFTER, this.getDateTimeFiveDaysBeforeFromNow())
				.queryParam(Constants.YOUTUBE_QUERYPARAM_KEY, apiKeyService.getCurrentApiKey());
		HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
				String.class);

		List<Video> videoList = new ArrayList<>();
		try {
			ArrayNode itemsNode = (ArrayNode) objectMapper.readTree(response.getBody()).get("items");
			itemsNode.forEach(item -> {
				Video video = objectMapper.convertValue(item.get("snippet"), Video.class);
				video.setVideoId(item.get("id").get("videoId").asText());
				videoList.add(video);
			});
			logger.info("Fetched {} {} videos from YouTube.", videoList.size(), videoFetchTopic);
		} catch (JsonProcessingException e) {
			logger.warn(e.getMessage());
		}

		return videoList;
	}
	
	
	private String getDateTimeFiveDaysBeforeFromNow() {
		LocalDateTime dt = LocalDateTime.now().minusDays(5);
		return dateTimeFormatter.format(dt);
	}

}
