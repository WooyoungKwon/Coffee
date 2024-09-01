package com.date.coffee.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

@Getter @Setter
public class SecurityUser extends User {

    private Member member;

    public SecurityUser(Member member) {
        super(member.getUsername(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole()));

        this.member = member;
    }
}
