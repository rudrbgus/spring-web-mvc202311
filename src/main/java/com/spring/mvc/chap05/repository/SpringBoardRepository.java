package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SpringBoardRepository implements BoardRepository {
    private final JdbcTemplate template;
    @Override
    public boolean save(Board board) {
        String sql = "insert into tbl_board "+
                "(title, content, view_count, reg_date_time) "+
                "values(?,?,?,?)";
        template.update(sql, board.getTitle(), board.getContent(), board.getViewCount(), board.getRegDateTime());
        return true;
    }


    @Override
    public boolean delete(int boardNum) {
        String sql = "delete from tbl_board where board_no = ?";
        return template.update(sql, boardNum)==1;
    }

    @Override
    public Board findOne(int boardNum) {
        String sql = "select * from tbl_board where board_no = ?";
        increaseViewCount(boardNum);
        return template.queryForObject(sql, (rs, rn) -> new Board(rs), boardNum);
    }

    @Override
    public List<Board> findAll() {
        String sql = "select * from tbl_board order by board_no DESC ";
        return template.query(sql, (rs, rowNum) -> new Board(rs));
    }

    public void increaseViewCount(int boardNo){
        String sql = "update tbl_board "+
                "set view_count = ? "+
                "where board_no = ?";
        int viewCount = findViewCount(boardNo);
        template.update(sql, viewCount+1, boardNo);
    }
    public int findViewCount(int boardNo){
        String sql = "select * from tbl_board where board_no = ?";
        Board board = template.queryForObject(sql, (rs, rn) -> new Board(rs), boardNo);
        return board.getViewCount();
    }
}
