package com.spring.mvc.chap05.dto;

import com.spring.mvc.chap05.entity.Board;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
@EqualsAndHashCode
public class BoardDetailResponseDTO {
    private final int boardNo;
    private final String Title; // 5글자 이상이면 잘라내기
    private final String Content; // 30자 이상이면 잘라내기
    private final String date; // 날짜패턴 yyyy-MM-dd HH:mm

    public BoardDetailResponseDTO(Board board){
        this.boardNo = board.getBoardNo();
        this.Content = board.getContent();
        this.Title = board.getTitle();
        this.date = makePrettierDateString(board.getRegDateTime());
    }

    static String makePrettierDateString(LocalDateTime regDateTime) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return pattern.format(regDateTime);
    }
}
