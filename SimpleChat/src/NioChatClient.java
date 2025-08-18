import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NioChatClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345;

        try (SocketChannel client = SocketChannel.open()) {
            // 서버 연결
            client.connect(new InetSocketAddress(host, port));
            client.configureBlocking(false); // 논블로킹 모드 설정

            System.out.println("서버에 연결됨: " + host + ":" + port);

            Scanner scanner = new Scanner(System.in);
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            // 서버 메시지 수신 전용 스레드
            Thread readerThread = new Thread(() -> {
                try {
                    while (true) {
                        buffer.clear();
                        int readBytes = client.read(buffer);
                        if (readBytes > 0) {
                            buffer.flip();
                            byte[] bytes = new byte[buffer.limit()];
                            buffer.get(bytes);
                            System.out.println(new String(bytes).trim());
                        }
                        Thread.sleep(200); // CPU 사용량 조절
                    }
                } catch (IOException | InterruptedException e) {
                    System.out.println("서버와의 연결이 끊겼습니다.");
                }
            });
            readerThread.setDaemon(true); // 메인 스레드 종료시 함께 종료
            readerThread.start();

            // 사용자 입력 처리 (메인 스레드)
            while (true) {
                String message = scanner.nextLine();
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("채팅 종료");
                    break;
                }

                // 메시지를 서버로 전송
                buffer.clear();
                buffer.put(message.getBytes());
                buffer.flip();
                while (buffer.hasRemaining()) {
                    client.write(buffer);
                }
            }

        } catch (IOException e) {
            System.err.println("연결 오류: " + e.getMessage());
        }
    }
}
