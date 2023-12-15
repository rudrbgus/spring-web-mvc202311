package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.common.PageMaker;
import com.spring.mvc.chap05.common.Search;
import com.spring.mvc.chap05.dto.BoardDetailResponseDTO;
import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.BoardWriteRequsetDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    // 1. 목록 조회 요청 (/board/list : GET)
    // 2. 글쓰기 화면요청(/board/write : GET)
    // 3. 글쓰기 등록요청(/board/write : POST)
    // 4. 글 삭제 요청 (/board/delete : GET)
    // 5. 글 상세보기 요청 (/board/detail : GET)


    // 1. 목록 조회 요청
    @GetMapping("/list")
    public String showList(@ModelAttribute("s") Search page, Model model){
        System.out.println(page);
        List<BoardListResponseDTO> all = boardService.findAll(page);

        // 페이징 계산 알고리즘 적용
        PageMaker maker = new PageMaker(page, boardService.getCount(page));

        model.addAttribute("bList", all);
        model.addAttribute("maker", maker);
//        model.addAttribute("s", page);

        return "chap05/list";
    }

    // 2. 글쓰기 화면요청(/board/write : GET)
    @GetMapping("write")
    public String write(){
        System.out.println("/board/write GET");
        return "chap05/write";
    }

    // 3. 글쓰기 등록요청(/board/write : POST)
    @PostMapping("write")
    public String write2(BoardWriteRequsetDTO dto){
        System.out.println("/board/write POST");
        boardService.write(dto);
        return "redirect:/board/list";
    }

    // 4. 글 삭제 요청 (/board/delete : GET)
    @GetMapping("delete")
    public String delete(@RequestParam int bno){
        boardService.remove(bno);
        return "redirect:/board/list";
    }

    // 5. 글 상세보기 요청 (/board/detail : GET)
    @GetMapping("detail")
    public String detail(@RequestParam int bno, Model model){
        System.out.println(bno);
        BoardDetailResponseDTO one = boardService.getOne(bno);
        model.addAttribute("b", one);
        return "chap05/detail";
    }
}