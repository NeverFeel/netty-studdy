package com.ilidan.javaNio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockTest {

    private static String MODE = "rw";
    private static String FILE_PATH = "javaNioTest.txt";

    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile(FILE_PATH, MODE);
             FileChannel channel = file.getChannel()) {
            FileLock fileLock = channel.lock(3, 6, true);
            System.out.println("valid:" + fileLock.isValid());
            System.out.println("shared:" + fileLock.isShared());
            fileLock.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
