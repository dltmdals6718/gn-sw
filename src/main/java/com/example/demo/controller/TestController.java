package com.example.demo.controller;


import com.example.demo.KaKaoToken;
import com.example.demo.domain.Member;
import com.example.demo.kakaoLogin.KaKaoUser;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final KaKaoToken kaKaoToken;
    private final KaKaoUser kaKaoUser; // 유저 클래스 싱글톤으로하면 문제있으니 추후에 수정하자.

    @GetMapping("kakao/test")
    public String kakaoOauth(@RequestParam(name="code", required = false) String ingaCode, HttpServletResponse response) {

        Map<String, String> token = kaKaoToken.getToken(ingaCode);
        Member member = kaKaoUser.login(token.get("access_token")); // id=1L, nickname, email 처리
        System.out.println("member = " + member);
        return "redirect:/";
    }

    @GetMapping("/")
    public String home(@CookieValue(name="memberId", required = false) Long memberId, Model model) {
        return "home";
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("memberId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}