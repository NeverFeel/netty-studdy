package com.ilidan.javaNio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class NioScatteringGatheringTest {

    public static void main(String[] args) {
        ServerSocketChannel channel = null;
        try {
            channel = ServerSocketChannel.open();
            channel.socket().bind(new InetSocketAddress(5051));
            //SocketChannel实现了ScatteringByteChannel和GatheringByteChannel
            SocketChannel socketChannel = channel.accept();

            int messageLength = 2 + 3 + 4;

            ByteBuffer[] byteBuffers = new ByteBuffer[3];
            byteBuffers[0] = ByteBuffer.allocate(2);
            byteBuffers[1] = ByteBuffer.allocate(3);
            byteBuffers[2] = ByteBuffer.allocate(4);

            while (true) {
                int byteRead = 0;
                while (byteRead < messageLength) {
                    long r = socketChannel.read(byteBuffers);
                    byteRead += r;
                    System.out.println("byteRead:" + byteRead);
                    Arrays.asList(byteBuffers).stream().map(buffer -> "capacity："
                            + buffer.capacity() + ",limit："
                            + buffer.limit() + ",position："
                            + buffer.position()).forEach(System.out::println);
                }
                Arrays.asList(byteBuffers).forEach(Buffer::flip);
                long byteWritten = 0;
                while (byteWritten < messageLength) {
                    long w = socketChannel.write(byteBuffers);
                    byteWritten += w;
                }
                Arrays.asList(byteBuffers).forEach(Buffer::clear);
                System.out.println("byteRead：" + byteRead
                        + "，byteWritten：" + byteWritten
                        + "，messageLength：" + messageLength);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
