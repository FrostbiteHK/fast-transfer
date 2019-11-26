package protocol.response;

import lombok.Data;
import protocol.Packet;

import static protocol.command.Command.LOGIN_PACKET_RESPONSE;

/**
 * @author J95ha
 * @title: LoginResponsePacket
 * @projectName fast-transfer
 * @description: 登录响应的数据包
 * @date 2019/11/26 13:09
 */
@Data
public class LoginResponsePacket extends Packet {

	private String id;

	private String name;

	public LoginResponsePacket() {
	}

	public LoginResponsePacket(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public Byte getCommand() {
		return LOGIN_PACKET_RESPONSE;
	}


}
