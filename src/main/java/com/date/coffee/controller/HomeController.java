package com.date.coffee.controller;

import com.date.coffee.domain.Cafe;
import com.date.coffee.domain.Member;
import com.date.coffee.domain.Photo;
import com.date.coffee.domain.Role;
import com.date.coffee.service.CafeService;
import com.date.coffee.service.MemberService;
import com.date.coffee.service.PhotoService;
import com.date.coffee.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CafeService cafeService;
    private final MemberService memberService;
    private final PhotoService photoService;
    private final S3Service s3Service;

    @RequestMapping("/")
    public String home(Model model) {
        List<Cafe> cafes = cafeService.findAll();

        // 가장 최근에 저장된 객체 3개만 가져오기
        List<Cafe> recentCafes = cafes.stream()
                .sorted(Comparator.comparing(Cafe::getCreatedAt).reversed())
                .limit(3)
                .toList();

        Map<Long, String> cafeS3Keys = recentCafes.stream()
                // cafeId를 Key 값, photoUrl을 value 값으로 사용하는 Map 생성
                .collect(Collectors.toMap(Cafe::getId, cafe -> s3Service.getPresignedImageUrl(
                        photoService.findS3KeyByCafeId(cafe.getId()))));

        // 로그인하기 귀찮아서 만든 테스트용 계정
        Member member = new Member();
        member.setUsername("1");
        member.setPassword("1");
        member.setName("1");
        member.setRole(Role.ROLE_ADMIN);
        memberService.signUp(member);

        model.addAttribute("cafes", recentCafes);
        model.addAttribute("cafeS3Keys", cafeS3Keys);


        return "home";
    }
}
