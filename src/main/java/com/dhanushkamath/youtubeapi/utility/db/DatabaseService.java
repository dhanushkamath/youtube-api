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

/**
 * Service for interacting with DB.
 */
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

	/**
	 * Get the latest results limited by maxResults
	 * 
	 * @param maxResults Maximum number of results to be fetched
	 * @return List of videos sorted in reverse chronological order of publishing
	 *         date-time.
	 */
	public List<Video> getVideosInReverseChronologicOrder(int maxResults) {
		Pageable paging = PageRequest.of(0, maxResults)
				.withSort(Sort.by(Sort.Direction.DESC, Constants.DBSERVICE_VIDEO_PUBLISHEDAT));
		Page<Video> videoPage = videoRepository.findAll(paging);
		return videoPage.getContent();
	}

	/**
	 * Get the latest paginated results
	 * 
	 * @param page the page number
	 * @param size the page size
	 * @return Page embedded with Video.
	 */
	public Page<Video> getVideosInReverseChronologicalOrder(int page, int size) {
		Pageable paging = PageRequest.of(page, size)
				.withSort(Sort.by(Sort.Direction.DESC, Constants.DBSERVICE_VIDEO_PUBLISHEDAT));
		Page<Video> videoPage = videoRepository.findAll(paging);
		return videoPage;
	}

	/**
	 * Get the top search results for provided text limited by maxResults
	 * 
	 * @param text       Text to be searched for
	 * @param maxResults Maximum number of results to be fetched
	 * @return List of videos.
	 */
	public List<Video> getVideosByText(String text, int maxResults) {
		TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(text.split("\\s+"));
		Pageable paging = PageRequest.of(0, maxResults)
				.withSort(Sort.by(Sort.Direction.DESC, Constants.DBSERVICE_VIDEO_TEXTSEARCHSCORE));
		Page<Video> videoPage = videoRepository.findAllBy(criteria, paging);
		List<Video> videoList = videoPage.getContent();
		return videoList;
	}

	/**
	 * Get the paginated top search results for provided text
	 * 
	 * @param text Text to be searched for
	 * @param page the page number
	 * @param size the page size
	 * @return Page embedded with Video.
	 */
	public Page<Video> getVideosByText(String text, int page, int size) {
		TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(text.split("\\s+"));
		Pageable paging = PageRequest.of(page, size)
				.withSort(Sort.by(Sort.Direction.DESC, Constants.DBSERVICE_VIDEO_TEXTSEARCHSCORE));
		Page<Video> videoPage = videoRepository.findAllBy(criteria, paging);
		return videoPage;
	}
}
