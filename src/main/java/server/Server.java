package server;


import codec.DecodeHandler;
import codec.EncodeHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.stream.ChunkedWriteHandler;
import server.console.Console;
import server.console.ConsoleManager;
import server.console.impl.SendFileConsole;
import server.handler.FilePacketServerHandler;
import server.handler.FileReceiveServerHandler;
import server.handler.FileSendServerHandler;
import server.handler.JoinClusterRequestHandler;

import java.util.Scanner;

/**
 * @author J95ha
 * @title: Server
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/20 20:18
 */
public class Server {

    private static final int PORT = Integer.parseInt(System.getProperty("port", "8888"));

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup(); // 服务器端管理线程
        EventLoopGroup workerGroup = new NioEventLoopGroup(); //服务器端工作线程

        ServerBootstrap serverBootstrap = new ServerBootstrap(); // ServerBootstrap 初始化netty容器，监听端口的socket请求

        serverBootstrap.group(bossGroup, workerGroup)  // 绑定管理线程和工作线程
                .channel(NioServerSocketChannel.class) // 指定传输方式为nio
                .option(ChannelOption.SO_BACKLOG, 1024) // BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
                .option(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new FileReceiveServerHandler());
                        pipeline.addLast(new FileSendServerHandler());
                        pipeline.addLast(new DecodeHandler());
                        pipeline.addLast(new EncodeHandler());
                        pipeline.addLast(new ChunkedWriteHandler());
                        pipeline.addLast(new JoinClusterRequestHandler());
                        pipeline.addLast(new FilePacketServerHandler());
                    }
                });

        ChannelFuture future = serverBootstrap.bind(PORT).sync();
        if (future.isSuccess()) {
            System.out.println("端口绑定成功");
            Channel channel = future.channel();
            console(channel);
        } else {
            System.out.println("端口绑定失败");
        }

        future.channel().closeFuture().sync();
    }

    private static void console(Channel channel) {
        Console consoleManager = new ConsoleManager();
        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {
            while (!Thread.interrupted()) {
                SendFileConsole sendFileConsole = new SendFileConsole();
                sendFileConsole.exec(channel, scanner);
                //consoleManager.exec(channel, scanner);

            }
        }).start();
    }
}
