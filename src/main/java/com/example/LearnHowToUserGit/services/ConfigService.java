package com.example.LearnHowToUserGit.services;

import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    long timeToLive = 86400;
    String authTokenSecret = "UTF-8";

    public String getAuthTokenSecret() {
        return authTokenSecret;
    }
    public long getTimeToLive() {
        return timeToLive;
    }
}
