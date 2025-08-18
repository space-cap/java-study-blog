import java.io.*;
import java.net.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 다중 클라이언트를 지원하는 채팅 서버 클래스
 * 각 클라이언트는 별도의 스레드에서 처리되며, 한 클라이언트의 메시지는 다른 모든 클라이언트에게 브로드캐스트됩니다.
 */
public class Server {
    // 서버가 사용할 포트 번호 (고정값)
    private static final int PORT = 12345;
    
    /**
     * 현재 연결된 모든 클라이언트 핸들러를 저장하는 리스트
     * CopyOnWriteArrayList 사용 이유:
     * - 다중 스레드 환경에서 안전한 동시 읽기/쓰기 보장
     * - 클라이언트 추가/제거 시 다른 스레드의 순회 작업에 영향을 주지 않음
     */
    private static CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();

    /**
     * 서버 메인 메서드
     * 서버 소켓을 생성하고 클라이언트의 연결 요청을 지속적으로 수락합니다.
     */
    public static void main(String[] args) {
        System.out.println("서버를 시작합니다...");
        
        // try-with-resources 문법을 사용하여 자동으로 서버 소켓을 닫음
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            // 무한 루프로 클라이언트의 연결 요청을 계속 기다림
            while (true) {
                // 클라이언트의 연결 요청을 대기하고 수락
                // accept()는 블로킹 메서드로 연결이 올 때까지 대기
                Socket socket = serverSocket.accept();
                System.out.println("새 클라이언트 연결됨: " + socket);
                
                // 새로 연결된 클라이언트를 위한 핸들러 객체 생성
                ClientHandler handler = new ClientHandler(socket);
                
                // 클라이언트 핸들러를 리스트에 추가 (브로드캐스트를 위해)
                clients.add(handler);
                
                // 각 클라이언트를 별도의 스레드에서 처리하여 동시 다중 접속 지원
                new Thread(handler).start();
            }
        } catch (IOException e) {
            // 서버 소켓 생성 또는 클라이언트 연결 수락 중 오류 발생 시 처리
            System.err.println("서버 실행 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 특정 발신자를 제외한 모든 연결된 클라이언트에게 메시지를 브로드캐스트하는 메서드
     * @param message 전송할 메시지
     * @param sender 메시지를 보낸 클라이언트 핸들러 (자신에게는 메시지를 보내지 않음)
     */
    public static void broadcast(String message, ClientHandler sender) {
        // 현재 연결된 모든 클라이언트를 순회
        for (ClientHandler client : clients) {
            // 발신자가 아닌 클라이언트에게만 메시지 전송
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    /**
     * 각 클라이언트의 연결을 독립적으로 처리하는 내부 클래스
     * Runnable 인터페이스를 구현하여 별도의 스레드에서 실행 가능
     */
    static class ClientHandler implements Runnable {
        // 클라이언트와의 소켓 연결
        private Socket socket;
        
        // 클라이언트에게 메시지를 보내기 위한 출력 스트림
        private PrintWriter out;
        
        // 클라이언트로부터 메시지를 받기 위한 입력 스트림
        private BufferedReader in;
        
        // 클라이언트의 사용자명 (닉네임)
        private String username;

        /**
         * ClientHandler 생성자
         * 클라이언트 소켓을 받아 입출력 스트림을 초기화합니다.
         * @param socket 클라이언트와의 연결된 소켓
         */
        public ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                // 클라이언트로 데이터를 보내기 위한 PrintWriter 생성
                // autoFlush=true로 설정하여 자동으로 버퍼를 플래시
                out = new PrintWriter(socket.getOutputStream(), true);
                
                // 클라이언트로부터 데이터를 받기 위한 BufferedReader 생성
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                System.err.println("클라이언트 핸들러 초기화 중 오류: " + e.getMessage());
                e.printStackTrace();
            }
        }

        /**
         * 스레드가 실행될 때 호출되는 메서드
         * 클라이언트의 닉네임을 받고, 지속적으로 메시지를 수신하여 다른 클라이언트들에게 브로드캐스트합니다.
         */
        public void run() {
            try {
                // 클라이언트에게 닉네임 입력 요청
                out.println("닉네임을 입력하세요:");
                
                // 클라이언트로부터 닉네임을 읽어옴
                username = in.readLine();
                System.out.println(username + " 접속됨!");
                
                // 클라이언트에게 환영 메시지 전송
                out.println("채팅방에 오신걸 환영합니다, " + username + "!");
                out.println("메시지를 입력하세요:");

                String message;
                // 클라이언트로부터 지속적으로 메시지를 받아서 처리
                // readLine()은 클라이언트가 연결을 끊으면 null을 반환
                while ((message = in.readLine()) != null) {
                    // 서버 콘솔에 수신된 메시지 출력
                    System.out.println("[" + username + "]: " + message);
                    
                    // 발신자를 제외한 모든 클라이언트에게 메시지 브로드캐스트
                    broadcast("[" + username + "]: " + message, this);
                }
            } catch (IOException e) {
                // 클라이언트 연결이 끊어졌을 때의 처리
                System.out.println(username + " 연결 종료.");
            } finally {
                // 클라이언트 연결 종료 시 정리 작업
                try {
                    // 클라이언트 리스트에서 이 핸들러 제거
                    clients.remove(this);
                    
                    // 모든 스트림과 소켓 자원 해제
                    if (in != null) in.close();
                    if (out != null) out.close();
                    if (socket != null) socket.close();
                } catch (IOException ex) {
                    System.err.println("클라이언트 연결 정리 중 오류: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }

        /**
         * 이 클라이언트에게 메시지를 전송하는 메서드
         * @param message 전송할 메시지
         */
        public void sendMessage(String message) {
            // PrintWriter를 통해 클라이언트에게 메시지 전송
            // autoFlush가 true로 설정되어 있어 자동으로 전송됨
            out.println(message);
        }
    }
}
