package com.dhanushkamath.youtubeapi.utility.videoclient;

import java.util.List;

import com.dhanushkamath.youtubeapi.video.Video;

public interface IVideoSearchClient {
	public List<Video> searchVideosByText(String text);
}
