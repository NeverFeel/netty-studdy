package com.ilidan.javaNio;

import java.nio.IntBuffer;
import java.security.SecureRandom;
import java.util.Random;

/**
 * 每次flip要么limit不变，要么变小,
 * 数据量大的时候，存在内存泄露，因为写->读->写，并没有清除掉数组中上一次未读的元素
 *
 */
public class InBufferTest {

    public static void main(String[] args) {

        IntBuffer intBuffer = IntBuffer.allocate(10);
        Random random = new SecureRandom();
        for (int i = 0; i < 6; i++) {
            //向buffer中写
            intBuffer.put(random.nextInt(20));
        }

        System.out.println("capacity:" + intBuffer.capacity()
                + ",position:" + intBuffer.position()
                + ",limit:" + intBuffer.limit());
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
        System.out.println("capacity:" + intBuffer.capacity()
                + ",position:" + intBuffer.position()
                + ",limit:" + intBuffer.limit());

        intBuffer.flip();
        System.out.println("capacity:" + intBuffer.capacity()
                + ",position:" + intBuffer.position()
                + ",limit:" + intBuffer.limit());
        for (int i = 0; i < 3; i++) {
            //向buffer中写
            intBuffer.put(random.nextInt(20));
        }

        intBuffer.flip();
        System.out.println("capacity:" + intBuffer.capacity()
                + ",position:" + intBuffer.position()
                + ",limit:" + intBuffer.limit());

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }


    }

}
