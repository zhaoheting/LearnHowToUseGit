package com.example.LearnHowToUserGit.config;

import com.example.LearnHowToUserGit.services.RedisConfigService;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.util.StringUtils;

import java.time.Duration;

@Configuration("CacheConfiguration")
@Profile("RedisCluster")
public class RedisClusterConfig implements CacheConfiguration {

    @Autowired
    private RedisConfigService redisConfigService;

    @Bean
    @Override
    public RedisConnectionFactory redisConnectionFactory() {
//        int refreshInterval = redisConfigService.getRefreshInterval();
//        boolean dynamicRefreshSources = redisConfigService.isDynamicRefreshRedisSources();
//        boolean validateRedisClusterMembership = redisConfigService.isValidateRedisClusterMembership();
//        String redisPassword = redisConfigService.getRedisClusterPassword();
//        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(redisConfigService.getRedisClusterNodes());
//        if(!StringUtils.isEmpty(redisPassword)){
//            redisClusterConfiguration.setPassword(RedisPassword.of(redisPassword));
//        }
//        ClusterTopologyRefreshOptions clusterTopologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
//                .enablePeriodicRefresh(Duration.ofSeconds(refreshInterval))//Enable period refresh and set interval.
//                .dynamicRefreshSources(dynamicRefreshSources)//Refresh sources dynamicly.
//                .build();
//        ClientOptions clientOptions = ClusterClientOptions.builder()
//                .topologyRefreshOptions(clusterTopologyRefreshOptions)
//                .validateClusterNodeMembership(validateRedisClusterMembership)//Validate the availability of the nodes.
//                .build();
//        //Two kinds of clients, including jedis and lettuce.
//        LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder().clientOptions(clientOptions).build();
//        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisClusterConfiguration, lettuceClientConfiguration);
//        return lettuceConnectionFactory;
        return null;
    }
}
