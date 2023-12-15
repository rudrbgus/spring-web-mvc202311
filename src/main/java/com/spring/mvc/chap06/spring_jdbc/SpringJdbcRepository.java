package com.spring.mvc.chap06.spring_jdbc;

import com.spring.mvc.chap06.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SpringJdbcRepository {

    // 데이터 소스 설정 : 데이터베이스 접속정보 설정 (url, id, pw...)
    // application.properties 파일에 작성

    //JdbcTemplate 빈 의존성 주입
    private final JdbcTemplate template;


    // INSERT 기능
    public void save(Person person){
        String sql = "INSERT into person " +
                "(id, person_name, person_age) "+
                "Values (?, ?, ?)";
        template.update(sql, person.getId(), person.getPersonName(), person.getPersonAge());
    }

    // DELETE 기능
    public void remove(String id){
        String sql = "DELETE FROM person WHERE id = ?";
        template.update(sql, id);
    }

    // UPDATE 기능
    public void modify(Person p){
        String sql = "UPDATE person "
                +"SET person_name=?, person_age=? "+
                "Where id=?";

        template.update(sql, p.getPersonName(), p.getPersonAge(), p.getId());
    }

    // 전체 조회
    public List<Person> findAll(){
        String sql = "SELECT * from PERSON";
        // rowMapper : DB에서 조회한 것들을 객체로 어떻게 매칭할지 설정
        return template.query(sql, (rs, rn) -> new Person(rs));
    }

    // 단일 조회
    public Person findOne(String id){
        String sql = "SELECT * from person where id = ?";
        return template.queryForObject(sql, (rs, rn) -> new Person(rs), id);

    }
}