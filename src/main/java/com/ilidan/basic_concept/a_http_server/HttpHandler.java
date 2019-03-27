package com.ilidan.basic_concept.a_http_server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

public class HttpHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject httpObject) throws Exception {

//        System.out.println(httpObject.getClass().getName());//请求类型信息
//        System.out.println(ctx.channel().remoteAddress());//远程地址信息

        if (httpObject instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) httpObject;
            URI uri = new URI(httpRequest.uri());
            //对于google来说会额外的发送一个获取ico图标的请求
            if ("/favicon.ico".equals(uri)) {
                System.out.println("read favicon.ico");
                return;
            }
            System.out.println("channelRead0 invoke!");
            //创建byteBuf对象信息
            ByteBuf byteBuf = Unpooled.copiedBuffer("Hello, world!", CharsetUtil.UTF_8);
            //创建response对象
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    byteBuf);
            //设置响应头信息
            HttpHeaders httpHeaders = response.headers();
            //设置响应头content_type
            httpHeaders.add(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            //设置响应头content_length信息
            httpHeaders.add(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
            //直接调用write()方法只将数据写入到内存中,
            // writeAndFlush()将数据从内存中刷新并返回到客户端
            ctx.writeAndFlush(response);
        }
        //断开连接的请求
        ctx.channel().close();

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded invoke");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered invoke");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete invoke");
        super.channelReadComplete(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive invoke");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive invoke");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered invoke");
        super.channelUnregistered(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved invoke");
        super.handlerRemoved(ctx);
    }
}
