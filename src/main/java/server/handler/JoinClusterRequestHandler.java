package server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.request.LoginRequestPacket;
import protocol.response.LoginResponsePacket;
import protocol.session.Session;
import utils.IDUtil;
import utils.SessionUtil;

import java.util.Date;

/**
 * @author J95ha
 * @title: JoinClusterRequestHandler
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/26 15:09
 */
@ChannelHandler.Sharable
public class JoinClusterRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
		String id = IDUtil.randomId();
		System.out.println(new Date() + " [" + loginRequestPacket.getName() + "-" + id + "]加入集群");
		SessionUtil.bindSession(new Session(id, loginRequestPacket.getName()), ctx.channel());

		ctx.writeAndFlush(new LoginResponsePacket(id, loginRequestPacket.getName()));
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		SessionUtil.unBindSession(ctx.channel());
	}
}

