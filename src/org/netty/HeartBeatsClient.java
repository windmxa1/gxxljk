package org.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.HashedWheelTimer;

import org.listener.MyListener;

public class HeartBeatsClient {
	private MyListener listener;
	private Long userId;

	public HeartBeatsClient(MyListener listener,Long userId) {
		this.listener = listener;
		this.userId= userId;
	}

	protected final HashedWheelTimer timer = new HashedWheelTimer();

	private static Bootstrap boot;

	// private final ConnectorIdleStateTrigger idleStateTrigger = new
	// ConnectorIdleStateTrigger();

	static {
		EventLoopGroup group = new NioEventLoopGroup();
		boot = new Bootstrap();
		boot.group(group).channel(NioSocketChannel.class)
				.handler(new LoggingHandler(LogLevel.INFO));
	}

	public HeartBeatsClient() {
	}

	public void connect(int port, String host) throws Exception {
		final ConnectionWatchdog watchdog = new ConnectionWatchdog(boot, timer,
				port, host, true) {
			public ChannelHandler[] handlers() {
				return new ChannelHandler[] { this,
						//new IdleStateHandler(0, 5, 0, TimeUnit.SECONDS),
						new AlarmDecoder(userId), new StringEncoder(),
						new HeartBeatClientHandler(listener) };
			}
		};
		ChannelFuture future = null;
		// 进行连接
		try {
			synchronized (boot) {
				boot.handler(new ChannelInitializer<Channel>() {
					// 初始化channel
					@Override
					protected void initChannel(Channel ch) throws Exception {
						ch.pipeline().addLast(watchdog.handlers());
					}
				});

				future = boot.connect(host, port);
			}

			// 以下代码在synchronized同步块外面是安全的
			future.sync();
			// future.channel().closeFuture().sync();
		} catch (Throwable t) {
			//this.listener.onReadMessage("连接失败！！！！！" , host);
			//this.listener.onConnected(host + ":" + port, false);
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	/*
	 * public static void main(String[] args) throws Exception { int port =
	 * 6179; if (args != null && args.length > 0) { try { port =
	 * Integer.valueOf(args[0]); } catch (NumberFormatException e) { // 采用默认值 }
	 * } // new HeartBeatsClient().connect(port, "192.168.1.167"); }
	 */

}