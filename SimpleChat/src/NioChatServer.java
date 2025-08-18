import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class NioChatServer {
    public static void main(String[] args) throws IOException {
        int port = 12345;
        // Selector와 서버 소켓 채널 생성
        Selector selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(port));
        serverChannel.configureBlocking(false);

        // 접속 대기 이벤트 등록
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("NIO 채팅 서버 시작(port: " + port + ")");

        // 클라이언트 관리용 Map (소켓 채널 → 닉네임)
        Map<SocketChannel, String> userMap = new ConcurrentHashMap<>();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            selector.select(); // 이벤트 감지 (블로킹)
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                iter.remove();

                if (key.isAcceptable()) {
                    // 새 클라이언트 접속
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                    sc.write(ByteBuffer.wrap("닉네임을 입력하세요:\n".getBytes()));
                }
                else if (key.isReadable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    buffer.clear();
                    int bytesRead = 0;
                    try {
                        bytesRead = sc.read(buffer);
                    } catch (IOException e) {
                        bytesRead = -1;
                    }
                    if (bytesRead <= 0) {
                        // 접속 종료 처리
                        String user = userMap.getOrDefault(sc, "알 수 없음");
                        System.out.println("[" + user + "] 연결 종료");
                        userMap.remove(sc);
                        key.cancel();
                        sc.close();
                        continue;
                    }
                    buffer.flip();
                    String msg = new String(buffer.array(), 0, buffer.limit()).trim();

                    // 닉네임 등록 또는 메시지 송신 분기
                    if (!userMap.containsKey(sc)) {
                        userMap.put(sc, msg);
                        sc.write(ByteBuffer.wrap(("어서오세요, '" + msg + "'님!\n").getBytes()));
                        broadcastMsg(selector, userMap, sc, "'" + msg + "'님이 입장했습니다.\n");
                    } else {
                        String user = userMap.get(sc);
                        System.out.println("[" + user + "]: " + msg);
                        broadcastMsg(selector, userMap, sc, "[" + user + "]: " + msg + "\n");
                    }
                }
            }
        }
    }

    // 전체 클라이언트에게 메시지 전송 (자기 자신 제외)
    private static void broadcastMsg(Selector selector, Map<SocketChannel, String> userMap, SocketChannel sender, String msg) throws IOException {
        ByteBuffer msgBuffer = ByteBuffer.wrap(msg.getBytes());
        for (SelectionKey k : selector.keys()) {
            Channel ch = k.channel();
            if (ch instanceof SocketChannel sc && sc != sender && userMap.containsKey(sc)) {
                msgBuffer.rewind();
                sc.write(msgBuffer);
            }
        }
    }
}
