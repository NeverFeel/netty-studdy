package com.ilidan.javaNio;

import java.nio.ByteBuffer;

/**
 * 分片buffer
 */
public class SliceBuffer {

    public static void main(String[] args) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        for (int i = 0; i < byteBuffer.capacity(); i++) {
            byteBuffer.put((byte) i);
        }

        byteBuffer.position(2).limit(6);

        //分片buffer，其实未创建新的buffer对象，而是原buffer的一个子序列
        //不管修改哪一个buffer，都会影响到原来的buffer。position，limit，mark
        //是相互独立的，新buffer的position是0，limit和capacity是新buffer的容量值
        //mark是未被定义的
        ByteBuffer sliceBuffer = byteBuffer.slice();

        for (int i = 0; i < sliceBuffer.capacity(); i++) {
            byte b = sliceBuffer.get();
            b *= 2;
            //修改分片buffer指定索引的值，这个put方法时一个Absolute put方法
            //它不会改变position的值
            sliceBuffer.put(i, b);
        }

        byteBuffer.position(0).limit(byteBuffer.capacity());

        while (byteBuffer.hasRemaining()){
            System.out.println((int)byteBuffer.get());
        }

    }

}
