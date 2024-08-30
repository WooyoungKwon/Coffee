package com.date.coffee.controller;

import com.date.coffee.domain.Address;
import com.date.coffee.domain.Cafe;
import com.date.coffee.dto.CafeDto;
import com.date.coffee.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;

    @GetMapping("/cafes/new")
    public String createCafe(Model model) {
        model.addAttribute("cafe", new CafeDto());
        return "cafe/createCafe";
    }

    @PostMapping("/cafes/new")
    public String createCafe(CafeDto cafeDto) {
        Cafe cafe = new Cafe();
        cafe.setName(cafeDto.getName());

        Address address = new Address(cafeDto.getCity(), cafeDto.getStreet(), cafeDto.getZipcode());

        cafe.setAddress(address);
        cafe.setVibe(cafeDto.getVibe());
        cafe.setRecommendedCoffee(cafeDto.getRecommendedCoffee());
        cafe.setCoffeeBean(cafeDto.getCoffeeBean());
        cafe.setCreatedAt(cafeDto.getCreatedAt());

        cafeService.save(cafe);
        return "redirect:/";
    }
}
