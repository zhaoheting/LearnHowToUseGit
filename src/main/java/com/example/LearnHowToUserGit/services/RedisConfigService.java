package com.example.LearnHowToUserGit.services;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfigService {

    private int redisRefreshInterval = 60;

    private boolean dynamicRefreshRedisSources = true;

    private boolean validateRedisClusterMembership = true;

    private String RedisClusterPassword = "";

    private int timeToLive = 3600;

    public int getTimeToLive() {
        return timeToLive;
    }

    //    @Value("#{T(java.util.Arrays).asList('${spring.redis.cluster.nodes}')}")
//    private List<String> redisClusterNodes;

    /**
     * Get redis cluster nodes.
     *
     * @return The value of redis cluster nodes.
     */
//    public List<String> getRedisClusterNodes() {
//        return Collections.unmodifiableList(redisClusterNodes);
//    }
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
