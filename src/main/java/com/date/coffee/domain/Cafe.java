package com.date.coffee.domain;

import com.date.coffee.dto.CafeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Cafe {

    @Id @GeneratedValue
    @Column(name = "cafe_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    private String vibe;
    private String recommendedCoffee;
    private String coffeeBean;
    private int rating;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarks = new ArrayList<>();

    // JPA 규칙으로 기본 생성자 만들어야 함.
    // 대신 사용하지 못하도록 protected 선언, private은 안되지만 protected는 허용해줌.
    protected Cafe() {}

    public Cafe(CafeDto cafeDto) {
        this.name = cafeDto.getName();
        this.address = new Address(cafeDto.getCity(), cafeDto.getStreet(), cafeDto.getZipcode());
        this.vibe = cafeDto.getVibe();
        this.recommendedCoffee = cafeDto.getRecommendedCoffee();
        this.coffeeBean = cafeDto.getCoffeeBean();
    }
}
