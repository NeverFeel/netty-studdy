package com.ilidan.basic_concept.d_websoket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WsServer {

    public static void main(String[] args) {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            // HttpServerCodec：将请求和应答消息解码为HTTP消息
                            pipeline.addLast("httpServerCodec", new HttpServerCodec())
                                    // ChunkedWriteHandler：以块的方式进行读写
                                    .addLast("chunkedWriteHandler", new ChunkedWriteHandler())
                                    // HttpObjectAggregator：将HTTP消息的多个部分合成一条完整的HTTP消息
                                    .addLast("httpObjectAggregator", new HttpObjectAggregator(8192))
                                    //用于处理webSocket, /ws为访问webSocket时的uri
                                    .addLast("webSocketServerProtocolHandler", new WebSocketServerProtocolHandler("/ws"))
                                    .addLast("wsServerHandler", new WsServerHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {
            //TODO 异常处理
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}
