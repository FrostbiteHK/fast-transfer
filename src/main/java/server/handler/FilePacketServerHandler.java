package server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.FilePacket;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author J95ha
 * @title: FilePacketServerHandler
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/26 15:28
 */
public class FilePacketServerHandler extends SimpleChannelInboundHandler<FilePacket> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FilePacket packet) throws Exception {
		File file = packet.getFile();
		System.out.println("receive file from client :" + file.getName());
		FileReceiveServerHandler.fileLength = file.length();
		FileReceiveServerHandler.fileOutputStream = new FileOutputStream(
				new File("./server-receive-" + file.getName())
		);
		packet.setACK(packet.getACK() + 1);
		ctx.writeAndFlush(packet);
	}
}
