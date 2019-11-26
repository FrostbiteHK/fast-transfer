package client;

import client.console.SendFileConsole;
import client.handler.FilePacketClientHandler;
import client.handler.FileReceiveClientHandler;
import client.handler.FileSendClientHandler;
import client.handler.LoginResponseHandler;
import codec.DecodeHandler;
import codec.EncodeHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.stream.ChunkedWriteHandler;
import protocol.request.LoginRequestPacket;

/**
 * @author J95ha
 * @title: Client
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/20 20:16
 */
public class Client {

    private static final String HOST = System.getProperty("host", "127.0.0.1"); // getProperty()这个方法是获取指定键指示的系统属性

    private static final int PORT = Integer.parseInt(System.getProperty("port", "8888"));

    public static void main(String[] args) throws InterruptedException {

        Bootstrap bootstrap = new Bootstrap();

        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast(new FileReceiveClientHandler());
                        pipeline.addLast(new FileSendClientHandler());
                        pipeline.addLast(new DecodeHandler());
                        pipeline.addLast(new EncodeHandler());
                        pipeline.addLast(new ChunkedWriteHandler());
                        pipeline.addLast(new LoginResponseHandler());
                        pipeline.addLast(new FilePacketClientHandler());
                        // pipeline.addLast(new MyClientHandler());
                    }
                });

        ChannelFuture future = bootstrap.connect(HOST, PORT).sync();
        if (future.isSuccess()) {
            System.out.println("连接服务器成功");
            Channel channel = future.channel();
            joinCluster(channel);
            console(channel);
        } else {
            System.out.println("连接服务器失败");
        }

        future.channel().closeFuture().sync();
    }

    private static void joinCluster(Channel channel) throws InterruptedException {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket("node1");
        channel.writeAndFlush(loginRequestPacket);
        Thread.sleep(2000);
    }

    private static void console(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                SendFileConsole.exec(channel);
            }
        }).start();
    }

}

