package com.example.LearnHowToUserGit.config;

import com.example.LearnHowToUserGit.interceptors.AuthenticationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {

    public static final String[] excludePatterns = {"/login", ""};
    protected final AuthenticationInterceptor authenticationInterceptor;

    public WebApplicationConfig(AuthenticationInterceptor authenticationInterceptor) {
        this.authenticationInterceptor = authenticationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePatterns);
        //.order(authenticationInterceptor);???
    }
}
