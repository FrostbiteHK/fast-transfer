package client.handler;

import codec.Codec;
import com.sun.org.apache.bcel.internal.classfile.Code;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author J95ha
 * @title: FileReceiveClientHandler
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/26 14:32
 */
public class FileReceiveClientHandler extends ChannelInboundHandlerAdapter {

	static FileOutputStream outputStream;

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
		outputStream.write(bytes);
		byteBuf.release();
	}

	private void sendComplete(long readLength) throws IOException {
		if (readLength >= fileLength) {
			System.out.println("文件接收完成...");
			outputStream.close();
		}
	}
}

