package com.ilidan.javaNio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class DirectByteBufferTest {

    private static String INPUT_FILE_PATH = "javaNioTest.txt";
    private static String OUTPUT_FILE_PATH = "javaNioTest-copy2.txt";

    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream(INPUT_FILE_PATH);
             FileOutputStream outputStream = new FileOutputStream(OUTPUT_FILE_PATH);
             FileChannel inputFileChannel = inputStream.getChannel();
             FileChannel outputFileChannel = outputStream.getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(20);

            while (true){
               int read =  inputFileChannel.read(byteBuffer);
               if(read == -1){
                   break;
               }
                byteBuffer.flip();
                outputFileChannel.write(byteBuffer);
                byteBuffer.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
