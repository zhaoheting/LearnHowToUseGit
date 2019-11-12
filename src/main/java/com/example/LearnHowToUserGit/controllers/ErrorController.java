package com.example.LearnHowToUserGit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/error400Page")
    public String notFound(){
        return "/error/ErrorPage.html";
    }

}
