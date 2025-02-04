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

    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private String name;
    private String email;
    private int phoneNumber;

    @Enumerated(EnumType.STRING) // 숫자가 아닌 문자 그대로 데이터가 저장됨
    private Role role = Role.ROLE_ADMIN;


    @CreationTimestamp // 생성 시간
    private LocalDateTime localDateTime;

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Cafe> cafes = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Photo> photos = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Bookmark> bookmarks = new ArrayList<>();

    public void addCafe(Cafe cafe) {
        cafes.add(cafe);
        cafe.setMember(this);
    }

    // orphanRemoval 속성 설정으로 자식 객체는 DB에서 완전히 삭제
    public void removeCafe(Cafe cafe) {
        cafes.remove(cafe);
        // cascade 설정
        // cafe.setMember(null);
    }

    public int addReview(Review review) {
        reviews.add(review);
        return reviews.size();
    }
}