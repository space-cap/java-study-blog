package com.ezlevup.hello.service;

import com.ezlevup.hello.domain.Member;
import com.ezlevup.hello.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        //Assertions.assertEquals();
        //Member findMember = memberService.findOne(saveId).get();

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        //assertThrows(IllegalAccessError.class, ()-> memberService)


//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalAccessError e) {
//
//        }

        // then
    }
}