package com.lextereum.lextereumbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

public class SecurityConfiguration {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange()
                   .pathMatchers("/actuator/**")
                   .permitAll()
                   .anyExchange()
                   .authenticated()
                   .and()
                   .build();
    }
}
