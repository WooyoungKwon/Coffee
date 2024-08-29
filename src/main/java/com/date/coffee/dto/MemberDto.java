package com.date.coffee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDto {
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;
    private int phoneNumber;
}
