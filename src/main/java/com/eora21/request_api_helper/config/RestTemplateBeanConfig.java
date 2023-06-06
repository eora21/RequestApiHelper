package com.eora21.request_api_helper.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateBeanConfig {
    private static final long TIME_OUT_SECONDS = 3L;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setReadTimeout(Duration.ofSeconds(TIME_OUT_SECONDS))
                .setConnectTimeout(Duration.ofSeconds(TIME_OUT_SECONDS))
                .build();
    }
}
