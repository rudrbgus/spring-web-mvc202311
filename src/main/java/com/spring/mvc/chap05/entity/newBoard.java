package com.spring.mvc.chap05.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class newBoard extends Board {
    private String shortTitle;
    private String shortContent;
    private String date;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public newBoard(int boardNo, String title, String content, String shortTitle, String shortContent) {
        super(boardNo, title, content);
        this.date = super.getRegDateTime().format(dateTimeFormatter);
        this.shortTitle = shortTitle;
        this.shortContent = shortContent;
    }



}
