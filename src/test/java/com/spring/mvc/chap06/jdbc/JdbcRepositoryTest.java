package com.spring.mvc.chap06.jdbc;

import com.spring.mvc.chap06.entity.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class JdbcRepositoryTest {

    @Autowired
    JdbcRepository repository;

    @Test
    @DisplayName("데이터베이스 접속에 성공해야 한다.")
    void connectTest(){
        try {
            Connection connection = repository.getConnection();

            assertNotNull(connection); // 커넥션이 Null이 아닐거라고 단언한다.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("사람 객체정보를 데이터베이스에 삽입해야 한다.")
    void saveTest(){
        //given
        Person p = new Person("1", "망둥이", 10);

        //when
        repository.save(p);
    }

    @Test
    @DisplayName("회원번호가 1인 회원의 이름과 나이를 수정해야 한다")
    void updateTest(){
        //given
        String id = "1";

        String newName = "개구리";
        int newAge = 15;
        //when
        Person person = new Person(id, newName, newAge);
        repository.update(person);
    }

    @Test
    @DisplayName("회원번호가 1인 회원을 삭제한다")
    void deleteTest(){
        //given
        String id = "1";

        //when
        repository.delete(id);
    }

    @Test
    @DisplayName("랜덤 회원 아이디를 가진 회원을 10명 등록해야 한다.")
    void bulkInsertTest(){
        for(int i=0; i<10; i++){
            Person person = new Person(""+Math.random(), "랄랄라"+i, i+10);
            repository.save(person);
        }
    }

    @Test
    @DisplayName("전체 회원을 조회하면 회원 리스트의 수가 10개이다.")
    void findAllTest(){
        List<Person> all = repository.findAll();
        all.forEach(System.out::println);

    }

    @Test
    @DisplayName("특정 아이디 회원을 조회하면 하나의 회원만 조회된다")
    void findOneTest(){
        String id = "0.0369929458194308";

        Person one = repository.findOne(id);
        System.out.println("one = " + one);
    }
}