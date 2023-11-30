package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.BoardRequsetDTO;
import com.spring.mvc.chap05.entity.newBoard;
import com.spring.mvc.chap05.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
public class BoardController {
    // 1. 목록 조회 요청 (/board/list : GET)
    // 2. 글쓰기 화면요청(/board/write : GET)
    // 3. 글쓰기 등록요청(/board/write : POST)
    // 4. 글 삭제 요청 (/board/delete : GET)
    // 5. 글 상세보기 요청 (/board/detail : GET)


    // 1. 목록 조회 요청
    @GetMapping("/list")
    public String showList(Model model){
        model.addAttribute("bList", BoardService.bList);
        return "chap05/list";
    }

    // 2. 글쓰기 화면요청(/board/write : GET)
    @GetMapping("write")
    public String write(){
        System.out.println("/board/write GET");
        return "chap05/write";
    }

    // 3. 글쓰기 등록요청(/board/write : POST)
    @PostMapping("write1")
    public String write2(BoardRequsetDTO dto){
        System.out.println("/board/write POST");
        BoardService.write(dto);
        return "redirect:/board/list";
    }

    // 4. 글 삭제 요청 (/board/delete : GET)
    @GetMapping("delete")
    public String delete(@RequestParam int bno){
        BoardService.delete(bno);
        return "redirect:/board/list";
    }

    // 5. 글 상세보기 요청 (/board/detail : GET)
    @GetMapping("detail")
    public String detail(@RequestParam int bno, Model model){
        newBoard newBoard = BoardService.bList.get(bno);
        model.addAttribute("b", newBoard);
        return "chap05/detail";
    }
}
