package com.date.coffee.service;

import com.date.coffee.domain.Member;
import com.date.coffee.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long save(Member member) {
        Member saveMember = memberRepository.save(member);
        return saveMember.getId();
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    // 변경 감지
    public void update(Long id, String userId, String password, String name, String email) {
        Member findMember = memberRepository.findById(id).orElseThrow();
        findMember.setUserId(userId);
        findMember.setPassword(password);
        findMember.setName(name);
        findMember.setEmail(email);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
