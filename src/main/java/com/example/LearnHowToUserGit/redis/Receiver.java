package com.example.LearnHowToUserGit.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
    private CountDownLatch latch;

    @Autowired
    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(String message){
        LOGGER.info("Receive message: " + message);
        latch.countDown();
    }
}
