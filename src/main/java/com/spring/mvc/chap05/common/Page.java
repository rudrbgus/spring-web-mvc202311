package com.spring.mvc.chap05.common;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
@AllArgsConstructor
public class Page {
    private int pageNo; // 클라이언트가 보낸 페이지 번호
    private int amount; // 클라이언트가 보낸 목록게시물 수

    public Page() {
        this.pageNo = 1;
        this.amount = 6;
    }
    public Page(int amount){
        this.amount = amount;
    }



    /*
        만약에 한페이지에 게시물을 10개씩 뿌린다고 가정하면

        1페이지 -> LIMIT 0, 10
        2페이지 -> LIMIT 10, 10
        3페이지 -> LIMIT 20, 10

        만약에 한페이지에 게시물을 6개씩 뿌린다고 가정하면

        1페이지 -> LIMIT 0, n
        2페이지 -> LIMIT 6, n
        3페이지 -> LIMIT 18, n
        M페이지 -> LIMIT (M-1) * M , M
     */

    public int getPageStart(){
        return (pageNo - 1) * amount;
    }
}
