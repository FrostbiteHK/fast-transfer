package protocol.request;

import lombok.Data;
import protocol.Packet;

import static protocol.command.Command.LOGIN_PACKET_REQUEST;

/**
 * @author J95ha
 * @title: LoginRequestPacket
 * @projectName fast-transfer
 * @description: 登录请求的数据包
 * @date 2019/11/26 13:08
 */

@Data
public class LoginRequestPacket extends Packet {

	private String name;

	private String id;

	public LoginRequestPacket() {
	}

	public LoginRequestPacket(String name) {
		this.name = name;
	}

	public LoginRequestPacket(String name, String id) {
		this.name = name;
		this.id = id;
	}

	@Override
	public Byte getCommand() {
		return LOGIN_PACKET_REQUEST;
	}
}
