package com.ilidan.protobuf.animal.client;

import com.ilidan.protobuf.animal.AnimalData;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import java.util.Random;

public class AnimalClient {

    private static final int CAT_INDEX = 0;
    private static final int MONKEY_INDEX = 1;
    private static final int TIGER_INDEX = 2;

    public static void main(String[] args) {

        EventLoopGroup loopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast(new ProtobufVarint32FrameDecoder());
                    pipeline.addLast(new ProtobufDecoder(AnimalData.Animal.getDefaultInstance()));
                    pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                    pipeline.addLast(new ProtobufEncoder());
                    pipeline.addLast(new SimpleChannelInboundHandler<AnimalData.Animal>() {
                        @Override
                        protected void channelRead0(ChannelHandlerContext channelHandlerContext,
                                                    AnimalData.Animal animal) throws Exception {
                        }
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            //模拟多个连接的情况
                            int randomIndex = new Random().nextInt(3);
                            AnimalData.Animal animal = null;
                            switch (randomIndex) {
                                case CAT_INDEX:
                                    animal = AnimalData.Animal.newBuilder()
                                            .setAnimalType(AnimalData.Animal.AnimalType.CAT_TYPE)
                                            .setCat(AnimalData.Cat.newBuilder()
                                                    .setName("死肥")
                                                    .setColor("white")
                                                    .setAge(2)
                                                    .build())
                                            .build();
                                    break;
                                case MONKEY_INDEX:
                                    animal = AnimalData.Animal.newBuilder()
                                            .setAnimalType(AnimalData.Animal.AnimalType.MONKEY_TYPE)
                                            .setMonkey(AnimalData.Monkey.newBuilder()
                                                    .setName("淘气")
                                                    .setSpecies("水帘洞猴孙")
                                                    .setAge(1)
                                                    .build())
                                            .build();
                                    break;
                                case TIGER_INDEX:
                                    animal = AnimalData.Animal.newBuilder()
                                            .setAnimalType(AnimalData.Animal.AnimalType.TIGER_TYPE)
                                            .setTiger(AnimalData.Tiger.newBuilder()
                                                    .setName("虎~吼~")
                                                    .setSpecies("虎咪")
                                                    .setAge(3)
                                                    .build())
                                            .build();
                                    break;
                            }
                            ctx.channel().writeAndFlush(animal);
                        }
                    });
                }
            });
            ChannelFuture future = bootstrap.connect("localhost", 8899).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            //TODO 异常处理
        } finally {
            loopGroup.shutdownGracefully();
        }

    }

}
