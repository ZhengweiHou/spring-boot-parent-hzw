package com.hzw.learn.springboot.dataSource.alibaba_druid_DruidDataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.hzw.learn.springboot.dataSource.alibaba_druid_DruidDataSource.configs.DruidDataSourceConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MainTest_DruidDataSource_Hander {
    static int threadSleep = 3;
    static volatile ArrayList<Long> thArray = new ArrayList<>();

    public static void main(String[] args) throws SQLException, NoSuchFieldException, IllegalAccessException {

        AnnotationConfigApplicationContext annoApplicationContext
                = new AnnotationConfigApplicationContext(DruidDataSourceConfig.class);

        DruidDataSource ds = (DruidDataSource) annoApplicationContext.getBean("druidDataSource");

        Scanner scanner = new Scanner(System.in);

//        while (thArray != null) {
        while (true) {
            String s = scanner.nextLine();
            switch (s) {
                case "+":
                case "=":
                    addThread(ds);
                    break;
                case "-":
                    reduceThread();
                    break;
                case "x":
                    reduceAll();
                    break;
                default:
                    break;
            }
            showBasicDataSource(ds);

        }

    }

    static void addThread(DruidDataSource ds){
        final long stringFlagStr = System.currentTimeMillis();

        synchronized (thArray){
            thArray.add(stringFlagStr);
        }
        new Thread(() -> {
            while (thArray != null && thArray.contains(stringFlagStr)) {
                try {
                    Connection connection = ds.getConnection();
                    PreparedStatement ps = connection.prepareStatement("select * from student");
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        rs.getString(1);
                    }
                    TimeUnit.SECONDS.sleep(threadSleep);

                    connection.close();
                } catch (SQLException | InterruptedException throwables) {
                    throwables.printStackTrace();
                }
            }
        }).start();
    }

    static void reduceThread(){
        if (thArray.size() > 0)
            thArray.remove(0);
    }

    static void reduceAll(){
        thArray.clear();
    }

    static void showBasicDataSource(DruidDataSource ds){
        final String[] str = {""};
        AtomicInteger index = new AtomicInteger();
        str[0] += "thNum:[" + (thArray==null?0:thArray.size()) + "]  \n";
        Arrays.stream(DruidDataSource.class.getDeclaredMethods())
                .filter(method -> {return method.getName().startsWith("get") && method.getName().endsWith("Count");})
                .forEach(method -> {
                    try {
                        index.getAndIncrement();
                        str[0] += String.format("%-24s: %5d    ", method.getName().substring(3), method.invoke(ds));
                        if(index.get()%3 == 0) str[0] += "\n";
                    } catch (IllegalAccessException | InvocationTargetException e) {e.printStackTrace();}
                });

        str[0] += String.format("%-24s: %5s    ", "ActivePeak",     ds.getActivePeak());
        str[0] += String.format("%-24s: %5s    ", "ActivePeakTime", ds.getActivePeakTime());
        System.out.println(str[0]);
    }
}
