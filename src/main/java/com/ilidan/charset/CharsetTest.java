package com.ilidan.charset;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class CharsetTest {

    private static final String INPUT_FILE = "charset-input-file.txt";
    private static final String OUTPUT_FILE = "charset-output-file.txt";

    public static void main(String[] args) throws Exception {

        RandomAccessFile inputFile = new RandomAccessFile(INPUT_FILE, "r");
        RandomAccessFile outputFile = new RandomAccessFile(OUTPUT_FILE, "rw");

        FileChannel inputChannel = inputFile.getChannel();
        FileChannel outputChannel = outputFile.getChannel();

        long length = new File(INPUT_FILE).length();
        MappedByteBuffer byteBuffer = inputChannel.map(FileChannel.MapMode.READ_ONLY, 0, length);

        Charset charsetUtf = Charset.forName("utf-8");
        Charset charsetIso = Charset.forName("iso-8859-1");

        CharsetDecoder decoder = charsetUtf.newDecoder();
        CharsetEncoder encoder = charsetUtf.newEncoder();

        CharsetDecoder isoDecoder = charsetIso.newDecoder();

        CharsetEncoder isoEncoder = charsetIso.newEncoder();

        CharBuffer charBuffer = isoDecoder.decode(byteBuffer);

//        CharBuffer charBuffer = decoder.decode(byteBuffer);
        ByteBuffer bf = encoder.encode(charBuffer);

//        ByteBuffer bf = isoEncoder.encode(charBuffer);
        outputChannel.write(bf);

        inputFile.close();
        outputFile.close();
    }

}
