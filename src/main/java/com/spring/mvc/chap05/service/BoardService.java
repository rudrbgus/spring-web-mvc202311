package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.BoardRequsetDTO;
import com.spring.mvc.chap05.entity.newBoard;
import com.spring.mvc.chap05.repository.BoardRepositoryImpl;

import java.util.List;

public class BoardService {
    public static int boardNo;
    private static BoardRepositoryImpl boardRepositoryImpl;

    static {
        boardNo=0;
        boardRepositoryImpl = new BoardRepositoryImpl();
    }

    public static void write(BoardRequsetDTO dto) {
        String shortTitle;
        String shortContent;

        if(dto.getTitle().length()>7){
            String substring = dto.getTitle().substring(0, 7);
            shortTitle = substring+"...";
        }else{
            shortTitle = dto.getTitle();
        }
        if(dto.getContent().length() > 20){
            String substring = dto.getContent().substring(0, 20);
            shortContent = substring+"...";
        }else{
            shortContent = dto.getContent();
        }
        boardNo +=1;
        newBoard board = new newBoard(boardNo, dto.getTitle(), dto.getContent(), shortTitle, shortContent);
        boolean save = boardRepositoryImpl.save(boardNo, board);
    }

    public static void remove(int bno){
        boardRepositoryImpl.delete(bno);
    }

    public static List<newBoard> findAll() {
        return boardRepositoryImpl.findAll();
    }

    public static newBoard getOne(int bno) {
        return boardRepositoryImpl.findOne(bno);
    }
}
