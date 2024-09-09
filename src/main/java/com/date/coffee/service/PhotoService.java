package com.date.coffee.service;

import com.date.coffee.domain.Cafe;
import com.date.coffee.domain.Member;
import com.date.coffee.domain.Photo;
import com.date.coffee.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PhotoService {
    private final PhotoRepository photoRepository;

    @Transactional
    public void save(Cafe cafe, Member member, String s3Key) {
        Photo photo = new Photo();
        photo.setCafe(cafe);
        photo.setMember(member);
        photo.setS3Key(s3Key);

        photoRepository.save(photo);
    }

    public String findS3KeyByCafeId(Long id) {
        Optional<Photo> photo = photoRepository.findFirstByCafeId(id);
        return photo.map(Photo::getS3Key).orElse(null);
    }
}
