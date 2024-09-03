package com.date.coffee.controller;

import com.date.coffee.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collection;
import java.util.Iterator;

// html에 화면에 찍을 때나 th:if 작성할 때 유용함
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final MemberService memberService;

    @ModelAttribute("role")
    public String role() {
        Authentication sessionUser = memberService.getSessionUser();
        Collection<? extends GrantedAuthority> authorities = sessionUser.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        return auth.getAuthority();
    }
}
