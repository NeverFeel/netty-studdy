package com.ilidan.javaNio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelReadTest {

    public static void main(String[] args) throws Exception {

        File file = new File("javaNioTest.txt");

        if (!file.exists()) {
            throw new FileNotFoundException("不存在 javaNioTest.txt文件");
        }

        try (FileInputStream inputStream = new FileInputStream(file)) {
            FileChannel channel = inputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(64);
            channel.read(byteBuffer);

            byteBuffer.flip();
            StringBuilder readResult = new StringBuilder();
            while (byteBuffer.hasRemaining()) {
                readResult.append((char)byteBuffer.get());
            }

            System.out.println(readResult.toString());
        } catch (Exception e) {
            //ignore exception
            e.printStackTrace();
        }

    }

}
