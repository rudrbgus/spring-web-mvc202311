package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SignUpRequsetDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.spring.mvc.chap05.service.LoginResult.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원정보를 전달하면 비밀번호가 암호화되어 디비에 저장된다.")
    void joinTest() {
        //given
        SignUpRequsetDTO dto = SignUpRequsetDTO.builder()
                .account("abc")
                .password("abc")
                .name("abc")
                .email("abc123444@gmail.com")
                .build();
        //when
        boolean flag = memberService.join(dto);
        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("계정명이 abc 회원의 로그인시도 결과를 상황별로 검증한다")
    void loginTest() {
        //given
        LoginRequestDTO dto = LoginRequestDTO.builder()
                .account("abc")
                .password("abc")
                .build();
        //when
        LoginResult result = memberService.authenticate(dto);

        //then
        assertEquals(SUCCESS, result);
    }


}