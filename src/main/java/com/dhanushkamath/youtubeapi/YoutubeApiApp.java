package com.dhanushkamath.youtubeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class YoutubeApiApp {

	public static void main(String[] args) {
		SpringApplication.run(YoutubeApiApp.class, args);
	}

}
