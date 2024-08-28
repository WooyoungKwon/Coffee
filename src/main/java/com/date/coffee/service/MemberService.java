package com.date.coffee.service;

import com.date.coffee.domain.Cafe;
import com.date.coffee.domain.Member;
import com.date.coffee.repository.MemberRepository;
import com.date.coffee.service.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(Member member) {
        Member saveMember = memberRepository.save(member);
        return saveMember.getId();
    }

    @Transactional
    public void addCafe(Long id, Cafe cafe) {
        Member member = memberRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        member.addCafe(cafe);
    }

    @Transactional
    public void removeCafe(Long id, Cafe cafe) {
        Member member = memberRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        member.removeCafe(cafe);
    }

    @Transactional
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    // 변경 감지
    @Transactional
    public void update(Long id, MemberDto memberDto) {
        Member findMember = memberRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        findMember.setUserId(memberDto.getUserId());
        findMember.setPassword(memberDto.getPassword());
        findMember.setName(memberDto.getName());
        findMember.setEmail(memberDto.getEmail());
        findMember.setPhoneNumber(memberDto.getPhoneNumber());
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Member> findByUsername(String name) {
        return memberRepository.findByName(name);
    }
}
