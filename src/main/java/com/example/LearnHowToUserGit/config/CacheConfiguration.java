package com.example.LearnHowToUserGit.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Interface to Config Redis.
 *
 * @author heting.zhao
 * @since 12/06/2019
 */
@FunctionalInterface
public interface CacheConfiguration {

    /**
     * Spring-data-redis Redis Cache. Replace if using alternative cache.
     *
     * @return Redis connection factory.
     */
    RedisConnectionFactory redisConnectionFactory();

    /**
     * Spring-data-redis Redis Cache. Replace it if choose to use customised connection factory.
     *
     * @return Redis template.
     */
    @Bean
    @SuppressWarnings("all")
    default RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String,Object>();
        RedisConnectionFactory factory = redisConnectionFactory();
        redisTemplate.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //what is the usage?
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        //??
        redisTemplate.afterPropertiesSet();
        return redisTemplate;

    }
}
