package com.example.LearnHowToUserGit;

import com.example.LearnHowToUserGit.redis.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class LearnHowToUserGitApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(LearnHowToUserGitApplication.class);

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(LearnHowToUserGitApplication.class, args);
//		StringRedisTemplate template = applicationContext.getBean(StringRedisTemplate.class);
//		CountDownLatch latch = applicationContext.getBean(CountDownLatch.class);
//		LOGGER.info("Sending message: ");
//		template.convertAndSend("chat","Hello redis!");
//      template.opsForHash().get("", Object.class);
//		template.opsForValue().set("qqq","eee");
//		latch.await();
//		System.exit(0);
	}

//	//listener container
//	@Bean
//	RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter adapter){
//		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//		container.setConnectionFactory(redisConnectionFactory);
//		container.addMessageListener(adapter,new PatternTopic("chat"));
//		return container;
//	}
//
//	@Bean
//	MessageListenerAdapter adapter(Receiver receiver){
//		return new MessageListenerAdapter(receiver,"receiveMessage");
//	}
//
//	@Bean
//	Receiver receiver(CountDownLatch latch){
//		return new Receiver(latch);
//	}
//
//	@Bean
//	CountDownLatch latch(){
//		return new CountDownLatch(1);
//	}
//
//	//template
//	@Bean
//	StringRedisTemplate template(RedisConnectionFactory factory){
//		return new StringRedisTemplate(factory);
//	}
}
