package com.ilidan.reactor.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(5000));
        while (!Thread.interrupted()) {
            new Thread(new BioAcceptor(serverSocket.accept())).start();
        }
    }

    static class BioAcceptor implements Runnable {
        private Socket socket;

        public BioAcceptor(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BioHandler handler = new BioHandler(socket);
//            handler.recv();
            handler.write();
        }
    }

    static class BioHandler {
        private Socket socket;

        public BioHandler(Socket socket) {
            this.socket = socket;
        }

        public void recv() {
            byte[] buffer = new byte[1024];
            InputStream input = null;
            try {
                input = socket.getInputStream();
                int read = 0;
                int totalRead = 0;
                while ((read = input.read(buffer)) > -1) {
                    totalRead += read;
                }
                System.out.println("总共读入字节数:" + totalRead);
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    input.close();
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }

        public void write() {
            String message = "服务端写给客户端的数据";
            OutputStream output = null;
            try {
                output = socket.getOutputStream();
                output.write(message.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                output.close();
                socket.close();
            }catch (IOException e1){
                e1.printStackTrace();
            }
        }
    }

}
