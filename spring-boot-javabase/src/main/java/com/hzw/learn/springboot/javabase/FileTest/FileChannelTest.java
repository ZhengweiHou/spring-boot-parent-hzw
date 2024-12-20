package com.hzw.learn.springboot.javabase.FileTest;

import org.junit.Test;

import javax.imageio.stream.FileImageInputStream;
import java.io.*;
import java.nio.channels.FileChannel;

/**
 * @ClassName FileChannelTest
 * @Description TODO
 * @Author houzw
 * @Date 2024/12/2
 **/
public class FileChannelTest {
    @Test
    public void test1() throws IOException, InterruptedException {
        File fileDir = new File("target/temp/");
        System.out.println(fileDir.getAbsolutePath());
        if (!fileDir.exists())
            fileDir.mkdir();

        File sfile = new File(fileDir.getAbsolutePath() + "/sourse.log");
        if (!sfile.exists()){
            sfile.createNewFile();
        }

        File tfile = new File(fileDir.getAbsolutePath() + "/target.log");
        if (!tfile.exists()){
            tfile.createNewFile();
        }


        FileOutputStream sfo = new FileOutputStream(sfile);
        sfo.write("123456789abcdefghijklmnopqrstuvwxyz".getBytes());
        sfo.close();

        Thread.sleep(500);


        FileChannel sfich = new FileInputStream(sfile).getChannel();

        FileChannel tfoch = new FileOutputStream(tfile).getChannel();

        sfich.transferTo(3,10,tfoch);

        sfich.close();
        tfoch.close();


        try (
                BufferedReader sreader = new BufferedReader(new FileReader(sfile));
                BufferedReader treader = new BufferedReader(new FileReader(tfile));
        ) {
            String line;
            while ((line = sreader.readLine()) != null) {
                System.out.println(line);
            }
            while ((line = treader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
