package com.example.LearnHowToUserGit.services;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RedisConfigService {

    private int redisRefreshInterval;

    private boolean dynamicRefreshRedisSources;

    private boolean validateRedisClusterMembership;

    private String RedisClusterPassword;

    private List<String> redisClusterNodes;

    /**
     * Get redis cluster nodes.
     *
     * @return The value of redis cluster nodes.
     */
    public List<String> getRedisClusterNodes() {
        return Collections.unmodifiableList(redisClusterNodes);
    }

    public int getRefreshInterval() {
        return redisRefreshInterval;
    }

    public boolean isDynamicRefreshRedisSources() {
        return dynamicRefreshRedisSources;
    }

    public boolean isValidateRedisClusterMembership() {
        return validateRedisClusterMembership;
    }

    public String getRedisClusterPassword() {
        return RedisClusterPassword;
    }
}
