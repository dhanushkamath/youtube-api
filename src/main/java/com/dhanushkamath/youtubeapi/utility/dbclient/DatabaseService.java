package com.dhanushkamath.youtubeapi.utility.dbclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
		List<Video> videoList = videoRepository.findAll(Sort.by(Sort.Direction.DESC, "publishedAt"));
		return videoList;
	}
}
