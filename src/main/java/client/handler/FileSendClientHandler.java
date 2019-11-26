package client.handler;

import codec.Codec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.DefaultFileRegion;
import protocol.FilePacket;
import protocol.Packet;

import java.io.File;

/**
 * @author J95ha
 * @title: FileSendClientHandler
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/26 14:20
 */
@ChannelHandler.Sharable
public class FileSendClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf byteBuf = (ByteBuf) msg;
		int type = byteBuf.getInt(0);
		if (type == Codec.TYPE) {
			Packet packet = Codec.INSTANCE.decode(byteBuf);
			if (packet instanceof FilePacket) {
				FilePacket filePacket = (FilePacket) packet;
				if (filePacket.getACK() != 0) {
					writeAndFlushFileRegion(ctx, filePacket);
				} else {
					super.channelRead(ctx, packet);
				}
			} else {
				super.channelRead(ctx, packet);
			}
		} else {
			System.out.println("无法识别此类数据包");
		}
	}

	private void writeAndFlushFileRegion(ChannelHandlerContext ctx, FilePacket packet) {
		File file = packet.getFile();
		DefaultFileRegion fileRegion = new DefaultFileRegion(file, 0, file.length());
		ctx.writeAndFlush(fileRegion).addListener(future -> {
			if (future.isSuccess()) {
				System.out.println("发送完成...");
			}
		});
	}


}


