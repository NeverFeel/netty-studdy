package com.ilidan.zero_copy.new_io;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewIoServer {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.setReuseAddress(true);
        serverSocket.bind(new InetSocketAddress(5000));
        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while (true) {
            SocketChannel socketChannel = null;
            int totalRead = 0;
            try{
                //该方法会阻塞，直到有连接进来
                socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(true);
                int read = 0;
                while (true) {
                    read = socketChannel.read(byteBuffer);
                    if (read <= 0) {
                        break;
                    }
                    byteBuffer.rewind();
                    totalRead += read;
                }
            }catch (Exception e){
                socketChannel.close();
            }
            System.out.println("totalRead:" + totalRead);
        }
    }

}
