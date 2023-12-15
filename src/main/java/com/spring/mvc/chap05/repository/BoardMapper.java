package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.common.Search;
import com.spring.mvc.chap05.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BoardMapper {
    boolean save(Board board);

    boolean delete(int stuNum);

    Board findOne(int stuNum);

    void increaseViewCount(int stuNum);

    List<Board> findAll(Search page);

    // 총 게시물 수 구하기
    int count(Search search);
}
