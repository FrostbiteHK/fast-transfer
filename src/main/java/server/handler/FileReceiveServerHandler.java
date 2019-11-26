package server.handler;

import codec.Codec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author J95ha
 * @title: FileReceiveServerHandler
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/26 15:25
 */
public class FileReceiveServerHandler extends ChannelInboundHandlerAdapter {

	static FileOutputStream fileOutputStream;

	static long fileLength;

	private static long readLength;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf byteBuf = (ByteBuf) msg;
		int type = byteBuf.getInt(0);
		if (type != Codec.TYPE) {
			readLength += byteBuf.readableBytes();
			writeToFile(byteBuf);
			sendComplete(readLength);
		} else {
			super.channelRead(ctx, msg);
		}
	}

	private void writeToFile(ByteBuf byteBuf) throws IOException {
		byte[] bytes = new byte[byteBuf.readableBytes()];
		byteBuf.readBytes(bytes);
		fileOutputStream.write(bytes);
		byteBuf.release();
	}

	private void sendComplete(long readLength) throws IOException {
		if (readLength >= fileLength) {
			System.out.println("文件接收完成---");
			fileOutputStream.close();
		}
	}
}
