package com.spring.mvc.chap05.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class BoardWriteRequsetDTO {
    private String title;
    private String content;
}
