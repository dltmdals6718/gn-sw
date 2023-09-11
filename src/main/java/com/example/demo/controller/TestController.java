package com.example.demo.controller;


import com.example.demo.KaKaoToken;
import com.example.demo.domain.Member;
import com.example.demo.kakaoLogin.KaKaoUser;
import com.example.demo.repository.MemberRepository;
import com.example.demo.session.SessionConst;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
    private final MemberRepository memberRepository;

    @GetMapping("kakao/test")
    public String kakaoOauth(@RequestParam(name="code", required = false) String ingaCode, HttpServletRequest request) {

        Map<String, String> token = kaKaoToken.getToken(ingaCode);
        Member member = kaKaoUser.login(token.get("access_token")); // id=1L, nickname, email 처리

        Member findMember = memberRepository.findById(member.getId()).orElse(null);
        if(findMember==null) {
            System.out.println("새로운 회원입니다.");
            memberRepository.save(member);
        } else {
            System.out.println("기존 회원입니다.");
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);
        return "redirect:/";
    }

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if(session==null) {
            return "home";
        }

        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        if(loginMember==null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session!=null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}