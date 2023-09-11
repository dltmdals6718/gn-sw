package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class KaKaoToken {

    private final String tokenUri = "https://kauth.kakao.com/oauth/token";
    private final String grantType = "authorization_code";
    private final String clientId = "ced4991c0aeab040d5182416037bc9cc"; // 깃허브에 올리기전에 clientId를 숨겨보자
    private final String redirectUri = "http://localhost:8080/kakao/test";

    public Map getToken(String ingaCode) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", grantType);
        map.add("client_id", clientId);
        map.add("redirect_uri", redirectUri);
        map.add("code", ingaCode);

        HttpEntity<MultiValueMap> request = new HttpEntity<>(map, httpHeaders);
        ResponseEntity<Map> stringResponseEntity = restTemplate.postForEntity(tokenUri, request, Map.class);
        return stringResponseEntity.getBody();
    }

}
