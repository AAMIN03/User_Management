package com.springrest.User_Management.Configurations;


import com.springrest.User_Management.services.UserService;
import com.springrest.User_Management.services.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.springrest.User_Management")
@org.springframework.context.annotation.Configuration
public class AppConfig {
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }
}
