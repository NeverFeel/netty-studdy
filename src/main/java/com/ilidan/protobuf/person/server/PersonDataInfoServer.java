package com.ilidan.protobuf.person.server;

import com.ilidan.protobuf.person.PersonDataInfo;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class PersonDataInfoServer {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            //用于decode前解决半包和粘包问题（利用包头中的包含数组长度来识别半包粘包）
                            pipeline.addLast(new ProtobufVarint32FrameDecoder());
                            //反序列化指定的Probuf字节数组为protobuf类型。
                            pipeline.addLast(new ProtobufDecoder(PersonDataInfo.Person.getDefaultInstance()));
                            //用于在序列化的字节数组前加上一个简单的包头，只包含序列化的字节长度。
                            pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                            //用于对Protobuf类型序列化。
                            pipeline.addLast(new ProtobufEncoder());
                            pipeline.addLast(new SimpleChannelInboundHandler<PersonDataInfo.Person>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext channelHandlerContext,
                                                            PersonDataInfo.Person person) throws Exception {
                                    System.out.println(person.getName());
                                    System.out.println(person.getAge());
                                    System.out.println(person.getAddress());
                                }
                            });
                        }
                    });
            ChannelFuture future = bootstrap.bind(8899).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            //TODO 异常处理
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}
