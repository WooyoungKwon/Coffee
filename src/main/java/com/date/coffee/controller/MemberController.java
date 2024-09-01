package com.date.coffee.controller;

import com.date.coffee.domain.Member;
import com.date.coffee.dto.MemberDto;
import com.date.coffee.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String signUpForm(Model model, @RequestParam(value = "error", required = false)String error,
                             @RequestParam(value = "exception", required = false)String exception) {
        model.addAttribute("memberDto", new MemberDto());

        return "member/memberSignUp";
    }

    @PostMapping("/member/signUp")
    public String signUpMember(MemberDto memberDto) {
        Member member = new Member();
        member.setUsername(memberDto.getUsername());
        member.setPassword(memberDto.getPassword());
        member.setName(memberDto.getName());
        member.setEmail(memberDto.getEmail());

        memberService.signUp(member);

        return "redirect:/login";
    }

    @PostMapping("/member/signIn")
    public String signInMember (MemberDto memberDto) {
//        if (memberService.signIn(memberDto.getUsername(), memberDto.getPassword()))
            return "redirect:/";
//        return "redirect:/member/login";
    }
}
