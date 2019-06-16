package com.ilidan.encode_decode;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 编码和解码服务
 */
public class MyEncodeAndDecodeServer {

    public static void main(String[] args) {

        EventLoopGroup bossGroup = null;
        EventLoopGroup workerGroup = null;
        try {
            bossGroup = new NioEventLoopGroup(1);
            workerGroup = new NioEventLoopGroup();
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).
                    channel(NioServerSocketChannel.class).
                    handler(new LoggingHandler(LogLevel.INFO)).
                    childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("myDecoder", new MyByteToLongDecoder2());
                            pipeline.addLast("myEncoder", new MyLongToByteEncoder());
                            pipeline.addLast("myServerHandler", new SimpleChannelInboundHandler<Long>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
                                    System.out.println(String.format("服务器接收到的消息：%s", msg));
                                    ctx.channel().writeAndFlush(123456L);
                                }
                            });
                        }
                    });
            ChannelFuture future = bootstrap.bind(8899).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            //TODO 异常处理
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}
