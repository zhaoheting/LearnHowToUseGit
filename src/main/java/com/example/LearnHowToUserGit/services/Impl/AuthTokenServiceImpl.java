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
        long currentTime = System.currentTimeMillis();
        claims.put("createdTime", currentTime);
        int timetoLive = configService.getTimeToLive();
        String secret = "UTF-8";//不知道是什么用
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(currentTime))//jwt的签发时间
                .signWith(SIGNATURE_ALGORITHM, TextCodec.BASE64.encode(secret))////设置签名使用的签名算法和签名使用的秘钥
                .setExpiration(new Date(System.currentTimeMillis() + timetoLive))////设置过期时间
                .compact();////就开始压缩为xxxxxxxxxxxxxx.xxxxxxxxxxxxxxx.xxxxxxxxxxxxx这样的jwt
    }
}
