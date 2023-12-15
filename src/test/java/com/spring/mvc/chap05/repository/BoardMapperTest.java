package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardMapperTest {
    @Autowired
    BoardMapper boardMapper;

    // 게시물을 300개 저장
//    @Test
//    @DisplayName("게시물 300개를 저장해야 한다")
//    void bulkInsertTest() {
//        //given
//        for (int i = 1; i <=300 ; i++){
//            Board b = Board.builder()
//                    .content("테스트용 내용 " + i)
//                    .title("테스트용 제목 " + i)
//                    .build();
//            boardMapper.save(b);
//        }
//        //when
//
//        //then
//    }

    @Test
    @DisplayName("게시물을 전체 조회하면 303개의 개시물이 조회된다.")
    void findAllTest() {
        //given
        //when
        List<Board> boardList = boardMapper.findAll();

        //then
        assertEquals(303, boardList.size());
    }
    
    @Test
    @DisplayName("30번 게시물의 제목에는 19가 포함되어있다")
    void findOneTest() {
        //given
        
        //when
        Board b = boardMapper.findOne(30);
        //then
        assertTrue(b.getTitle().contains("19"));
    }

    @Test
    @DisplayName("29번 게시물을 삭제하고 다시 조회하면 조회되지 않아야 한다.")
    @Transactional
    @Rollback
    void deleteTest() {
        //테스트는 몇번을 돌려도 같은 결과가 나와야 한다.
        //given
        int boardNo = 29;
        //when
        boolean b = boardMapper.delete(boardNo);
        Board board = boardMapper.findOne(boardNo);
        //then
        assertTrue(b);
        assertNull(board);
    }

}