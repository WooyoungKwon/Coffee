package com.date.coffee.service;

import com.date.coffee.domain.Cafe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CafeServiceTest {

    @Autowired CafeService cafeService;

    @Test
    public void 카페저장() throws Exception{
        //given
        Cafe cafe = new Cafe();
        cafe.setName("123");
        cafe.setRecommendedCoffee("latte");

        //when
        cafeService.save(cafe);

        //then
        assertEquals("latte", cafeService.findByName("123").get(0).getRecommendedCoffee());
    }

    @Test
    public void 카페찾기() throws Exception{
        //given
        Cafe cafe = new Cafe();
        cafe.setName("12");
        cafe.setCoffeeBean("aa");
        cafeService.save(cafe);

        //when
        List<Cafe> cafeList = cafeService.findByName("12");

        //then
        assertEquals("aa", cafeList.get(0).getCoffeeBean());
    }

}