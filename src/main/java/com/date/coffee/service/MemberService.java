package com.date.coffee.service;

import com.date.coffee.domain.Cafe;
import com.date.coffee.domain.Member;
import com.date.coffee.repository.MemberRepository;
import com.date.coffee.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    public Long signUp(Member member) {
        // 중복된 아이디가 있으면 저장 X
        if(memberRepository.existsByUsername(member.getUsername())) {
            return 0L;
        }
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        Member saveMember = memberRepository.save(member);
        return saveMember.getId();
    }

    public boolean signIn(String username, String password) {
        Optional<Member> optionalMember = memberRepository.findByUsername(username);
        if(optionalMember.isPresent()) {
            Member member = optionalMember.get();
            return bCryptPasswordEncoder.matches(password, member.getPassword());
        }
        return false;
    }

    @Transactional
    public void addCafeToMember(Long id, Cafe cafe) {
        Member member = memberRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        member.addCafe(cafe);
    }

    @Transactional
    public void removeCafeToMember(Long id, Cafe cafe) {
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
        findMember.setUsername(memberDto.getUsername());
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

    /**
     * 현재 로그인한 사용자
     * @Role
     */
    public String getSessionUserRole() {
        // 세션에서 현재 로그인한 사용자의 권한 목록 return
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        return role;
    }
}
