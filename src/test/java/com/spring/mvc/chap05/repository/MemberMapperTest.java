package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberMapperTest {
    @Autowired
    MemberMapper memberMapper;

    @Autowired
    PasswordEncoder passwordEncoder;



    @Test
    @DisplayName("회원가입에 성공해야 한다")
    void saveTest() {
        //given
        Member member = Member.builder()
                .account("kuromi")
                .password(passwordEncoder.encode("abc1234!"))
                .name("쿠로미")
                .email("kuromi@gmail.com")
                .build();
        //when
        boolean save = memberMapper.save(member);

        //then
        assertTrue(save);
    }

    @Test
    @DisplayName("lesserafimr계정을 조회하면 그 회원의 이름이 라이그이어야 한다")
    void findMemberTest() {
        //given
        String acc = "lesserafim";
        //when
        Member member = memberMapper.findMember(acc);
        //then
        System.out.println("member = " + member);
        assertEquals("라이그", member.getName());
    }

    @Test
    @DisplayName("계정명이 newJeans일 경우 결과값은 false이어야 한다.")
    void duplicateTest() {
        //given
        String acc = "newjeans";
        String wrongAcc = "lesserafim";
        //when
        boolean dupulicate = memberMapper.isDupulicate("account" ,acc);
        //then
        assertFalse(dupulicate);
    }

    @Test
    @DisplayName("이메일이 abc@naver.com일 경우 결과값은 true이어야 한다.")
    void duplicateEmailTest() {
        //given
        String email = "abc@naver.com";
        //when
        boolean dupulicate = memberMapper.isDupulicate("email", email);
        //then
        assertTrue(dupulicate);
    }

    @Test
    @DisplayName("비밀번호가 암호화되어야 한다")
    void encodingTest() {
        //given
        String rawPassword = "abc1234!@";
        //when
        String encode = passwordEncoder.encode(rawPassword);
        //then

        System.out.println("rawPassword = " + rawPassword);
        System.out.println("encode = " + encode);
    }
}