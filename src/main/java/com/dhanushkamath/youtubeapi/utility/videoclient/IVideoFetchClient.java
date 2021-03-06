package com.dhanushkamath.youtubeapi.utility.videoclient;

import java.util.List;

import com.dhanushkamath.youtubeapi.video.Video;

public interface IVideoFetchClient {
	public List<Video> getLatestVideos(int maxResults);
}
