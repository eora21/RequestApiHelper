package com.eora21.request_api_helper.service;

import com.eora21.request_api_helper.model.GatewayUser;
import com.eora21.request_api_helper.model.GitEmailDto;
import com.eora21.request_api_helper.util.RequestApiHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class OAuth2GithubUserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Value("${github.token}")
    private String githubToken;

    private final RequestApiHelper requestApiHelper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        HttpHeaders headers = setHeader();

        List<GitEmailDto> response = requestApiHelper.getResponse(
                "https://api.github.com/user/emails", HttpMethod.GET, headers,
                new ParameterizedTypeReference<>() {
                });

        String email = findPrimaryEmail(response);

        System.out.println(email);

        return new GatewayUser("test", null);
    }

    private HttpHeaders setHeader() {
        HttpHeaders headers = requestApiHelper.getDefaultHeaders();
        headers.setAccept(List.of(MediaType.valueOf("application/vnd.github+json")));
        headers.setBearerAuth(githubToken);
        headers.set("X-GitHub-Api-Version", "2022-11-28");
        return headers;
    }

    private static String findPrimaryEmail(List<GitEmailDto> response) {
        return response.stream()
                .filter(GitEmailDto::getPrimary)
                .findAny()
                .orElseThrow(IllegalAccessError::new)
                .getEmail();
    }
}
