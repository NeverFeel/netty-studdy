package com.ilidan.zero_copy.old_io;

import java.io.DataInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class OldIoServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("localhost", 5000));

        System.out.println("服务端已启动！");
        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            try {
                byte[] bytes = new byte[4096];
                while (true) {
                    int readCount = inputStream.read(bytes);
//                    System.out.println(readCount);
                    if (readCount == -1) {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                socket.close();
            }

        }

    }

}
