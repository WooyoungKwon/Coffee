package com.date.coffee.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String userId;
    private String password;
    private String name;
    private String email;

    @Enumerated(EnumType.STRING) // 숫자가 아닌 문자 그대로 데이터가 저장됨
    private Role role;

    @CreationTimestamp // 생성 시간
    private LocalDateTime localDateTime;

    @OneToMany(mappedBy = "member")
    private List<Cafe> cafes = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Photo> photos = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();
}
