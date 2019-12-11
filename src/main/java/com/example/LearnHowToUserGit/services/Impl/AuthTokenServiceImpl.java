package com.example.LearnHowToUserGit.services.Impl;

import com.example.LearnHowToUserGit.services.ConfigService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthTokenServiceImpl {

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
    ConfigService configService;

    public AuthTokenServiceImpl(ConfigService configService) {
        this.configService = configService;
    }

    public String createToken(Claims claims) {
        claims.put("createdTime", System.currentTimeMillis());
        int timetoLive = configService.getTimeToLive();
        String secret = "UTF-8";//不知道是什么用
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SIGNATURE_ALGORITHM, TextCodec.BASE64.encode(secret))
                .setExpiration(new Date(System.currentTimeMillis() + timetoLive))
                .compact();
    }
}
