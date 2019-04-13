package com.ilidan.javaNio;

import java.nio.ByteBuffer;

/**
 * 通过调用byteBuffer方法的asReadOnlyBuffer返回一个只读的buffer对象，但不能通过只读的buffer转换成读写buffer。
 * 和分片buffer一样，生成的buffer的position，limit和mark也是独立的，修改不会对原buffer影响，对只读的buffer
 * 不能执行写操作，否则会抛出异常信息。
 */
public class ReadOnlyBuffer {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        int i = 0;
        while (true) {
            if (i >= byteBuffer.capacity()) {
                break;
            }
            byteBuffer.put((byte) i++);
        }

        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
//        readOnlyBuffer.put(2, (byte) 10);
        System.out.println("readOnlyBuffer before flip capacity:"
                + readOnlyBuffer.capacity() + ",limit:" + readOnlyBuffer.limit() + ",position:" + readOnlyBuffer.position());
        readOnlyBuffer.flip();
        System.out.println("readOnlyBuffer after flip capacity:"
                + readOnlyBuffer.capacity() + ",limit:" + readOnlyBuffer.limit() + ",position:" + readOnlyBuffer.position());
        while (readOnlyBuffer.hasRemaining()) {
            System.out.println((int) readOnlyBuffer.get());
        }

    }

}
