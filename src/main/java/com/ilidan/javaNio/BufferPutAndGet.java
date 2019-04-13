package com.ilidan.javaNio;

import java.nio.ByteBuffer;

/**
 * ByteBuffer类型化的put和get方法，
 * ByteBuffer中如果存在多个类型化数据，那么读写的顺序要一致，
 * 否则可能会抛异常，或者读出错误数据
 */
public class BufferPutAndGet {

    public static void main(String[] args) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.putChar('a');
        byteBuffer.putDouble(4.35);
        byteBuffer.putLong(789789L);
        byteBuffer.put("sadasdasd".getBytes());

        byteBuffer.flip();

        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getDouble());
        System.out.println(byteBuffer.getLong());
        System.out.println((char)byteBuffer.get());
    }

}
