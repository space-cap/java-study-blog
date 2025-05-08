package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class OracleConnectTest {
    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            // db.properties 파일 경로 (src/db.properties 또는 resources/db.properties)
            FileInputStream fis = new FileInputStream("resources/db.properties");
            props.load(fis);
        } catch (IOException e) {
            System.out.println("❌ properties 파일 로드 실패!");
            e.printStackTrace();
            return;
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ 오라클 DB 연결 성공!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ 오라클 DB 연결 실패!");
            e.printStackTrace();
        }
    }
}
