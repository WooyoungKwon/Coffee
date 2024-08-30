package com.date.coffee.controller;

import com.date.coffee.domain.Cafe;
import com.date.coffee.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CafeService cafeService;

    @RequestMapping("/")
    public String home(Model model) {
        List<Cafe> cafes = cafeService.findAll();

        // 가장 최근에 저장된 객체 3개만 가져오기
        List<Cafe> recentCafes = cafes.stream()
                .sorted(Comparator.comparing(Cafe::getCreatedAt).reversed())
                .limit(3)
                .toList();

        model.addAttribute("cafes", recentCafes);
        return "home";
    }
}
