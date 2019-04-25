package com.ilidan.zero_copy.new_io;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIoClient {

    private static String FILE_PATH = "C:\\Users\\lidan_Y\\Desktop\\38_NIO堆外内存与零拷贝深入讲解.mp4";

    public static void main(String[] args) throws Exception {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 5000));
        FileInputStream inputStream = new FileInputStream(FILE_PATH);
        FileChannel channel = inputStream.getChannel();

        long startTime = System.currentTimeMillis();
        long transfer = channel.transferTo(0, channel.size(), socketChannel);

        System.out.println("读入字节："+transfer+",耗时："+(System.currentTimeMillis()-startTime));

//        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

//        long startDate = System.currentTimeMillis();
//        int read = 0;
//        long totalRead = 0;
//        while (true) {
//            read = channel.read(byteBuffer);
//
//            if(read <= 0){
//                break;
//            }
//            byteBuffer.flip();
//            socketChannel.write(byteBuffer);
//            byteBuffer.clear();
//            totalRead += read;
//        }
//
//        System.out.println("totalRead: "+totalRead+",耗时："+(System.currentTimeMillis()-startDate));
//
//        inputStream.close();
//        channel.close();
//        socketChannel.close();
    }

}
