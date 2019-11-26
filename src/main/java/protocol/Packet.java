package protocol;

import lombok.Data;

/**
 * @author J95ha
 * @title: Packet
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/20 20:17
 */


@Data
public abstract class Packet {

    /**
     * 指令
     * @return
     */
    public abstract Byte getCommand();
}
