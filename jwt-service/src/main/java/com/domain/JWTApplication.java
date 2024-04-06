package com.domain;

import com.domain.entity.UserCredential;
import com.domain.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class JWTApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JWTApplication.class, args);
        AuthService authService = context.getBean("authService", AuthService.class);
        UserCredential credential = new UserCredential();
        credential.setName("admin");
        credential.setPassword("admin");
        authService.saveUser(credential);
        log.info("user admin created ! ! !");
    }
}
