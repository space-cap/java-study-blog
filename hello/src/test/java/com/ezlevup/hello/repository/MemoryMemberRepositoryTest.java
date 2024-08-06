package com.ezlevup.hello.repository;

import com.ezlevup.hello.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findByid(member.getId()).get();
        //System.out.println("result = " + (result == member));

        //Assertions.assertEquals(result, member);

        //Assertions.assertThat

    }


    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //Member result = repository.findByName("sprint1").get();

        Optional<Member> memberOptional = repository.findByName("sprint1");
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            // member를 사용하여 필요한 작업 수행
        } else {
            // 값이 없을 때의 처리
            System.out.println("해당 이름의 멤버를 찾을 수 없습니다.");
        }


    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> list = new ArrayList<>();

    }

}
