package com.date.coffee.controller;

import com.date.coffee.domain.Member;
import com.date.coffee.dto.MemberDto;
import com.date.coffee.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/new")
    public String createForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/memberSignup";
    }

    @PostMapping("/member/new")
    public String sighupMember (MemberDto memberDto) {
        Member member = new Member();
        member.setUserId(memberDto.getUserId());
        member.setPassword(memberDto.getPassword());
        member.setName(memberDto.getName());
        member.setEmail(memberDto.getEmail());

        memberService.save(member);

        return "redirect:/";
    }
}
