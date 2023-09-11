package com.example.demo.kakaoLogin;

import com.example.demo.domain.Member;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KaKaoUser {
    private final String loginUri = "https://kapi.kakao.com/v2/user/me";
    private final String logoutUri = "";

    public Member login(String access_token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + access_token);
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");


        String str = "property_keys=[\"kakao_account.email\", \"kakao_account.profile\"]";
        HttpEntity<String> request = new HttpEntity<>(str, httpHeaders);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(loginUri, request, String.class);

        //kakao_account.profile.nickname, kakao_account.email
        JSONObject jsonObject = new JSONObject(stringResponseEntity.getBody());
        JSONObject kakao_account = jsonObject.getJSONObject("kakao_account");
        JSONObject profile = kakao_account.getJSONObject("profile");
        Long id = jsonObject.getLong("id");
        String nickName = profile.getString("nickname");
        String email = kakao_account.getString("email");

        System.out.println("jsonObject = " + jsonObject);
        Member member = new Member();
        member.setId(id);
        member.setNickname(nickName);
        member.setEmail(email);
        return member;
    }

    public void logout() {

    }
}
