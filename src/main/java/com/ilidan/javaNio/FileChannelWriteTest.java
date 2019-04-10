package com.ilidan.javaNio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelWriteTest {

    public static void main(String[] args) throws IOException {
        File file = new File("javaNioTest.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            FileChannel channel = fileOutputStream.getChannel();
            byte[] bytes = "Hello byteBuffer!".getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(64);

            //写入到byteBuffer
            for (int i = 0; i<bytes.length; i++){
                byteBuffer.put(bytes[i]);
            }

            byteBuffer.flip();

            channel.write(byteBuffer);
        } catch (Exception e) {
            //ignore exception
            e.printStackTrace();
        }

    }

}
