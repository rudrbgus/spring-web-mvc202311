package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreRepositoryImplTest {
    ScoreRepository repository = new ScoreRepositoryImpl();

    // 단위 테스트 (Unit test)
    // junit 5
    // 테스트 시나리오 - A를 주면 B가 나온다
    // 단언(Assertion) 기법 - ~해야 한다, ~이다 (O)
    //                    - ~할 것이다 , ~일 것 같다 (X)
    // GWT패턴 - Given, When, Then 패턴
    @Test
    @DisplayName("저장소에서 findAll 메서드를 호출하면 "+
                "리스트가 반환되고, 해당 리스트에는 성적정보가 3개 들어있어야 한다.")
    void findAllTest(){
        // GWT 패턴
        // Given: 테스트를 위해 주어지는 데이터 - parameter

        // When: 테스트 해봐야할 상황
        List<Score> scoreList = repository.findAll();

        // Then: 테스트 결과 단언 (결과 확인)

        assertEquals(3, scoreList.size()); // 예상
        assertNotNull(scoreList);
        assertEquals("제다이", scoreList.get(0).getName());
    }
    @Test
    @DisplayName("저장소에서 findOne을 호출하여 학번이 2인 학생을 조회하면 그 학생의 국어점수가 33점이고 이름은 옥테인 이어야한다.")
    void findOneTest(){
        // Given
        int sutNum = 2;
        // When
        Score score = repository.findOne(sutNum);
        // Then
        assertEquals(33, score.getKor());
        assertEquals("옥테인", score.getName());
    }

    @Test
    @DisplayName("학번이 -99번인 학생을 조회하면 Null이 나와야 한다.")
    void FindOneFailTest(){
        //given
        int stuNum = -99;
        // when
        Score score = repository.findOne(stuNum);
        // then
        assertNull(score);

    }

    @Test
    @DisplayName("저장소에서 학번이 2인 학생을 삭제한 후에" +
            "리스트를 전체조회보면 성적의 개수가 2개일 것이고" +
            "다시 2번학생을 조회했을 때 null이 반환되어야 한다.")
    void deleteTest() {
        int stuNum = 2;
        repository.delete(stuNum);
        List<Score> scoreList = repository.findAll();

        assertEquals(2, scoreList.size());
        assertNull(repository.findOne(stuNum));
    }

    @Test
    @DisplayName("새로운 성적정보를 save를 통해 추가하면" +
            "목록의 개수가 4개여야 한다.")
    void saveTest() {
        // Given
        Score s4 = new Score("감스트", 0, 0, 0, 4, 0, 0.0, Grade.F); // 새로운 학생 정보
        // When
        repository.save(s4); // 저장소에 새로운 학생 정보를 추가
        List<Score> scoreList = repository.findAll(); // 저장소에 있는 Score리스트를 꺼내서 다시 담음
        // Then
        assertEquals(4, scoreList.size()); // 저장소안에 크기가 4인지 확인.

    }
}