package org.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import java.util.Date;

import org.listener.MyListener;
import org.tools.Constans;

@Sharable
public class HeartBeatClientHandler extends ChannelInboundHandlerAdapter {
	private MyListener listener;

	public HeartBeatClientHandler() {
	}

	public HeartBeatClientHandler(MyListener listener) {
		this.listener = listener;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("激活时间是：" + new Date());
		 String sendInfo = "{\"command_code\":\"8000001\",\"description\":\"login\",\"seq_num\":\"0\",\"account\":\"ffrc\",\"password\":\"ffrc12345\"}";
         ByteBuf buf = Unpooled
                 .copyInt(sendInfo.getBytes().length);
         ctx.write(buf);
         ctx.writeAndFlush(Unpooled.copiedBuffer(sendInfo,
                 CharsetUtil.UTF_8));
		// String host = ctx.channel().remoteAddress().toString();
		// 连接成功后，改变颜色
		// listener.onConnected(host.substring(1), true);
		// host = host.substring(1, host.indexOf(":"));
		// System.out.println("HeartBeatClientHandler channelActive");
		ctx.fireChannelActive();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		try {
			// listener.onConnected(ctx.channel().remoteAddress().toString()
			// .substring(1), false);
		} catch (Exception e) {
			System.out.println("断线了");
		}
		System.out.println("停止时间是：" + new Date());
		System.out.println("HeartBeatClientHandler channelInactive");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		String message = (String) msg;
		String host = ctx.channel().remoteAddress().toString();
		host = host.substring(1, host.indexOf(":"));
		Long userId = Constans.SOCKET_USER_MAP.get(ctx.hashCode());
		listener.onReadMessage(message, host,
				Constans.USER_SESSION_MAP.get(userId));
		ReferenceCountUtil.release(msg);
	}
}