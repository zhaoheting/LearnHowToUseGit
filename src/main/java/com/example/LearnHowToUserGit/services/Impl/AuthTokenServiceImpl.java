package com.example.LearnHowToUserGit.services.Impl;

import com.example.LearnHowToUserGit.beans.ZhtClaims;
import com.example.LearnHowToUserGit.services.ConfigService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthTokenServiceImpl {

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
    private static final int MILLSECOND = 1000;
    ConfigService configService;

    public AuthTokenServiceImpl(ConfigService configService) {
        this.configService = configService;
    }

    public String createToken(Claims claims) {
        long currentTime = System.currentTimeMillis();
        int timetoLive = configService.getTimeToLive() * MILLSECOND;
        String secret = configService.getAuthTokenSecret();//不知道是什么用
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(currentTime))//jwt的签发时间
                .signWith(SIGNATURE_ALGORITHM, TextCodec.BASE64.encode(secret))//设置签名使用的签名算法和签名使用的秘钥
                .setExpiration(new Date(System.currentTimeMillis() + timetoLive))//设置过期时间,unit is mill second.
                .compact();//就开始压缩为xxxxxxxxxxxxxx.xxxxxxxxxxxxxxx.xxxxxxxxxxxxx这样的jwt
    }

    public ZhtClaims getUserTokenClaims(String jwt, Boolean isSigned) {
        String secret = configService.getAuthTokenSecret();
        return new ZhtClaims((Claims) parseToken(jwt, isSigned, secret).getBody());
    }

    public Jwt parseToken(String jwt, Boolean isSigned, String secret) {
        JwtParser parser = Jwts.parser();
        if (isSigned && !parser.isSigned(jwt)) {
            throw new SignatureException("JWTs with no signature are not allowed.");
        }
        return parser.setSigningKey(TextCodec.BASE64.encode(secret)).parse(jwt);
    }
}