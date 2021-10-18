package com.hzw.learn.springboot.log4j2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileLock {
    public static void main(String[] args) throws IOException, InterruptedException {
        RandomAccessFile rf = new RandomAccessFile("/tmp/hzwlock.lock","rw");
        FileChannel fc = rf.getChannel();
        fc.lock();

        Thread.sleep(30 * 1000);
    }
}
