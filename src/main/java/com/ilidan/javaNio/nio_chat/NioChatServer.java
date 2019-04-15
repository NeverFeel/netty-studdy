package com.ilidan.javaNio.nio_chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class NioChatServer {

    private static ConcurrentMap<String, SocketChannel>
            clientChannelCache = new ConcurrentHashMap<>();
    private static Selector selector = null;

    static {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);//设置为非阻塞的，这个很重要
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(5000));

        //将serverSocketChannel注册到selector上，并只对accept事件感兴趣
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端已启动！");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("服务端关闭！");
        }));

        while (true) {
            try {
                int selectNum = selector.select();
                System.out.println("选择channel的个数：" + selectNum);
                if (selectNum == 0) continue;
                Set<SelectionKey> keys = selector.selectedKeys();
                keys.forEach(selectionKey -> {
                    if (selectionKey.isValid()) {//忽略无效的selectionKey
                        handleKey(selectionKey);
                    }
                });

                //清空selectionKeys的集合
                keys.clear();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    private static void handleKey(SelectionKey selectionKey) {
        if (selectionKey.isAcceptable()) {
            handleAcceptable(selectionKey);
        } else if (selectionKey.isReadable()) {
            handleReadable(selectionKey);
        } else if (selectionKey.isWritable()) {
            handleWritable(selectionKey);
        }
    }

    private static void handleAcceptable(SelectionKey selectionKey) {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            String cacheKey = "【" + UUID.randomUUID().toString() + "】";
            clientChannelCache.put(cacheKey, socketChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleReadable(SelectionKey selectionKey) {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            int read = socketChannel.read(byteBuffer);
            if (read > 0) {
                byteBuffer.flip();
                Charset charset = Charset.forName("utf-8");
                String readMessage = String.valueOf(charset.decode(byteBuffer).array());
                System.out.println(socketChannel + ":" + readMessage);
                sendMessage(socketChannel, readMessage);
            }
        } catch (IOException e) {
            try {
                //客户端断开连接时处理
                selectionKey.cancel();
                socketChannel.socket().close();
                socketChannel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private static void sendMessage(SocketChannel socketChannel, String readMessage) throws IOException {
        String key = null;
        for (Map.Entry<String, SocketChannel> entry : clientChannelCache.entrySet()) {
            if (socketChannel == entry.getValue()) {
                key = entry.getKey();
                break;
            }
        }
        for (Map.Entry<String, SocketChannel> entry : clientChannelCache.entrySet()) {
            SocketChannel channel = entry.getValue();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put((key + ":" + readMessage).getBytes());
            byteBuffer.flip();
            channel.write(byteBuffer);
        }
    }

    private static void handleWritable(SelectionKey selectionKey) {

    }

}
