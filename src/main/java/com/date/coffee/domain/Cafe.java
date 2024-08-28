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
public class Cafe {

    @Id @GeneratedValue
    @Column(name = "cafe_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    private String recommendedCoffee;
    private String coffeeBean;
    private int rating;

    @CreationTimestamp
    private LocalDateTime localDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "cafe")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "cafe")
    private List<Menu> menus = new ArrayList<>();

    @OneToMany(mappedBy = "cafe")
    private List<Photo> photos = new ArrayList<>();


}
