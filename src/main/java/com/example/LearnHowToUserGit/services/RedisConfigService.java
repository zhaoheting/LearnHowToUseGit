package com.example.LearnHowToUserGit.services;

import org.springframework.stereotype.Service;

@Service
public class RedisConfigService {

    private int redisRefreshInterval;

    private boolean dynamicRefreshRedisSources;

    private boolean validateRedisClusterMembership;

    public int getRefreshInterval() {
        return redisRefreshInterval;
    }

    public boolean isDynamicRefreshRedisSources() {
        return dynamicRefreshRedisSources;
    }

    public boolean isValidateRedisClusterMembership() {
        return validateRedisClusterMembership;
    }
}
