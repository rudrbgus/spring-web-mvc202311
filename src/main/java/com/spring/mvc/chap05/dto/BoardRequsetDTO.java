package com.spring.mvc.chap05.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class BoardRequsetDTO {
    private String title;
    private String content;
}
