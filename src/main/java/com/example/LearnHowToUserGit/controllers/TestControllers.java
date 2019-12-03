package com.example.LearnHowToUserGit.controllers;

import com.example.LearnHowToUserGit.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControllers {

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping("/setCache")
    public void testSetCache() {
        redisUtils.set("testKey", "testValue");
    }

    @RequestMapping("/getCache")
    public String testGetCache() {
        return redisUtils.get("testKey").toString();
    }
}
