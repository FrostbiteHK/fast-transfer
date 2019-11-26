package server.console;

import io.netty.channel.Channel;
import server.console.impl.SendFileConsole;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author J95ha
 * @title: ConsoleManager
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/26 15:38
 */
public class ConsoleManager implements Console {

	private Map<String, Console> consoleMap;

	public ConsoleManager() {
		consoleMap = new HashMap<>();
		consoleMap.put("sendFile", new SendFileConsole());
	}

	@Override
	public void exec(Channel channel, Scanner scanner) {
		String consoleType = scanner.nextLine();
		Console console = consoleMap.get(consoleType);
		if (console != null) {
			console.exec(channel, scanner);
		} else {
			System.out.println("无法识别指令：" + consoleType);
		}

	}
}
