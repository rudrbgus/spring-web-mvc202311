package com.spring.mvc.chap04.service;

/*
    컨트롤러와 레파지토리에 사이에 위치하여
    중간 로직을 처리하는 역할

    컨트롤러 -> 서비스 -> 레파지토리 (3 Tier 의존도)
 */

import com.spring.mvc.chap04.dto.ScoreAdjustRequestDTO;
import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.dto.ScoreResponseDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreMapper;
import com.spring.mvc.chap04.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@RequiredArgsConstructor
@Service
@RequiredArgsConstructor
public class ScoreService {
    private final ScoreMapper repository;
//    private final ScoreRepository repository;
//
//    public ScoreService(@Qualifier("dbRepo") ScoreRepository repository) {
//        this.repository = repository;
//    }


    // 목록 조회 중간처리
    /*
        컨트롤러는 데이터베이스에서 성적정보 리스트를
        조회해 오기를 원하고 있다.
        그런데 데이터베이스는 민감한정보까지 모두 조회한다.
        그리고 컬럼명도 그대로 노출하기 때문에
        이 모든것을 숨기는 처리를 하고 싶다.
     */
    public List<ScoreResponseDTO> getList(String sort){
        return repository.findAll(sort)
                .stream()
                .map(ScoreResponseDTO::new)
                .collect(Collectors.toList());
    }

    // 성적 입력 중간처리
    public boolean insertScore(ScoreRequestDTO dto){
        return repository.save(new Score(dto));
    }

    // 삭제 중간 처리
    public boolean deleteScore(int stuNum){
        return repository.delete(stuNum);
    }

    // 삽입 중간처리
    public void add(ScoreAdjustRequestDTO dto){
        repository.findOne(dto.getStuNum()).adjust(dto);
    }

}
