package com.date.coffee.service;

import com.date.coffee.domain.Member;
import com.date.coffee.dto.CustomUserDetails;
import com.date.coffee.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Member> optionalMember = memberRepository.findByUsername(username);
//        Member member = optionalMember.orElseThrow(() -> new UsernameNotFoundException(username));
        Member member = memberRepository.findByUsername(username);
        if (member != null) {
            return new CustomUserDetails(member);
        }

        return null;
    }
}
