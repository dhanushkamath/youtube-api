package com.dhanushkamath.youtubeapi.utility.videoclient;

import java.util.Map;

public interface IVideoFetchPageableClient {
	Map<String, Object> getLatestVideos(int page, int size);
}
