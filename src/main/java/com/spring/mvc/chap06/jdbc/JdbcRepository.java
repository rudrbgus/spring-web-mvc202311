package com.spring.mvc.chap06.jdbc;

import com.spring.mvc.chap06.entity.Person;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcRepository {

    // db 연결 설정 정보
    private String url = "jdbc:mariadb://localhost:3307/spring"; // 데이터베이스 url
    private String userName = "root";
    private String password = "mariadb";

    // JDBC 드라이버 로딩
    public JdbcRepository(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    // 데이터베이스 커넥션 얻기
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }

    //Insert 기능
    public void save(Person person){
        Connection connection = null;
        // 1. DB연결하고 연결 정보를 얻어와야 함
        try {
            connection = DriverManager.getConnection(url, userName, password);

            // 2. 트랜잭션을 시작
            connection.setAutoCommit(false); // 자동 커밋 비활성화

            // 3. SQL을 생성
            String sql = "insert into person "+
                    "(id, person_name, person_age)"+
                    "Values (?, ?, ?)";

            // 4. sql을 실행시켜주는 객체 얻어와야 함
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // 5. ? 파라미터 세팅
            preparedStatement.setString(1, person.getId());
            preparedStatement.setString(2, person.getPersonName());
            preparedStatement.setInt(3, person.getPersonAge());

            // 6. sql 실행 명령
            // executeUpdate - 갱신, executeQuery - 조회
            int result = preparedStatement.executeUpdate();// 리턴값은 성공한 쿼리의 개수

            // 7. 트랜잭션 처리
            if(result == 1) connection.commit();
            else connection.rollback();


        } catch (SQLException e) {
            try {
                if(connection != null)connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            try {
                if(connection!=null)connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // UPDATE
    public void update(Person person){
        Connection connection = null;
        // 1. DB연결하고 연결 정보를 얻어와야 함
        try {
            connection = DriverManager.getConnection(url, userName, password);

            // 2. 트랜잭션을 시작
            connection.setAutoCommit(false); // 자동 커밋 비활성화

            // 3. SQL을 생성
            String sql = "UPDATE person "+
                    "SET person_name = ?, person_age = ? " +
                    "WHERE id = ?";

            // 4. sql을 실행시켜주는 객체 얻어와야 함
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // 5. ? 파라미터 세팅
            preparedStatement.setString(1, person.getPersonName());
            preparedStatement.setInt(2, person.getPersonAge());
            preparedStatement.setString(3, person.getId());

            // 6. sql 실행 명령
            // executeUpdate - 갱신, executeQuery - 조회
            int result = preparedStatement.executeUpdate();// 리턴값은 성공한 쿼리의 개수

            // 7. 트랜잭션 처리
            if(result == 1) connection.commit();
            else connection.rollback();


        } catch (SQLException e) {
            try {
                if(connection != null)connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            try {
                if(connection!=null)connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Delete
    public void delete(String id){
        Connection connection = null;
        // 1. DB연결하고 연결 정보를 얻어와야 함
        try {
            connection = DriverManager.getConnection(url, userName, password);

            // 2. 트랜잭션을 시작
            connection.setAutoCommit(false); // 자동 커밋 비활성화

            // 3. SQL을 생성
            String sql = "delete from person " +
                    "Where id = ?";

            // 4. sql을 실행시켜주는 객체 얻어와야 함
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // 5. ? 파라미터 세팅
            preparedStatement.setString(1, id);

            // 6. sql 실행 명령
            // executeUpdate - 갱신, executeQuery - 조회
            int result = preparedStatement.executeUpdate();// 리턴값은 성공한 쿼리의 개수

            // 7. 트랜잭션 처리
            if(result == 1) connection.commit();
            else connection.rollback();


        } catch (SQLException e) {
            try {
                if(connection != null)connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            try {
                if(connection!=null)connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 전체 회원 조회
    public List<Person> findAll(){

        List<Person> people = new ArrayList<>();

        try{
            // 1. 데이터베이스 연결해서 연결정보 획득
            Connection conn = DriverManager.getConnection(url, userName, password);

            // 2. sql을 생성
            String sql = "SELECT * From person";

            // 3. SQL실행을 위한 객체 획득
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. ? 파라미터 채우기

            // 5. SQL 실행 명령
            ResultSet rs = pstmt.executeQuery();

            // 6. 결과 집합 조작하기
            while(rs.next()) {
                // 커서에 위치한 데이터 레코드 읽기
                String personName = rs.getString("person_name");
                int personAge = rs.getInt("person_age");
                String id = rs.getString("id");
                Person person = new Person(id, personName, personAge);
                people.add(person);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return people;
    }

    // 단일 조회 (아이디로 조회)
    public Person findOne(String id){
        try{
            // 1. 데이터베이스 연결해서 연결정보 획득
            Connection conn = DriverManager.getConnection(url, userName, password);

            // 2. sql을 생성
            String sql = "SELECT * From person WHERE id = ?";

            // 3. SQL실행을 위한 객체 획득
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. ? 파라미터 채우기
            pstmt.setString(1, id);
            // 5. SQL 실행 명령
            ResultSet rs = pstmt.executeQuery();

            // 6. 결과 집합 조작하기
            while(rs.next()) {
                // 커서에 위치한 데이터 레코드 읽기
                String personName = rs.getString("person_name");
                int personAge = rs.getInt("person_age");
                String pid = rs.getString("id");

                return new Person(pid, personName, personAge);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
