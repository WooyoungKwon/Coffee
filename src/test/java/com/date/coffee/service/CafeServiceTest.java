package com.date.coffee.service;

import com.date.coffee.domain.Cafe;
import com.date.coffee.domain.Photo;
import com.date.coffee.dto.CafeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CafeServiceTest {

    @Autowired CafeService cafeService;

    @Test
    public void 카페저장() throws Exception{
        //given
        CafeDto cafeDto = new CafeDto();
        cafeDto.setName("123");
        cafeDto.setRecommendedCoffee("latte");
        Cafe cafe = new Cafe(cafeDto);

        //when
        Long id = cafeService.save(cafe);

        //then
        assertEquals("latte", cafeService.findById(id).getRecommendedCoffee());
    }

    @Test
    public void 카페찾기() throws Exception{
        //given
        CafeDto cafeDto = new CafeDto();
        cafeDto.setName("12");
        cafeDto.setCoffeeBean("aa");
        Cafe cafe = new Cafe(cafeDto);
        cafeService.save(cafe);

        //when
        List<Cafe> cafeList = cafeService.findByName("12");

        //then
        assertEquals("aa", cafeList.get(0).getCoffeeBean());
    }

    @Test
    public void 카페사진() throws Exception{
        Photo photo1 = new Photo();
        photo1.setS3Key("QWe");
        Photo photo2 = new Photo();
        photo2.setS3Key("QWe");
        List<Photo> photoList = new ArrayList<>();
        photoList.add(photo1);
        photoList.add(photo2);

        CafeDto cafeDto = new CafeDto();
        cafeDto.setName("123");
        cafeDto.setCoffeeBean("aa");
        Cafe cafe = new Cafe(cafeDto);
        cafe.setPhotos(photoList);

        Long id = cafeService.save(cafe);
        Cafe findCafe = cafeService.findById(id);
        System.out.println(findCafe.getPhotos());


    }

}