package com.ilidan.basic_concept.b_socket_chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    //这里要定义channelGroup为静态的，否则无法进行消息的广播
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        if (msg == null || "".equals(msg.trim())) {
            return;
        }
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (ch == channel) {
                ch.writeAndFlush("【自己】：" + msg + "\n");
            } else {
                ch.writeAndFlush(ch.remoteAddress() + "：" + msg + "\n");
            }
        });

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //向其他channel广播新添加的channel消息
        channelGroup.writeAndFlush("系统消息：" + channel.remoteAddress() + "已加入!\n");
        //将新添加的channel添加到channelGroup中
        channelGroup.add(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("系统消息：" + channel.remoteAddress() + "已上线!");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("系统消息：" + channel.remoteAddress() + "已下线!\n");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //向其他channel广播的channel退出的消息
        channelGroup.writeAndFlush("系统消息：" + channel.remoteAddress() + "已离开!\n");
        //这里不需要执行,因为netty会自动清除断开连接的channel
        //channelGroup.remove(channel);
        System.out.println(channelGroup.size());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.getStackTrace();
        ctx.close();
    }
}
