package com.date.coffee.controller;

import com.date.coffee.domain.Cafe;
import com.date.coffee.domain.Member;
import com.date.coffee.domain.Photo;
import com.date.coffee.dto.CafeDto;
import com.date.coffee.service.CafeService;
import com.date.coffee.service.MemberService;
import com.date.coffee.service.PhotoService;
import com.date.coffee.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;
    private final S3Service s3Service;
    private final MemberService memberService;
    private final PhotoService photoService;
    private final S3Service s3Service;

    Set<String> imageExtensionSet = Set.of("jpg", "jpeg", "png");

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
            // 파일이 입력되지 않았으면 return
            if(file.isEmpty()) {
                return "redirect:/";
            }
            // 확장자가 "jpg", "jpeg", "png" 중 하나가 아니라면 저장하지 않고 return
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            if (!imageExtensionSet.contains(extension))
                return "redirect:/";
            // "카페이름 + 저장한 사람 이름 + 랜덤 id 값 + 파일 이름" 형식으로 S3에 저장
            String key = cafe.getName() + "/"  + member.getName() + "/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
            s3Service.upload(file, key);

            photoService.save(cafe, member, key);
        }
        return "redirect:/";
    }

    @GetMapping("/cafe/list")
    public String cafeList(Model model) {
        model.addAttribute("cafes", cafeService.findAll());

        return "cafe/cafeList";
    }

    @GetMapping("/cafe/info/{id}")
    public String cafeInfo(@PathVariable Long id, Model model) {
        Cafe cafe = cafeService.findById(id);
        List<Photo> photos = cafe.getPhotos();

        s3Service.getPresignedImageUrl()


        model.addAttribute("photos", photos);

        return "cafe/cafeInfo";
    }
}
