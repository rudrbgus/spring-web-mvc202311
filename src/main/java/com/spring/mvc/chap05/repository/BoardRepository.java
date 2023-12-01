package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;

import java.util.List;

// 게시판 CRUD
public interface BoardRepository {
    // 전체 조회
    List<Board> findAll();

    // 상세 조회
    Board findOne(int boardNo);

    // 게시물 등록
     boolean save(Board board);

     boolean deleteByNo(int boardNo);
}
