package com.date.coffee.service;

import com.date.coffee.domain.Cafe;
import com.date.coffee.domain.Member;
import com.date.coffee.domain.Photo;
import com.date.coffee.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRepository photoRepository;

    public void save(Cafe cafe, Member member, byte[] photoUrl) {
        Photo photo = new Photo();
        photo.setCafe(cafe);
        photo.setMember(member);
        photo.setPhotoUrl(photoUrl);

        photoRepository.save(photo);
    }
}
