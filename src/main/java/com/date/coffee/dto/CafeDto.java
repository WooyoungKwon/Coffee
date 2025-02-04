package com.date.coffee.dto;

import com.date.coffee.domain.Address;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CafeDto {
    private Long id;
    private String name;
    private String description;

//    private Address address;
    private String city;
    private String street;
    private String zipcode;

    private String vibe;
    private String recommendedCoffee;
    private String coffeeBean;
    private LocalDateTime createdAt;
}
