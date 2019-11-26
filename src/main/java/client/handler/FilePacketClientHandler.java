package client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.FilePacket;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author J95ha
 * @title: FilePacketClientHandler
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/26 14:44
 */
public class FilePacketClientHandler extends SimpleChannelInboundHandler<FilePacket> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FilePacket packet) throws Exception {
		File file = packet.getFile();
		System.out.println("receive file from server: " + file.getName());
		FileReceiveClientHandler.fileLength = file.length();
		FileReceiveClientHandler.outputStream = new FileOutputStream(
				new File("./client-receive-" + file.getName())
		);
		packet.setACK(packet.getACK() + 1);
		ctx.writeAndFlush(packet);
	}
}
