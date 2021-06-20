package com.dhanushkamath.youtubeapi.utility.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	/*
	 * Get the latest results limited by maxResults
	 */
	public List<Video> getVideosInReverseChronologicOrder(int maxResults) {
		Pageable paging = PageRequest.of(0, maxResults)
				.withSort(Sort.by(Sort.Direction.DESC, Constants.DBSERVICE_VIDEO_PUBLISHEDAT));
		Page<Video> videoPage = videoRepository.findAll(paging);
		return videoPage.getContent();
	}

	/*
	 * Get the latest paginated results
	 */
	public Page<Video> getVideosInReverseChronologicalOrder(int page, int size) {
		Pageable paging = PageRequest.of(page, size)
				.withSort(Sort.by(Sort.Direction.DESC, Constants.DBSERVICE_VIDEO_PUBLISHEDAT));
		Page<Video> videoPage = videoRepository.findAll(paging);
		return videoPage;
	}

	/*
	 * Get the top search results for text
	 */
	public List<Video> getVideosSearchByText(String text) {
		TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(text.split("\\s+"));
		List<Video> videoList = videoRepository.findAllBy(criteria,
				Sort.by(Sort.Direction.DESC, Constants.DBSERVICE_VIDEO_TEXTSEARCHSCORE));
		return videoList;
	}
}