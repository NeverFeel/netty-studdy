package com.ilidan.basic_concept.b_socket_chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ChatClient {

    public static void main(String[] args) {

        EventLoopGroup loopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast("delimiterBasedFrameDecoder",
                            new DelimiterBasedFrameDecoder(4049, Delimiters.lineDelimiter()))
                            .addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8))
                            .addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8))
                            .addLast("chatClientHandler", new ChatClientHandler());
                }
            });
            ChannelFuture future = bootstrap.connect("localhost", 8899).sync();
            Channel channel = future.channel();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String line = bufferedReader.readLine();
                if ("end".equals(line)) {
                    break;
                }
                channel.writeAndFlush(line + "\r\n");
            }
        } catch (Exception e) {
            //异常信息自行处理
            e.printStackTrace();
        } finally {
            loopGroup.shutdownGracefully();
        }
    }

}
