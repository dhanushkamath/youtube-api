package com.dhanushkamath.youtubeapi.utility.dbclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import com.dhanushkamath.youtubeapi.constants.Constants;
import com.dhanushkamath.youtubeapi.video.Video;

@Service
public class DatabaseService {
	@Autowired 
	VideoRepository videoRepository;
	
	public void saveVideoList(List<Video> videoList) {
		videoRepository.saveAll(videoList);
	}
	
	public void saveVideo(Video video) {
		videoRepository.save(video);
	}
	
	public List<Video> getVideosInReverseChronologicOrder() {
		List<Video> videoList = videoRepository.findAll(Sort.by(Sort.Direction.DESC, Constants.DBSERVICE_VIDEO_PUBLISHEDAT));
		return videoList;
	}
	
	public List<Video> getVideosSearchByText(String text){
		TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(text.split("\\s+"));
		List<Video> videoList = videoRepository.findAllBy(criteria, Sort.by(Sort.Direction.DESC, Constants.DBSERVICE_VIDEO_TEXTSEARCHSCORE));
		return videoList;
	}
}
