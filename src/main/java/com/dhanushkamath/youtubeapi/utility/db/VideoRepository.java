package com.dhanushkamath.youtubeapi.utility.db;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.dhanushkamath.youtubeapi.video.Video;

/**
 * Spring Data Mongo Repository.
 */
public interface VideoRepository extends MongoRepository<Video, String> {
	Page<Video> findAllBy(TextCriteria textCriteria, Pageable page);

	List<Video> findAllBy(TextCriteria textCriteria);

	Page<Video> findAll(Pageable pageable);
}
