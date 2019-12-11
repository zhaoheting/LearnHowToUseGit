package com.example.LearnHowToUserGit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration("CacheConfiguration")
@Profile("RedisSentinel")
public class RedisSentinelConfig implements CacheConfiguration {

    @Override
    public RedisConnectionFactory redisConnectionFactory() {
        return null;
    }
}
