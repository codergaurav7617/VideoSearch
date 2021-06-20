package com.gaurav.videoSearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VideoSearchApplication {
	public static void main(String[] args) {
		SpringApplication.run(VideoSearchApplication.class, args);
	}
}
