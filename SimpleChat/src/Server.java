import java.io.*;
import java.net.*;
import java.util.concurrent.*;

// 서버 클래스
public class Server {
    private static final int PORT = 12345;         // 포트 설정
    private static final int MAX_THREADS = 100;    // 최대 동시 접속 인원(풀 스레드 개수)

    // 접속 클라이언트들을 관리하는 리스트(스레드 안전)
    private static CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        System.out.println("서버를 시작합니다...");

        // 스레드 풀 생성(최대 100개까지 동접 허용, 필요시 조정 가능)
        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                // 클라이언트 접속 대기 및 수락
                Socket socket = serverSocket.accept();
                System.out.println("새 클라이언트 연결됨: " + socket);
                // 각 클라이언트 담당 핸들러 및 리스트 등록
                ClientHandler handler = new ClientHandler(socket);
                clients.add(handler);
                // 스레드 풀로 작업 제출 (별도 스레드를 직접 생성하지 않음)
                executor.submit(handler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown(); // 서버 종료 시 스레드 풀 종료
        }
    }

    // 전체 클라이언트에게 메시지 전달(본인 제외)
    public static void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    // 클라이언트 한 명의 연결 및 메시지 처리를 담당하는 내부 클래스
    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String username;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                // 닉네임 입력 안내 및 수신
                out.println("닉네임을 입력하세요:");
                username = in.readLine();
                System.out.println(username + " 접속됨!");
                out.println("채팅방에 오신걸 환영합니다, " + username + "!");
                out.println("메시지를 입력하세요:");

                String message;
                // 클라이언트가 접속을 종료할 때까지 메시지 수신 및 방송 반복
                while ((message = in.readLine()) != null) {
                    System.out.println("[" + username + "]: " + message);
                    broadcast("[" + username + "]: " + message, this);
                }
            } catch (IOException e) {
                System.out.println((username != null ? username : "알 수 없는 사용자") + " 연결 종료.");
            } finally {
                // 자원 정리 및 리스트 제거
                try {
                    clients.remove(this);
                    if (in != null) in.close();
                    if (out != null) out.close();
                    if (socket != null && !socket.isClosed()) socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        // 클라이언트에 메시지 전송
        public void sendMessage(String message) {
            out.println(message);
        }
    }
}
