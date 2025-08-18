import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import java.util.Scanner;

public class NettyChatClient {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 12345;

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ChannelPipeline pipeline = ch.pipeline();

                            // 문자열 인코딩/디코딩 자동화
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());

                            // 서버 메시지 수신 핸들러
                            pipeline.addLast(new SimpleChannelInboundHandler<String>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                    System.out.println(msg.trim());
                                }

                                @Override
                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                                    System.out.println("서버와의 연결이 끊겼습니다.");
                                    ctx.close();
                                }
                            });
                        }
                    });

            // 서버 연결
            ChannelFuture f = b.connect(host, port).sync();
            System.out.println("서버에 연결됨: " + host + ":" + port);

            Scanner scanner = new Scanner(System.in);
            Channel channel = f.channel();

            // 사용자 입력 처리
            while (true) {
                String line = scanner.nextLine();
                if ("exit".equalsIgnoreCase(line)) {
                    System.out.println("채팅 종료");
                    channel.close().sync();
                    break;
                }
                channel.writeAndFlush(line + "\n");
            }
        } finally {
            group.shutdownGracefully();
        }
    }
}
