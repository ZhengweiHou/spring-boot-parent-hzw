package com.hzw.learn.springboot.freemarker;

import com.google.gson.Gson;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class StressTest {
    @Test
    public void stress_0() throws IOException, TemplateException, InterruptedException {
        Configuration cfg = new Configuration();
        StringTemplateLoader templateLoader = new StringTemplateLoader();
//        templateLoader.putTemplate("hello","=${value1}=");
        templateLoader.putTemplate("hello",templateStr);
        cfg.setTemplateLoader(templateLoader);
        cfg.setTemplateUpdateDelayMilliseconds(0); // 设置模板缓存为负数，不缓存

//        StringWriter sw = new StringWriter();
//        Template template = cfg.getTemplate("hello");
//        HashMap<String, String> values = new HashMap<String, String>();
//        values.put("value1","hello");
//        template.process(values,sw);
//
//        System.out.println(sw.toString());


        int processCount = 7;
        int[][] process = new int[processCount][2];

        new Thread(() -> {
            try {
                int x=0;
                while(true) {
                    Thread.sleep(100000);
                    StringTemplateLoader templateLoader2 = new StringTemplateLoader();
                    // templateLoader2.putTemplate("hello", "[=${value1}=]" + x++);
                    templateLoader2.putTemplate("hello", templateStr + x++);
//                    System.out.println("x=" + x);
                    cfg.setTemplateLoader(templateLoader2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        CountDownLatch latch = new CountDownLatch(process.length);

        for (int i=0; i<process.length; i++){
            int[] temp = process[i];
            int finalI = i;
            new Thread(() -> {
                try {
                    HashMap<String, String> values2 = new HashMap<String, String>();
                    values2.put("value1", "process:" + finalI);
                    while(temp[0] < 1000000) {

//                        Thread.sleep(1000);
                        long time_start = System.currentTimeMillis();
                        StringWriter sw2 = new StringWriter();
                        Template template2 = cfg.getTemplate("hello");
                        template2.process(values2, sw2);
                        temp[1] += (System.currentTimeMillis() - time_start);
                        temp[0]++;
//                        System.out.println(sw2.toString());
                    }
                    latch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }


        Thread.sleep(1);
        latch.await();
        Thread.sleep(1000);

        System.out.println(new Gson().toJson(process));

        double thousand_counts = 0;
        double total_time_millis = 0;
        for (int i=0; i<process.length; i++){
            thousand_counts += process[i][0]/10000;
            total_time_millis += process[i][1];
        }

        System.out.println("thousand_counts:" + thousand_counts + "  total_time_millis:" + total_time_millis);
        System.out.println("Average time per thousand:" + (total_time_millis/thousand_counts));
    }



    public static String templateStr="=${value1}=";
    public static String templateStr2="" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}==${value1}=" +
            "=${value1}=";
}
