package com.date.coffee.dto;

import com.date.coffee.domain.Address;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CafeDto {
    private Long id;
    private String name;
    private String image;

//    private Address address;
    private String city;
    private String street;
    private String zipcode;

    private String vibe;
    private String recommendedCoffee;
    private String coffeeBean;
}
