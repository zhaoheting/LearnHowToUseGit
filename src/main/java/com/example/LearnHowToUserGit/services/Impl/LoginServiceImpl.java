package com.example.LearnHowToUserGit.services.Impl;

import com.example.LearnHowToUserGit.beans.ZhtClaims;
import com.example.LearnHowToUserGit.dao.CacheAccessUtils;
import com.example.LearnHowToUserGit.entity.User;
import com.example.LearnHowToUserGit.model.Login;
import com.example.LearnHowToUserGit.model.TokenResponse;
import com.example.LearnHowToUserGit.services.ConfigService;
import com.example.LearnHowToUserGit.services.LoginService;
import com.example.LearnHowToUserGit.services.UserService;
import com.example.LearnHowToUserGit.services.utils.KeyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    UserService userService;
    AuthTokenServiceImpl authTokenService;
    CacheAccessUtils cacheAccessUtilsRedis;
    KeyService cacheKeyService;
    ConfigService configService;

    public LoginServiceImpl(UserService userService, AuthTokenServiceImpl authTokenService,
                            CacheAccessUtils cacheAccessUtilsRedis, KeyService cacheKeyService,
                            ConfigService configService) {
        this.userService = userService;
        this.authTokenService = authTokenService;
        this.cacheAccessUtilsRedis = cacheAccessUtilsRedis;
        this.cacheKeyService = cacheKeyService;
        this.configService = configService;
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
        String uuid = UUID.randomUUID().toString();
        claims.setIdKey(uuid);
        String username = user.getUserName();
        claims.setUsername(username);
        String token = authTokenService.createToken(claims);
        tokenResponse.setToken(token);
        cacheAccessUtilsRedis.set(cacheKeyService.generateUserKey(uuid, username), user, configService.getTimeToLive());
        return tokenResponse;
    }
}
