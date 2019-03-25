package org.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

import org.listener.ConnectListener;

@Sharable
public class ConnectorIdleStateTrigger extends ChannelInboundHandlerAdapter implements ConnectListener {
    private boolean first = true;
    private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled
            .unreleasableBuffer(Unpooled.copiedBuffer("Heartbeat",
                    CharsetUtil.UTF_8));

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            if (state == IdleState.WRITER_IDLE) {// 在这里发送心跳包给服务器端
                // write heartbeat to server
                String port = ctx.channel().remoteAddress().toString();
//                System.out.println(this.hashCode());
                if (port.split(":")[1].equals("2009")) {
                    String sendInfo = "0064{\"command_code\":1000,\"description\":\"heartbeat\",\"seq_num\":\"xxxx\"}";
                    ctx.writeAndFlush(Unpooled.copiedBuffer(sendInfo,
                            CharsetUtil.UTF_8));
                } else {
                    if (first) {
                        // String sendInfo =
                        // "{\"command_code\":1000,\"description\":\"heartbeat\",\"seq_num\":\"xxxx\"}";
                        String sendInfo = "{\"command_code\":\"8000001\",\"description\":\"login\",\"seq_num\":\"0\",\"account\":\"ffrc\",\"password\":\"ffrc12345\"}";
                        ByteBuf buf = Unpooled
                                .copyInt(sendInfo.getBytes().length);
                        ctx.write(buf);
                        ctx.writeAndFlush(Unpooled.copiedBuffer(sendInfo,
                                CharsetUtil.UTF_8));
                        first = false;
                    } else {
                        String sendInfo = "{\"command_code\":\"16000201\",\"description\":\"心跳\",\"seq_num\":\"0\"}";
                        ByteBuf buf = Unpooled
                                .copyInt(sendInfo.getBytes().length);
                        ctx.write(buf);
                        ctx.writeAndFlush(Unpooled.copiedBuffer(sendInfo,
                                CharsetUtil.UTF_8));
                        first = false;
                    }
                }
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void lostConnect() {
        first = true;
    }
}