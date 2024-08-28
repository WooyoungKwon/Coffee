package com.date.coffee.service;

import com.date.coffee.domain.Cafe;
import com.date.coffee.repository.CafeRepository;
import com.date.coffee.service.dto.CafeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeService {

    private final CafeRepository cafeRepository;

    @Transactional
    public Long save(Cafe cafe) {
        Cafe save = cafeRepository.save(cafe);
        return save.getId();
    }

    @Transactional
    public void delete(Long id) {
        cafeRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, CafeDto cafeDto) {
        Cafe findCafe = cafeRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        findCafe.setName(cafeDto.getName());
        findCafe.setAddress(cafeDto.getAddress());
        findCafe.setRecommendedCoffee(cafeDto.getRecommendedCoffee());
        findCafe.setCoffeeBean(findCafe.getCoffeeBean());
        findCafe.setRating(cafeDto.getRating());
    }

    public Cafe findById(Long id) {
        return cafeRepository.findById(id).orElse(null);
    }

    public List<Cafe> findByName(String name) {
        return cafeRepository.findByName(name);
    }

    public List<Cafe> findAll() {
        return cafeRepository.findAll();
    }
}
