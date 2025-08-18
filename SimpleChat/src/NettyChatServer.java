import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import java.util.concurrent.ConcurrentHashMap;

public class NettyChatServer {
    private final int port;
    // 클라이언트 관리 (채널 → 닉네임)
    private static final ConcurrentHashMap<Channel, String> clients = new ConcurrentHashMap<>();

    public NettyChatServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        // EventLoopGroup: 스레드 풀 역할
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);     // 연결 수락 담당
        EventLoopGroup workerGroup = new NioEventLoopGroup();    // I/O 처리 담당

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline pipeline = ch.pipeline();

                            // 문자열 인코딩/디코딩 자동화
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());

                            // 메시지 처리 핸들러
                            pipeline.addLast(new SimpleChannelInboundHandler<String>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                    Channel channel = ctx.channel();
                                    String trimmedMsg = msg.trim();

                                    // 닉네임 등록 처리
                                    if (!clients.containsKey(channel)) {
                                        clients.put(channel, trimmedMsg);
                                        ctx.writeAndFlush("어서오세요, '" + trimmedMsg + "'님!\n");
                                        broadcast("'" + trimmedMsg + "'님이 입장했습니다.\n", channel);
                                        System.out.println("[입장] " + trimmedMsg);
                                    } else {
                                        // 채팅 메시지 브로드캐스트
                                        String username = clients.get(channel);
                                        String chatMsg = "[" + username + "]: " + trimmedMsg + "\n";
                                        System.out.println(chatMsg.trim());
                                        broadcast(chatMsg, channel);
                                    }
                                }

                                @Override
                                public void channelActive(ChannelHandlerContext ctx) {
                                    ctx.writeAndFlush("닉네임을 입력하세요:\n");
                                }

                                @Override
                                public void channelInactive(ChannelHandlerContext ctx) {
                                    Channel channel = ctx.channel();
                                    String username = clients.remove(channel);
                                    if (username != null) {
                                        System.out.println("[퇴장] " + username);
                                        broadcast("'" + username + "'님이 퇴장했습니다.\n", channel);
                                    }
                                }

                                @Override
                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                                    cause.printStackTrace();
                                    ctx.close();
                                }
                            });
                        }
                    });

            ChannelFuture f = b.bind(port).sync();
            System.out.println("Netty 채팅 서버가 포트 " + port + " 에서 실행중입니다.");
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    // 모든 클라이언트에 메시지 브로드캐스트 (발신자 제외)
    private static void broadcast(String message, Channel sender) {
        for (Channel channel : clients.keySet()) {
            if (channel != sender && channel.isActive()) {
                channel.writeAndFlush(message);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 12345;
        new NettyChatServer(port).run();
    }
}
