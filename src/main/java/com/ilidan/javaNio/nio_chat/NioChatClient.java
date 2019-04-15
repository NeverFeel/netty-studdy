package com.ilidan.javaNio.nio_chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioChatClient {

    private static Selector selector = null;

    static {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress("localhost", 5000));
            System.out.println("客户端已连接!");
            while (true) {
                int connectNum = selector.select();
                if (connectNum <= 0) continue;
                Set<SelectionKey> keys = selector.selectedKeys();
                keys.forEach(NioChatClient::handleKey);
                keys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void handleKey(SelectionKey selectionKey) {

        if (!selectionKey.isValid()) return;
        if (selectionKey.isConnectable()) {
            handleConnectable(selectionKey);
        } else if (selectionKey.isReadable()) {
            handleReadable(selectionKey);
        }
    }

    private static void handleReadable(SelectionKey selectionKey) {
        try {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//            int count = socketChannel.read(byteBuffer);
//
//            if (count > 0) {
//                String message = new String(byteBuffer.array(), 0, count);
//                System.out.println(message);
//            }

            StringBuilder sb = new StringBuilder();
            Charset charset = Charset.forName("utf-8");
            while (true) {
                int count = socketChannel.read(byteBuffer);
                if (count <= 0) {
                    break;
                }
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()){
                    sb.append(String.valueOf((char)byteBuffer.get()));//有乱码问题
                }
                byteBuffer.clear();//清空buffer，重新开始读
            }

            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void handleConnectable(SelectionKey selectionKey) {
        try {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            if (socketChannel.isConnectionPending()) {
                socketChannel.finishConnect();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                byteBuffer.put((LocalDateTime.now() + "：连接成功!").getBytes());
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                //开一个线程去处理控制台输入
                handleConsoleInput(socketChannel, byteBuffer);
            }
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleConsoleInput(SocketChannel socketChannel, ByteBuffer byteBuffer) {
        ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
        executorService.submit(() -> {
            try {
                byteBuffer.clear();
                InputStreamReader reader = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(reader);
                String sendMessage = br.readLine();
                byteBuffer.put(sendMessage.getBytes());
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
