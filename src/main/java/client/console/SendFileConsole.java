package client.console;

import io.netty.channel.Channel;
import protocol.FilePacket;

import java.io.File;
import java.util.Scanner;

/**
 * @author J95ha
 * @title: SendFileConsole
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/26 12:48
 */
public class SendFileConsole {

	public static void exec(Channel channel) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入要传输的文件路径：");
		String path = sc.nextLine();
		File file = new File(path);
		FilePacket filePacket = new FilePacket(file);
		channel.writeAndFlush(filePacket);

	}
}
