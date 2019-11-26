package client.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.response.LoginResponsePacket;

import java.util.Date;

/**
 * @author J95ha
 * @title: LoginResponseHandler
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/26 14:18
 */
@ChannelHandler.Sharable
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> { // SimpleChannelInboundHandler后面指定了处理类型
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket packet) throws Exception {
		System.out.println(new Date() + " " + packet.getId() + " " + packet.getName() + " 登陆成功");
	}
}
