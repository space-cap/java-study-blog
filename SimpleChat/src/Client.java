import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * 채팅 서버에 연결하는 클라이언트 클래스
 * 서버로부터 메시지를 수신하는 스레드와 사용자 입력을 서버로 전송하는 메인 스레드로 구성됩니다.
 */
public class Client {
    // 연결할 서버의 주소 (로컬호스트)
    private static final String SERVER_ADDRESS = "localhost";
    
    // 연결할 서버의 포트 번호
    private static final int SERVER_PORT = 12345;

    /**
     * 클라이언트 메인 메서드
     * 서버에 연결하고 메시지 송수신을 처리합니다.
     */
    public static void main(String[] args) {
        // try-with-resources 문법을 사용하여 자동으로 자원 해제
        try (
                // 서버에 소켓 연결 생성
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                
                // 서버로 메시지를 보내기 위한 출력 스트림
                // autoFlush=true로 설정하여 자동으로 버퍼를 플래시
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                
                // 서버로부터 메시지를 받기 위한 입력 스트림
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            // 사용자 키보드 입력을 받기 위한 Scanner 객체
            Scanner scanner = new Scanner(System.in);

            // 서버로부터 오는 메시지를 지속적으로 받는 별도의 스레드 생성
            // 람다 표현식을 사용한 Runnable 구현
            new Thread(() -> {
                try {
                    String serverResponse;
                    // 서버로부터 메시지를 계속 읽어옴
                    // readLine()은 서버가 연결을 끊으면 null을 반환
                    while ((serverResponse = in.readLine()) != null) {
                        // 받은 메시지를 콘솔에 출력
                        System.out.println(serverResponse);
                    }
                } catch (IOException e) {
                    // 서버와의 연결이 끊어졌을 때 처리
                    System.out.println("서버 연결이 종료되었습니다.");
                }
            }).start(); // 스레드 시작

            // 메인 스레드에서 사용자 입력을 처리
            // 사용자가 입력한 내용을 서버로 전송
            while (scanner.hasNextLine()) {
                // 사용자 입력을 한 줄씩 읽어옴
                String userInput = scanner.nextLine();
                
                // 입력받은 내용을 서버로 전송
                out.println(userInput);
            }
        } catch (IOException e) {
            // 서버 연결 실패 시 오류 메시지 출력
            System.out.println("서버에 연결할 수 없습니다: " + e.getMessage());
        }
    }
}
