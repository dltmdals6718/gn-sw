package com.example.demo.controller;


import com.example.demo.KaKaoToken;
import com.example.demo.domain.Member;
import com.example.demo.kakaoLogin.KaKaoUser;
import com.example.demo.repository.MemberRepository;
import com.example.demo.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class KakaoLoginController {

    private final KaKaoToken kaKaoToken;
    private final KaKaoUser kaKaoUser;
    private final MemberRepository memberRepository; // 추후에 service AutoWired해야할듯

    @GetMapping("kakao/test")
    public String kakaoOauth(@RequestParam(name="code", required = false) String ingaCode, HttpServletRequest request) {

        Map<String, String> token = kaKaoToken.getToken(ingaCode);
        Member member = kaKaoUser.login(token.get("access_token"));

        Member findMember = memberRepository.findById(member.getId()).orElse(null);
        if(findMember==null)
            memberRepository.save(member);

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);
        return "redirect:/";
    }

    @GetMapping("/")
    public String home(@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {

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