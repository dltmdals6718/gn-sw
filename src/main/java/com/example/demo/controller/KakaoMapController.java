package com.example.demo.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class KakaoMapController {

    private final String uri = "https://dapi.kakao.com/v2/local/search/keyword.json?query={query}&page={page}";

    @GetMapping("/map/{search}/{page}")
    public Map ss(@PathVariable String search, @PathVariable String page) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "KakaoAK ced4991c0aeab040d5182416037bc9cc");

        Map<String, String> params = new HashMap<String, String>();
        params.put("query", search);
        params.put("page", page);

        System.out.println("pa = " + params);
        // 응답 본문의 meta에 있는 total_count보다 page가 넘어서면 Bad Request 뜸


        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Map> response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, Map.class, params);
        //응답의 meta.is_end가 true라면 다음 페이지 이동 가능

        System.out.println("response = " + response);
        return response.getBody();
    }
}
