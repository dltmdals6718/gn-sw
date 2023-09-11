package com.example.demo.controller;


import com.example.demo.KaKaoToken;
import com.example.demo.KaKaoUser;
import com.example.demo.Member;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    private final KaKaoToken kaKaoToken;
    private final KaKaoUser kaKaoUser; // 유저 클래스 싱글톤으로하면 문제있으니 추후에 수정하자.
    private Map<Long, Member> repository = new HashMap<>();
    @Autowired
    public TestController(KaKaoToken kaKaoToken, KaKaoUser kaKaoUser) {
        this.kaKaoToken = kaKaoToken;
        this.kaKaoUser = kaKaoUser;
    }

    @GetMapping("kakao/test")
    public String kakaoOauth(@RequestParam(name="code", required = false) String ingaCode, HttpServletResponse response) {

        Map<String, String> token = kaKaoToken.getToken(ingaCode);
        Member member = kaKaoUser.getUser(token.get("access_token")); // id=1L, nickname, email 처리

        // 로그인 성공 및 실패 처리, 유저 등록 및 찾기 로직
        // ...

        //신규라고만 가정함.
        repository.put(member.getId(), member);
        Cookie idCookie = new Cookie("memberId", String.valueOf(member.getId()));
        idCookie.setPath("/");
        response.addCookie(idCookie);

        return "redirect:/";
    }

    @GetMapping("/")
    public String home(@CookieValue(name="memberId", required = false) Long memberId, Model model) {
        if(memberId==null)
            return "home";
        else {
            Member loginMember = repository.get(memberId);
            if(loginMember==null)
                return "home";
            else {
                model.addAttribute("member", loginMember);
                return "loginHome";
            }
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("memberId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}

/*
인가 코드 받기
https://kauth.kakao.com/oauth/authorize
?client_id={RestAPIKey}
&redirect_uri={Redirect_uri}
&response_type=code
*/