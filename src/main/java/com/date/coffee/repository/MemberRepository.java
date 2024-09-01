package com.date.coffee.repository;

import com.date.coffee.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByName(String name);
    boolean existsByUsername(String username);
    Optional<Member> findByUsername(String username);
}
