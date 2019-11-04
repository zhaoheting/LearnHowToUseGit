package com.example.LearnHowToUserGit.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControllers {

    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
