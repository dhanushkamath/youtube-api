package com.dhanushkamath.youtubeapi.utility.videoclient;

import java.util.Map;

public interface IVideoSearchPageableClient {
	Map<String, Object> searchVideosByText(String text, int page, int size);
}
