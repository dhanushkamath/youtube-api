package com.dhanushkamath.youtubeapi.utility.db;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.dhanushkamath.youtubeapi.video.Video;

public interface VideoRepository extends MongoRepository<Video, String> {
	List<Video> findAllBy(TextCriteria textCriteria, Sort sort);

	Page<Video> findAll(Pageable pageable);
}
