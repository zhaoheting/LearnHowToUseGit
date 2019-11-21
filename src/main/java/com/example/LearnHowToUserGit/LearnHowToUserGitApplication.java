package com.example.LearnHowToUserGit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@ImportResource("/integration/integration.xml")
public class LearnHowToUserGitApplication {

    public static void main(String[] args) throws IOException {
//        SpringApplication.run(LearnHowToUserGitApplication.class, args);
        ConfigurableApplicationContext ctx = new SpringApplication(LearnHowToUserGitApplication.class).run(args);
        System.out.println("Hit Enter to terminate");
        System.in.read();
        ctx.close();
    }

    /**
     * Test how to config embedded tomcat server manually.
     *
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                //factory.setPort(8082);
                Set<ErrorPage> errorPages = new HashSet<>();
                //Define error page.
                errorPages.add(new ErrorPage(HttpStatus.NOT_FOUND, "/error400Page"));
                factory.setErrorPages(errorPages);
            }
        };
    }


}
