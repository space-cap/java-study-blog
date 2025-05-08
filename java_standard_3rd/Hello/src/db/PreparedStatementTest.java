package db;

import java.sql.*;

public class PreparedStatementTest {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
        String user = "student";
        String password = "apple1234";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("✅ DB 연결 성공");

            // 테이블 생성
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(50), age INT)");
                System.out.println("✅ 테이블 생성 완료");
            }

            // 데이터 삽입 (PreparedStatement 사용)
            String insertSQL = "INSERT INTO users (id, name, age) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setInt(1, 1);
                pstmt.setString(2, "Alice");
                pstmt.setInt(3, 25);
                pstmt.executeUpdate();

                pstmt.setInt(1, 2);
                pstmt.setString(2, "Bob");
                pstmt.setInt(3, 30);
                pstmt.executeUpdate();

                pstmt.setInt(1, 3);
                pstmt.setString(2, "Charlie");
                pstmt.setInt(3, 22);
                pstmt.executeUpdate();

                System.out.println("✅ 데이터 삽입 완료");
            }

            // 데이터 조회 (PreparedStatement 사용)
            String selectSQL = "SELECT id, name, age FROM users WHERE age >= ?";
            try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
                pstmt.setInt(1, 25);

                try (ResultSet rs = pstmt.executeQuery()) {
                    System.out.println("나이 25세 이상 사용자 목록:");
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        int age = rs.getInt("age");
                        System.out.printf("ID: %d, 이름: %s, 나이: %d%n", id, name, age);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
