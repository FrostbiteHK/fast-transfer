package codec;

import io.netty.buffer.ByteBuf;
import protocol.FilePacket;
import protocol.Packet;
import protocol.request.LoginRequestPacket;
import protocol.response.LoginResponsePacket;
import protocol.serilizer.Serilizer;

import java.util.HashMap;
import java.util.Map;

import static protocol.command.Command.FILE_PACKET;
import static protocol.command.Command.LOGIN_PACKET_REQUEST;
import static protocol.command.Command.LOGIN_PACKET_RESPONSE;


/**
 * @author J95ha
 * @title: Codec
 * @projectName fast-transfer
 * @description: 自定义协议的编解码
 * @date 2019/11/26 13:19
 */
public class Codec {

		public static final int TYPE = 0x12345678; // 自定义类型头

		private final Map<Byte, Class<? extends Packet>> packetTypeMap;

		public static Codec INSTANCE = new Codec(); // 创建单例

		private Codec() {
			packetTypeMap = new HashMap<>();
			packetTypeMap.put(FILE_PACKET, FilePacket.class);
			packetTypeMap.put(LOGIN_PACKET_REQUEST, LoginRequestPacket.class);
			packetTypeMap.put(LOGIN_PACKET_RESPONSE, LoginResponsePacket.class);

		}

		public void encode(ByteBuf byteBuf, Packet packet) {
			byte[] bytes = Serilizer.DEFAULT.serilize(packet);
			byteBuf.writeInt(TYPE);
			byteBuf.writeByte(packet.getCommand());
			byteBuf.writeInt(bytes.length);
			byteBuf.writeBytes(bytes);
		}

		public Packet decode(ByteBuf byteBuf) {
			byteBuf.readInt();
			Byte command = byteBuf.readByte();
			int len = byteBuf.readInt();
			byte[] bytes = new byte[len];
			byteBuf.readBytes(bytes);

			Class clazz = packetTypeMap.get(command);
			if (clazz == null) {
				throw new NullPointerException("解析失败，没有该类型数据包！");
			}

			return (Packet)Serilizer.DEFAULT.deSerilize(bytes, clazz);
		}
}
