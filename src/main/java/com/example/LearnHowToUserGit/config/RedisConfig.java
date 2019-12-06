package com.example.LearnHowToUserGit.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration("CacheConfiguration")
@Profile("RedisStandalone")
public abstract class RedisConfig implements CacheConfiguration{

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    public RedisConnectionFactory redisConnectionFactory(){
         return this.redisConnectionFactory;
     }
}
