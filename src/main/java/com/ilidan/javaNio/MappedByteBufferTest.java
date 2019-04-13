package com.ilidan.javaNio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferTest {

    private static String MODE = "rw";
    private static String FILE_PATH = "javaNioTest.txt";

    public static void main(String[] args) {

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(FILE_PATH, MODE);
             FileChannel channel = randomAccessFile.getChannel();
        ) {
            MappedByteBuffer mappedByteBuffer = channel.map(
                    FileChannel.MapMode.READ_WRITE, 0,
                    randomAccessFile.length());

            while (mappedByteBuffer.hasRemaining()) {
                System.out.println((char) mappedByteBuffer.get());
            }

            System.out.println("capacity:" + mappedByteBuffer.capacity()
                    + ",position:" + mappedByteBuffer.position()
                    + ",limit:" + mappedByteBuffer.limit());

        } catch (Exception e) {
            e.printStackTrace();
        }

        File file = new File(FILE_PATH);
        file.delete();

    }

}
