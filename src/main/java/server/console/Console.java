package server.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author J95ha
 * @title: Console
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/26 15:36
 */
public interface Console {

	void exec(Channel channel, Scanner scanner);
}
