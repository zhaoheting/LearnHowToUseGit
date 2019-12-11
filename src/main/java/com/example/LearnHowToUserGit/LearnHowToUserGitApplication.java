package com.example.LearnHowToUserGit;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("/dao")
public class LearnHowToUserGitApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(LearnHowToUserGitApplication.class);

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(LearnHowToUserGitApplication.class, args);
	}
}
