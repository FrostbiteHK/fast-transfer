package codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author J95ha
 * @title: DecodeHandler
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/26 13:20
 */
public class DecodeHandler extends ByteToMessageDecoder {
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		out.add(Codec.INSTANCE.decode(in));
	}
}
