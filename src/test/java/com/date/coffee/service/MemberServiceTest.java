package com.date.coffee.service;

import com.date.coffee.domain.Cafe;
import com.date.coffee.domain.Member;
import com.date.coffee.dto.CafeDto;
import com.date.coffee.dto.MemberDto;
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
    public void 저장() throws Exception{
        //given
        Member member = new Member();

        member.setUsername("test");
        member.setPassword("test");

        //when
        Long id = memberService.signUp(member);

        //then
        assertEquals("test", memberService.findById(id).getUsername());
    }

    @Test
    public void 수정() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("test");
        member.setPassword("test");

        memberService.signUp(member);

        //when
        MemberDto memberDto = new MemberDto();
        memberDto.setUsername("123");
        memberDto.setPassword("123");
        memberService.update(member.getId(), memberDto);

        //then
        assertEquals("123", member.getUsername());
    }

    @Test
    public void 카페추가() throws Exception{
        //given
        Member member = new Member();
        member.setName("test");
        Long id = memberService.signUp(member);

        CafeDto cafeDto = new CafeDto();
        cafeDto.setName("test1");
        Cafe cafe1 = new Cafe(cafeDto);

        CafeDto cafeDto2 = new CafeDto();
        cafeDto2.setName("test2");
        Cafe cafe2 = new Cafe(cafeDto2);

        //when
        memberService.addCafeToMember(id, cafe1);
        memberService.addCafeToMember(id, cafe2);

        //then
        assertEquals(cafe1, memberService.findById(id).getCafes().get(0));
        assertEquals(member, cafe1.getMember());
        assertEquals(2, memberService.findById(id).getCafes().size());
    }

    @Test
    public void 카페삭제() throws Exception{
        //given
        Member member = new Member();
        member.setName("test");
        Long id = memberService.signUp(member);

        CafeDto cafeDto1 = new CafeDto();
        cafeDto1.setName("test1");
        Cafe cafe1 = new Cafe(cafeDto1);

        CafeDto cafeDto2 = new CafeDto();
        cafeDto2.setName("test2");
        Cafe cafe2 = new Cafe(cafeDto2);

        memberService.addCafeToMember(id, cafe1);
        memberService.addCafeToMember(id, cafe2);

        //when
        memberService.removeCafeToMember(id, cafe2);

        //then
        assertEquals(1, memberService.findById(member.getId()).getCafes().size());
    }
}