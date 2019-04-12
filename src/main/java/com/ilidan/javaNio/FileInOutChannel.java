package com.ilidan.javaNio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileInOutChannel {

    private static String FILE_PATH_IN = "javaNioTest.txt";
    private static String FILE_PATH_OUT = "javaNioTest-copy.txt";

    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream(FILE_PATH_IN);
             FileOutputStream outputStream = new FileOutputStream(FILE_PATH_OUT)) {
            FileChannel inputChannel = inputStream.getChannel();
            FileChannel outChannel = outputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(50);
            while (true) {
                int readNum = inputChannel.read(byteBuffer);
                System.out.println("read:" + readNum);
                if (-1 == readNum) {
                    break;
                }
                byteBuffer.flip();
                outChannel.write(byteBuffer);
                byteBuffer.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
