package com.dhanushkamath.youtubeapi.utility.dbclient;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dhanushkamath.youtubeapi.video.Video;

public interface VideoRepository extends MongoRepository<Video, String> {

}
