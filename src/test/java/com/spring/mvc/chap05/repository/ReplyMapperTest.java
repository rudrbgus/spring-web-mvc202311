package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.entity.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional @Rollback
class ReplyMapperTest {
    @Autowired
    BoardMapper boardMapper;
    @Autowired
    ReplyMapper replyMapper;

    @Test
    @DisplayName("")
    void bulkInsertTest() {
        //given
        for(int i = 1; i<= 100; i++){
            Board b = Board.builder()
                    .title("재밌는 글 " +i)
                    .content("응 개노잼~~ " + i)
                    .build();

            boardMapper.save(b);
        }

        for(int i =1; i<=1000; i++){
            Reply r = Reply.builder()
                    .replyText("하하호호히히~~ "+i)
                    .replyWriter("잼민이 "+i)
                    .boardNo((long)(Math.random() *100 +1))
                    .build();

            replyMapper.save(r);
        }
        //when

        //then
    }

    @Test
    @DisplayName("77번 게시물의 댓글 목록을 조회 할때 결과 사이즈는 7이다")
    void findAllTest() {
        //given
        long boardNo = 77L;
        //when
        List<Reply> replyList = replyMapper.findAll(boardNo, new Page());
        //then
        assertEquals(7, replyList.size());
        assertEquals("잼민이 12", replyList.get(0).getReplyWriter());
    }

    @Test
    @DisplayName("77번 게시물의 456번 댓글을 삭제하면 456번 댓글은 조회되지 않을 것이고"
        +" 77번을 전체조회하면 리스트의 사이즈는 6 이어야한다.")
    void deleteTest() {
        //given
        long boardNo = 77;
        long replyNo = 456;
        //when
        replyMapper.delete(replyNo);
        Reply reply = replyMapper.findOne(replyNo);

        //then
        assertNull(reply);
        assertEquals(6, replyMapper.findAll(boardNo, new Page()).size());
    }
    
    @Test
    @DisplayName("77번 게시물의 456번 댓글의 댓글 내용을 수정하면 다시 조회 했을 떄 수정된 내용이 조회된다.")
    void modifytest() {
        //given
        long replyNo = 103;
        String newReplyText = "수정수정댓글";
        //when
        boolean flag = replyMapper.modify(Reply.builder().replyText(newReplyText).replyNo(replyNo).build());
        //then
        assertTrue(flag);
        String replyText = replyMapper.findOne(replyNo).getReplyText();
        System.out.println("\n\n\n");
        assertEquals(newReplyText, replyText);
        System.out.println("\n\n\n");
    }
}