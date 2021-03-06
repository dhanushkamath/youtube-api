package com.dhanushkamath.youtubeapi.video;

import java.time.Instant;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

/**
 * Model for Video.
 */
@Document(collection = "Video")
public class Video {
	@Id
	private String videoId;

	@TextIndexed(weight = 2)
	private String title;

	@TextIndexed()
	private String description;

	// Only used while running full text search. Used for sorting text search
	// results.
	@TextScore
	private Float textSearchScore;

	@Indexed(name = "publishedAt_index", direction = IndexDirection.DESCENDING)
	private Instant publishedAt;

	Map<String, Thumbnail> thumbnails;

	public Video() {

	}

	public Video(String title, String description, Instant datetime, Map<String, Thumbnail> thumbnails) {
		super();
		this.title = title;
		this.description = description;
		this.publishedAt = datetime;
		this.thumbnails = thumbnails;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Instant getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Instant publishedAt) {
		this.publishedAt = publishedAt;
	}

	public Map<String, Thumbnail> getThumbnails() {
		return thumbnails;
	}

	public void setThumbnails(Map<String, Thumbnail> thumbnails) {
		this.thumbnails = thumbnails;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	@Override
	public String toString() {
		return "Video [title=" + title + ", description=" + description + ", publishedAt=" + publishedAt + ", videoId="
				+ videoId + ", thumbnails=" + thumbnails + "]";
	}

}
