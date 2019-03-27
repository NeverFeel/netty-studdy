package com.ilidan.basic_concept.b_socket.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SocketClientMessageHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("来自服务端：" + ctx.channel().remoteAddress() + ",消息：" + msg);
    }

    /**
     * 当前方法无法向server发送消息，
     * 只有在channelActive阶段时，才可以向server发送消息
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("handlerAdded");
    }

    /**
     * 当前方法无法向server发送消息，
     * 只有在channelActive阶段时，才可以向server发送消息
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("channelRegistered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //当客户端启动时，需要向服务端发送一个消息，否则，客户端和服务端将一直处于等待中。
        ctx.writeAndFlush("hello, Server!");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
