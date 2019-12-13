package com.example.LearnHowToUserGit.interceptors;

import com.example.LearnHowToUserGit.beans.ZhtClaims;
import com.example.LearnHowToUserGit.dao.CacheAccessUtils;
import com.example.LearnHowToUserGit.services.Impl.AuthTokenServiceImpl;
import com.example.LearnHowToUserGit.services.utils.KeyService;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    protected final AuthTokenServiceImpl authTokenService;
    protected final CacheAccessUtils cacheAccessUtils;
    protected final KeyService cacheKeyService;

    public AuthenticationInterceptor(AuthTokenServiceImpl authTokenService, CacheAccessUtils cacheAccessUtils, KeyService cacheKeyService) {
        this.authTokenService = authTokenService;
        this.cacheAccessUtils = cacheAccessUtils;
        this.cacheKeyService = cacheKeyService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String jwt = request.getHeader("User-token");
        if (jwt == null) {
            throw new RuntimeException("invalid token.");
        } else {
            validateJwt(jwt);
        }
        return true;
    }

    public void validateJwt(String jwt) {
        //decode the token into claims.
        ZhtClaims claims = authTokenService.getUserTokenClaims(jwt, true);
        validateClaims(claims);
    }

    private void validateClaims(ZhtClaims claims) {
        //Validate the cache key of the claims.
        String username = claims.getUsername();
        String idKey = claims.getIdKey();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(idKey)) {
            throw new RuntimeException("Invalid token");
        }
        //Validate if the cache key is in the cache.
        if(!cacheAccessUtils.containKey(cacheKeyService.generateUserKey(idKey, username))){
            throw new RuntimeException("Token expired.");
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
    }
}
