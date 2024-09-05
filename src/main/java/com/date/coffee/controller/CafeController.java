package com.date.coffee.controller;

import com.date.coffee.domain.Cafe;
import com.date.coffee.domain.Member;
import com.date.coffee.dto.CafeDto;
import com.date.coffee.service.CafeService;
import com.date.coffee.service.MemberService;
import com.date.coffee.service.PhotoService;
import com.date.coffee.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;
    private final S3Service s3Service;
    private final MemberService memberService;
    private final PhotoService photoService;

    @GetMapping("/cafe/new")
    public String createCafe(Model model) {
        model.addAttribute("cafe", new CafeDto());
        return "cafe/createCafe";
    }

    @PostMapping("/cafe/new")
    public String createCafe(@RequestParam("cafeImages")List<MultipartFile> files, CafeDto cafeDto) throws IOException {
        Cafe cafe = new Cafe(cafeDto);
        cafeService.save(cafe);

        // 이미지 저장
        String username = memberService.getSessionUser().getName();
        Member member = memberService.findByUsername(username);
        for (MultipartFile file : files) {
            if(file.isEmpty()) {
                return "redirect:/";
            }
            String s3Key = s3Service.upload(file);
            String fileUrl = "https://" + "wycoffeebucket" + ".s3.amazonaws.com/" + s3Key;
            photoService.save(cafe, member, s3Key, fileUrl);

            System.out.println(fileUrl + " 이미지 저장");
        }
        return "redirect:/";
    }

    @GetMapping("/cafe")
    public String listCafes(Model model) {
        model.addAttribute("cafes", cafeService.findAll());
        return "cafe/cafeList";
    }
}
