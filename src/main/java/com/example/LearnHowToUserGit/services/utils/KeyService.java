package com.example.LearnHowToUserGit.services.utils;

import org.springframework.stereotype.Service;

@Service
public class KeyService {
    private static final String USERID = "userId:";

    public KeyService() {
    }

    public String generateUserKey(String uuid, String username) {
        return USERID.concat(uuid).concat(username);
    }
}
