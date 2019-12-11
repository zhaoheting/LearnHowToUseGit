package com.example.LearnHowToUserGit.services;

import com.example.LearnHowToUserGit.model.Login;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    ResponseEntity login(HttpServletRequest httpServletRequest, Login login);
}
