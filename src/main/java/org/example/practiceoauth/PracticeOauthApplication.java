package org.example.practiceoauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class PracticeOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeOauthApplication.class, args);
    }

}
