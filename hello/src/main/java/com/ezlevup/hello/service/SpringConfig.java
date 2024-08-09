package com.ezlevup.hello.service;

import com.ezlevup.hello.repository.JdbcTemplateMemberRepository;
import com.ezlevup.hello.repository.JpaMemberRepository;
import com.ezlevup.hello.repository.MemberRepository;
import com.ezlevup.hello.repository.MemoryMemberRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }


}
