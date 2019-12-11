package com.example.LearnHowToUserGit.services;

import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    int timeToLive ;

    public int getTimeToLive() {
        return timeToLive;
    }
}
