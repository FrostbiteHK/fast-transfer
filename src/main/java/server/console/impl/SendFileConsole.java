package server.console.impl;

import io.netty.channel.Channel;
import protocol.FilePacket;
import server.console.Console;
import utils.SessionUtil;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

/**
 * @author J95ha
 * @title: SendFileConsole
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/26 15:37
 */
public class SendFileConsole implements Console {
	@Override
	public void exec(Channel channel, Scanner scanner) {
		System.out.println("【服务器端】请输入文件路径：");
		String path = scanner.nextLine();

		File file = new File(path);
		FilePacket filePacket = new FilePacket(file);

		Map<String, Channel> channelMap = SessionUtil.getNodeIdChannelMap();
		for (Map.Entry<String, Channel> entry : channelMap.entrySet()) {
			entry.getValue().writeAndFlush(filePacket);
		}
	}
}
