package com.example.LearnHowToUserGit.services.Impl;

import com.example.LearnHowToUserGit.beans.ZhtClaims;
import com.example.LearnHowToUserGit.dao.CacheAccessUtils;
import com.example.LearnHowToUserGit.dao.CacheAccessUtilsRedisImpl;
import com.example.LearnHowToUserGit.entity.User;
import com.example.LearnHowToUserGit.model.Login;
import com.example.LearnHowToUserGit.model.TokenResponse;
import com.example.LearnHowToUserGit.services.LoginService;
import com.example.LearnHowToUserGit.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class LoginServiceImpl implements LoginService {

    UserService userService;
    AuthTokenServiceImpl authTokenService;
    CacheAccessUtils cacheAccessUtilsRedis;

    public LoginServiceImpl(UserService userService, AuthTokenServiceImpl authTokenService
            , CacheAccessUtilsRedisImpl cacheAccessUtilsRedis) {
        this.userService = userService;
        this.authTokenService = authTokenService;
        this.cacheAccessUtilsRedis = cacheAccessUtilsRedis;
    }

    @Override
    public ResponseEntity login(HttpServletRequest httpServletRequest, Login login) {
        User user = userService.selectUser(login.getUsername(), login.getPassword());
        if (user != null) {
            TokenResponse tokenResponse = updateCacheProfile(user);
            return new ResponseEntity<>(tokenResponse, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    public TokenResponse updateCacheProfile(User user) {
        TokenResponse tokenResponse = new TokenResponse();
        ZhtClaims claims = new ZhtClaims();
        String token = authTokenService.createToken(claims);
        tokenResponse.setToken(token);
        cacheAccessUtilsRedis.set("userToken", token);
        cacheAccessUtilsRedis.set(user.getUserName(), user);
        return tokenResponse;
    }
}
