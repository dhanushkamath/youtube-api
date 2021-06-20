package com.dhanushkamath.youtubeapi.constants;

/*
 * Class containing all constants defined throughout the app.
 * */
public final class Constants {

	private Constants() {
	}

	public static final String INFO_DEFAULTSTATUS = "up";

	public static final String YOUTUBE_URL_SEARCH = "https://youtube.googleapis.com/youtube/v3/search";
	public static final String YOUTUBE_QUERYPARAM_PART = "part";
	public static final String YOUTUBE_QUERYPARAM_PART_VALUE = "snippet";
	public static final String YOUTUBE_QUERYPARAM_ORDER = "order";
	public static final String YOUTUBE_QUERYPARAM_ORDER_VALUE = "date";
	public static final String YOUTUBE_QUERYPARAM_Q = "q";
	public static final String YOUTUBE_QUERYPARAM_KEY = "key";
	public static final String YOUTUBE_QUERYPARAM_MAXRESULTS = "maxResults";
	public static final String YOUTUBE_QUERYPARAM_TYPE = "type";
	public static final String YOUTUBE_QUERYPARAM_TYPE_VALUE = "video";

	public static final String DBSERVICE_VIDEO_PUBLISHEDAT = "publishedAt";
	public static final String DBSERVICE_VIDEO_TEXTSEARCHSCORE = "textSearchScore";

	public static final int VIDEOCONTROLLER_DEFAULT_VIDEO_SIZE = 10;

}
