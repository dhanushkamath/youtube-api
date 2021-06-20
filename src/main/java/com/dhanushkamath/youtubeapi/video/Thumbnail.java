package com.dhanushkamath.youtubeapi.video;

/**
 * Model for thumbnail.
 */
public class Thumbnail {
	private String url;
	private String width;
	private String height;

	public Thumbnail() {

	}

	public Thumbnail(String url, String width, String height) {
		super();
		this.url = url;
		this.width = width;
		this.height = height;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

}
