package com.ilidan.encode_decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 自定义解码器
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("decode invoke！");
        System.out.println(in.readableBytes());
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }

}
