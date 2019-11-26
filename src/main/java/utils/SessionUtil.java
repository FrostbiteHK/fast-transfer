package utils;

import io.netty.channel.Channel;
import protocol.attribute.Attribute;
import protocol.session.Session;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author J95ha
 * @title: SessionUtil
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/26 14:03
 */
public class SessionUtil {

	/*
	SessionUtil维持了nodeId -> channel 的映射,调用bindSession() 方法的时候，在 map 里面保存这个映射关系
	 */

	private static final Map<String, Channel> NODE_ID_CHANNEL_MAP = new ConcurrentHashMap<>();

	public static void bindSession(Session session, Channel channel) {
		// 给 channel附上了一个属性，这个属性就是当前用户的 Session
		NODE_ID_CHANNEL_MAP.put((session.getNodeId()), channel);
		channel.attr(Attribute.SESSION).set(session);
	}

	public static void unBindSession(Channel channel) {
		if (hasLogin(channel)) {
			Session session = getSession(channel);
			NODE_ID_CHANNEL_MAP.remove(session.getNodeId());
			channel.attr(Attribute.SESSION).set(null);
			System.out.println(new Date() + " " + session + "退出集群！");
		}
	}

	private static boolean hasLogin(Channel channel) {
		return channel.hasAttr(Attribute.SESSION);
	}

	private static Session getSession(Channel channel) {
		return channel.attr(Attribute.SESSION).get();
	}

	public static Map getNodeIdChannelMap() {
		return NODE_ID_CHANNEL_MAP;
	}
}
