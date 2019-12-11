package com.example.LearnHowToUserGit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("/dao")
public class LearnHowToUserGitApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnHowToUserGitApplication.class, args);
	}

}
