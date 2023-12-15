package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;

import java.util.*;
import java.util.stream.Collectors;

public class BoardRepositoryImpl {
    private static Map<Integer, Board> boardMap;

    static {
        boardMap = new HashMap<>();
    }

    public boolean save(int number,Board board){
        boardMap.put(number, board);
        return true;
    }

    public boolean delete(int boardNo){
        boardMap.remove(boardNo);
        return true;
    }

//    public List<board> findAll(){
//        List<board> collect = boardMap.values().stream()
//                .sorted(Comparator.comparing((board n) -> n.getBoardNo()).reversed())
//                .collect(Collectors.toList());
//        return collect;
//    }

//    public newBoard findOne(int num){
//        int viewCount = boardMap.get(num).getViewCount();
//        viewCount++;
//        boardMap.get(num).setViewCount(viewCount);
//        return boardMap.get(num);
//    }

}
