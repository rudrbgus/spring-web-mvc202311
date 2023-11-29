package com.spring.mvc.chap04.dto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ScoreAdjustRequestDTO {
    private int stuNum;
    private int kor, eng, math;
}
