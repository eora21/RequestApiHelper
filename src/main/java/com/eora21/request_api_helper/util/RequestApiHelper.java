package com.eora21.request_api_helper.util;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RequestApiHelper {
    private final RestTemplate restTemplate;

    public HttpHeaders getDefaultHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }

    public <T, R> R getResponse(String url, HttpMethod method, HttpHeaders headers, T requestBody,
                                ParameterizedTypeReference<R> reference) {
        HttpEntity<T> requestEntity = new HttpEntity<>(requestBody, headers);
        return getBody(url, method, requestEntity, reference);
    }

    public <R> R getResponse(String url, HttpMethod method, HttpHeaders headers,
                             ParameterizedTypeReference<R> reference) {
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        return getBody(url, method, requestEntity, reference);
    }

    public <T, R> R getResponse(String url, HttpMethod method, T requestBody, ParameterizedTypeReference<R> reference) {
        return getResponse(url, method, getDefaultHeaders(), requestBody, reference);
    }

    public <R> R getResponse(String url, HttpMethod method, ParameterizedTypeReference<R> reference) {
        return getResponse(url, method, getDefaultHeaders(), reference);
    }

    private <T, R> R getBody(String url, HttpMethod method, HttpEntity<T> requestEntity,
                             ParameterizedTypeReference<R> reference) {
        ResponseEntity<R> exchange = restTemplate.exchange(
                url,
                method,
                requestEntity,
                reference);
        return exchange.getBody();
    }
}