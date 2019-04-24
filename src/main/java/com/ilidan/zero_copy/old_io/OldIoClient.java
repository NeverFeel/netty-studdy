package com.ilidan.zero_copy.old_io;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class OldIoClient {

    private static String FILE_PATH = "C:\\Users\\lidan_Y\\Desktop\\38_NIO堆外内存与零拷贝深入讲解.mp4";

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 5000));
        InputStream inputStream = new FileInputStream(FILE_PATH);
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        long startTime = System.currentTimeMillis();
        byte[] bytes = new byte[4096];
        int read = 0;
        int totalRead = 0;
        while (true) {
            read = inputStream.read(bytes, 0, bytes.length);
            totalRead += read;
            if (read <= 0) {
                break;
            }
            outputStream.write(bytes);
        }
        System.out.println("totalRead:" + totalRead + ", 耗时:" + (System.currentTimeMillis() - startTime));
        outputStream.close();
        socket.close();
        inputStream.close();
    }

}
