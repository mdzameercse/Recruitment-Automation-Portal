package com.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())   // disable CSRF for simplicity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll() // allow all API routes
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form.disable())  // disable login page
                .httpBasic(httpBasic -> httpBasic.disable()); // disable basic auth
        return http.build();
    }
}
