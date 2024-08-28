package com.date.coffee.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    // JPA 규칙상 기본 생성자는 만들어야 함. JPA 라이브러리가 리플랙션 같은 기술을 사용할 수 있도록 해야 하기 때문.
    protected Address() {}

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
