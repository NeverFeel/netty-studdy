package com.ilidan.reactor.bio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class BioClient {

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        OutputStream outputStream = null;
        BufferedReader reader = null;
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", 5000));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //outputStream = socket.getOutputStream();
            //outputStream.write("客户端向服务端发送的数据：0".getBytes());
            System.out.println(reader.readLine());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
