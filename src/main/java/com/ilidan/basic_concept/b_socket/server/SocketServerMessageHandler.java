package com.ilidan.basic_concept.b_socket.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

public class SocketServerMessageHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("来自客户端：" + ctx.channel().remoteAddress() + "，消息：" + msg);
        String serverMsg = "服务端响应的消息：" + UUID.randomUUID().toString().replaceAll("-", "");
        ctx.writeAndFlush(serverMsg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("发生了一个异常:");
        cause.printStackTrace();
    }
}
