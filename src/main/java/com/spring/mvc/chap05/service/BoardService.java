package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.BoardRequsetDTO;
import com.spring.mvc.chap05.entity.newBoard;

import java.util.ArrayList;
import java.util.List;

public class BoardService {
    public static int boardNo;
    public static List<newBoard> bList;

    static {
        boardNo =0;
        bList = new ArrayList<>();
    }

    public static void write(BoardRequsetDTO dto) {
        String shortTitle;
        String shortContent;

        if(dto.getTitle().length() >7){
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
        newBoard board = new newBoard(boardNo, dto.getTitle(), dto.getContent(), shortTitle, shortContent);

        boardNo+=1;
        bList.add(board);
    }

    public static void delete(int bno) {
        bList.remove(bno);
    }
}
