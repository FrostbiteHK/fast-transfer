package codec;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import protocol.Packet;

/**
 * @author J95ha
 * @title: EncodeHandler
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/26 13:20
 */
public class EncodeHandler extends MessageToByteEncoder {
	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
		Codec.INSTANCE.encode(out, (Packet)msg);
	}
}
