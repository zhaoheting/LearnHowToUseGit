package com.example.LearnHowToUserGit.services;

import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    int timeToLive = 86400;
    String authTokenSecret = "UTF-8";

    public String getAuthTokenSecret() {
        return authTokenSecret;
    }
    public int getTimeToLive() {
        return timeToLive;
    }
}
