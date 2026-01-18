package com.fav.web.demo1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class configuration {

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/apiPubic").permitAll()
                                .requestMatchers("/apiBoss").hasRole("BOSS")
                );
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsManager() {

        UserDetails Anna = User.builder()
                .username("Ania")
                .password("123")
                .roles("BOSS")
                .build();

        UserDetails Dima = User.builder()
                .username("Dima")
                .password(encoder().encode("123"))
                .roles("BOSS")
                .build();

        UserDetails Ivan = User.builder()
                .username("Ivan")
                .password("321")
                .roles("WORKERS")
                .build();

        UserDetails Kiril = User.builder()
                .username("Kiril")
                .password("321")
                .roles("WORKERS")
                .build();

        return new InMemoryUserDetailsManager(Anna,Dima,Ivan,Kiril);
    }
}
