package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class KaKaoUser {
    private final String uri = "https://kapi.kakao.com/v2/user/me";

    public Member getUser(String access_token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + access_token);
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");


        String str = "property_keys=[\"kakao_account.email\", \"kakao_account.profile\"]";
        HttpEntity<String> request = new HttpEntity<>(str, httpHeaders);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(uri, request, String.class);

        //kakao_account.profile.nickname, kakao_account.email
        JSONObject jsonObject = new JSONObject(stringResponseEntity.getBody());
        JSONObject kakao_account = jsonObject.getJSONObject("kakao_account");
        JSONObject profile = kakao_account.getJSONObject("profile");
        String nickName = profile.getString("nickname");
        String email = kakao_account.getString("email");

        Member member = new Member();
        member.setId(1L);
        member.setNickname(nickName);
        member.setEmail(email);
        return member;
    }
}
