package com.ilidan.protobuf.animal.server;

import com.ilidan.protobuf.animal.AnimalData;
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

public class AnimalServer {

    public static void main(String[] args) {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast(new ProtobufVarint32FrameDecoder());
                    pipeline.addLast(new ProtobufDecoder(AnimalData.Animal.getDefaultInstance()));
                    pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                    pipeline.addLast(new ProtobufEncoder());
                    //自定义handler
                    pipeline.addLast(new SimpleChannelInboundHandler<AnimalData.Animal>() {
                        @Override
                        protected void channelRead0(ChannelHandlerContext channelHandlerContext,
                                                    AnimalData.Animal animal) throws Exception {
                            switch (animal.getAnimalType()) {
                                case MONKEY_TYPE:
                                    AnimalData.Monkey monkey = animal.getMonkey();
                                    System.out.println(monkey.getName());
                                    System.out.println(monkey.getSpecies());
                                    System.out.println(monkey.getAge());
                                    break;
                                case CAT_TYPE:
                                    AnimalData.Cat cat = animal.getCat();
                                    System.out.println(cat.getName());
                                    System.out.println(cat.getColor());
                                    System.out.println(cat.getAge());
                                    break;
                                case TIGER_TYPE:
                                    AnimalData.Tiger tiger = animal.getTiger();
                                    System.out.println(tiger.getName());
                                    System.out.println(tiger.getSpecies());
                                    System.out.println(tiger.getAge());
                                    break;
                            }
                        }
                    });
                }
            });
            ChannelFuture channelFuture = bootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            //TODO 异常处理
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
