package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository("dbRepo")
@Primary
@RequiredArgsConstructor
public class ScoreJdbcRepository implements ScoreRepository{
    private final JdbcTemplate template;
    @Override
    public List<Score> findAll(String sort) {
        String sql = "select * from tbl_score";
        switch (sort){
            case "num":
                sql += " Order by stu_num";
                break;
            case "name":
                sql += " Order by stu_name";
                break;
            case "avg":
                sql += " Order by average DESC";
                break;
        }
        return template.query(sql, (rs, rn) -> new Score(rs));
    }

    @Override
    public List<Score> findAll() {
        return Collections.emptyList();
    }

    @Override
    public boolean save(Score score) {
        String sql = "insert into tbl_score "+
                "(stu_name, kor, eng, math, total, average, grade) "+
                "values (?, ?, ?, ?, ?, ?, ?)";

        return template.update(sql, score.getName(), score.getKor(), score.getEng(), score.getMath(), score.getTotal(), score.getAverage(), score.getGrade().toString()) == 1;
    }

    @Override
    public boolean delete(int stuNum) {
        String sql = "Delete from tbl_score where stu_num = ?";
        return template.update(sql, stuNum) == 1;
    }

    @Override
    public Score findOne(int stuNum) {
        String sql = "Select * from tbl_score where stu_num = ?";
        return template.queryForObject(sql, (rs, rn) -> new Score(rs), stuNum);
    }
}
