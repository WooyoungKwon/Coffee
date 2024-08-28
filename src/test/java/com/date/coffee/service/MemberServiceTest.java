package com.date.coffee.service;

import com.date.coffee.domain.Member;
import com.date.coffee.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired private MemberService memberService;

    @Test
    public void save() throws Exception{
        //given
        Member member = new Member();

        member.setUserId("test");
        member.setPassword("test");

        //when
        Long id = memberService.save(member);
        Member member2 = memberService.findById(id);
        //then

        assertEquals(member.getUserId(), member2.getUserId());
    }


    @Test
    public void update() throws Exception{
        //given

        //when

        //then
    }
}