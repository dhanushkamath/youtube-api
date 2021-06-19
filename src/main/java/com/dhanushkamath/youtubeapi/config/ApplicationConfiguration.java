package com.dhanushkamath.youtubeapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dhanushkamath.youtubeapi.info.Info;

@Configuration
public class ApplicationConfiguration {
	@Bean()
	@ConfigurationProperties(prefix="info")
	public Info getInfo() {
		return new Info();
	}
}
