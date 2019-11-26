package codec;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import protocol.Packet;

import java.util.List;

/**
 * @author J95ha
 * @title: CodecHandler
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/26 13:19
 */

@ChannelHandler.Sharable
public class CodecHandler extends MessageToMessageCodec<ByteBuf, Object> {
	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {

		if (msg instanceof Packet) {
			ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
			Codec.INSTANCE.encode(byteBuf, (Packet)msg);
			out.add(byteBuf);
		} else {
			System.out.println("File ByteBuf needn't encode");
		}
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {

		if (msg.getInt(0) == Codec.TYPE) {
			System.out.println("decode FilePacket");
			out.add(Codec.INSTANCE.decode(msg));
		} else {
			System.out.println("File ByteBuf needn't decode");
			ctx.fireChannelRead(msg); // 寻找下一个注册（invoke）的handler去处理事件
		}

	}
}
