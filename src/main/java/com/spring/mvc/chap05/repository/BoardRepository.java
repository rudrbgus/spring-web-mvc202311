package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;

import java.util.List;

public interface BoardRepository {

    boolean save(Board board);

    boolean delete(int stuNum);

    Board findOne(int stuNum);

    List<Board> findAll();
}
