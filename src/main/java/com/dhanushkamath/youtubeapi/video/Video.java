package com.dhanushkamath.youtubeapi.video;

import java.time.Instant;
import java.util.Map;

public class Video {
	String title;
	String description;
	Instant datetime;
	Map<String, Thumbnail> thumbnails; 
	
	public Video() {
		
	}

	public Video(String title, String description, Instant datetime, Map<String, Thumbnail> thumbnails) {
		super();
		this.title = title;
		this.description = description;
		this.datetime = datetime;
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

	public Instant getDatetime() {
		return datetime;
	}

	public void setDatetime(Instant datetime) {
		this.datetime = datetime;
	}

	public Map<String, Thumbnail> getThumbnails() {
		return thumbnails;
	}

	public void setThumbnails(Map<String, Thumbnail> thumbnails) {
		this.thumbnails = thumbnails;
	}
	
	
}
