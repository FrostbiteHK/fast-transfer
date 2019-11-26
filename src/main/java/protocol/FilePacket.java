package protocol;

import lombok.Data;

import java.io.File;
import java.io.Serializable;

import static protocol.command.Command.FILE_PACKET;

/**
 * @author J95ha
 * @title: FilePacket
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/20 20:18
 */

@Data
public class FilePacket extends Packet implements Serializable {

    private File file;

    private int ACK;

    public Byte getCommand() {
        return FILE_PACKET;
    }

	public FilePacket() {

	}

    public FilePacket(File file) {
    	this.file = file;
    }

    public FilePacket(File file, int ACK) {
    	this.file = file;
    	this.ACK = ACK;
	}

}
