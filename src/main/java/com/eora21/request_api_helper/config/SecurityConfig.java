package com.eora21.request_api_helper.config;

import com.eora21.request_api_helper.service.OAuth2GithubUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final OAuth2GithubUserService oAuth2GithubUserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated();

        http
                .oauth2Login()
                .userInfoEndpoint()
                .userService(oAuth2GithubUserService);

        return http.build();
    }
}
