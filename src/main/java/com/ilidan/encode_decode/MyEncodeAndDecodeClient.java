package com.ilidan.encode_decode;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 解码和编码客户端
 */
public class MyEncodeAndDecodeClient {

    public static void main(String[] args) {

        EventLoopGroup clientGroup = null;

        try {
            clientGroup = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(clientGroup).
                    channel(NioSocketChannel.class).
                    handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("myDecoder", new MyByteToLongDecoder2());
                            pipeline.addLast("myEncoder", new MyLongToByteEncoder());
                            pipeline.addLast("myClientHandler", new SimpleChannelInboundHandler<Long>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
                                    System.out.println("接收来自服务端的信息：" + msg);
                                }

                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ctx.channel().writeAndFlush(31231L);
                                    ctx.channel().writeAndFlush(1L);
                                    ctx.channel().writeAndFlush(2L);
                                    ctx.channel().writeAndFlush(3L);
                                }
                            });
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {

        } finally {
            clientGroup.shutdownGracefully();
        }

    }

}
