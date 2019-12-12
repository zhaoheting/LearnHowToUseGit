package com.example.LearnHowToUserGit.controllers;

import com.example.LearnHowToUserGit.model.Login;
import com.example.LearnHowToUserGit.model.TokenResponse;
import com.example.LearnHowToUserGit.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthenticationApiController {

    @Autowired
    LoginService loginService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody Login login) {
        return loginService.login(request, login);
    }
}
