package org.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import org.tools.Constans;

public class AlarmDecoder extends ByteToMessageDecoder {
	public final int BASE_LENGTH = 4;
	private Long userId;

	public AlarmDecoder(Long userId) {
		this.userId = userId;
	}

	private boolean first = true;

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buffer,
			List<Object> out) throws Exception {
		if (first) {
			Constans.inputUSER_SOCKET_MAP(this.userId, ctx.hashCode());
			Constans.inputSOCKET_USER_MAP(ctx.hashCode(), this.userId);
		} else if (Constans.USER_SOCKET_MAP.get(this.userId) != ctx.hashCode()) {// 每个用户只能对应一个连接，多余的连接主动断开
			Constans.SOCKET_USER_MAP.remove(ctx.hashCode(), this.userId);
			ctx.close();
			return;
		}
		first = false;
		// 可读长度必须大于基本长度
		if (buffer.readableBytes() >= BASE_LENGTH) {
			// 防止socket字节流攻击
			// 防止，客户端传来的数据过大
			// 因为，太大的数据，是不合理的
			if (buffer.readableBytes() > 2048) {// 直接把波形数据包过滤掉
				buffer.skipBytes(buffer.readableBytes());
				return;
			}
			buffer.markReaderIndex();
			// 消息的长度
			int length = buffer.readInt();
			// 判断请求数据包数据是否到齐
			if (buffer.readableBytes() < length) {
				// 还原读指针
				buffer.resetReaderIndex();
				return;
			}

			// 读取data数据
			byte[] data = new byte[length];
			buffer.readBytes(data);
			String msg = new String(data, "UTF-8").replace("#", "");
			out.add(msg);
		}
	}
}