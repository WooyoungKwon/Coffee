package com.date.coffee.service.dto;

import com.date.coffee.domain.Address;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CafeDto {
    private Long id;
    private String name;
    private Address address;
    private String recommendedCoffee;
    private String coffeeBean;
    private int rating;
}
