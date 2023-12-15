package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.common.Search;
import com.spring.mvc.chap05.dto.BoardDetailResponseDTO;
import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.BoardWriteRequsetDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardMapper;
import com.spring.mvc.chap05.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardRepository;
//    private final BoardRepository boardRepository;
//
//    public BoardService(BoardRepository boardRepository) {
//        this.boardRepository = boardRepository;
//    }


    public void write(BoardWriteRequsetDTO dto) {
        Board board = new Board(dto);
        boardRepository.save(board);
    }

    public  void remove(int bno){
        boardRepository.delete(bno);
    }

    public List<BoardListResponseDTO> findAll(Search page) {
        return boardRepository.findAll(page).stream()
                .map(BoardListResponseDTO::new)
                .collect(Collectors.toList());
    }

    public  BoardDetailResponseDTO getOne(int bno)
    {
        boardRepository.increaseViewCount(bno);
        return new BoardDetailResponseDTO(boardRepository.findOne(bno));
    }

    public int getCount(Search search) {
        return boardRepository.count(search);
    }
}
