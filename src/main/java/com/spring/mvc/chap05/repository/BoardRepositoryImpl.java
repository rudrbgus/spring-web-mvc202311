package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.newBoard;

import java.util.*;
import java.util.stream.Collectors;

public class BoardRepositoryImpl {
    private static Map<Integer, newBoard> boardMap;

    static {
        boardMap = new HashMap<>();
    }

    public boolean save(int number,newBoard newBoard){
        boardMap.put(number, newBoard);
        return true;
    }

    public boolean delete(int boardNo){
        boardMap.remove(boardNo);
        return true;
    }

    public List<newBoard> findAll(){
        List<newBoard> collect = boardMap.values().stream()
                .sorted(Comparator.comparing((newBoard n) -> n.getBoardNo()).reversed())
                .collect(Collectors.toList());
        return collect;
    }

    public newBoard findOne(int num){
        int viewCount = boardMap.get(num).getViewCount();
        viewCount++;
        boardMap.get(num).setViewCount(viewCount);
        return boardMap.get(num);
    }

}
