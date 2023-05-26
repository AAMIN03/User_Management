//package com.springrest.User_Management.Config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import java.security.PublicKey;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class securityConfig {
//
//    @Bean
//    public UserDetailsService userDetailsService (PasswordEncoder encoder){
//        UserDetails admin = User.withUsername("Aamin")
//                .password(encoder.encode("ATTit9099@"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.withUsername("Aamin2")
//                .password(encoder.encode("ATTit9099@"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin , user);
//        //return new UserInfoUserDetailsService();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/home").permitAll()
//                .and()
//                .authorizeHttpRequests().requestMatchers("/walet/**")
//                .authenticated().and().formLogin().and().build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//}
