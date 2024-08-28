package com.date.coffee.repository;

import com.date.coffee.domain.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CafeRepository extends JpaRepository<Cafe, Long> {
    List<Cafe> findByName(String name);
}
