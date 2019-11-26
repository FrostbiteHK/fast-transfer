package protocol.command;

/**
 * @author J95ha
 * @title: Command
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/20 20:18
 */
public interface Command {

    Byte FILE_PACKET = 1;

    Byte LOGIN_PACKET_REQUEST = 2;

    Byte LOGIN_PACKET_RESPONSE = 3;
}
