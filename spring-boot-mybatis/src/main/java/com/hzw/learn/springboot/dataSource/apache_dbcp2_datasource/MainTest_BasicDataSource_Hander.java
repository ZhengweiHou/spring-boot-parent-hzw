package com.hzw.learn.springboot.dataSource.apache_dbcp2_datasource;

import com.hzw.learn.springboot.dataSource.apache_dbcp_BasicDataSource.configs.BasicDataSourceConfig;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MainTest_BasicDataSource_Hander {
    static int threadSleep = 3;
    static volatile ArrayList<Long> thArray = new ArrayList<>();
    static WeakReference<GenericObjectPool> poolWeakReference;

    public static void main(String[] args) throws SQLException, NoSuchFieldException, IllegalAccessException {

        AnnotationConfigApplicationContext annoApplicationContext
                = new AnnotationConfigApplicationContext(BasicDataSourceConfig.class);

        BasicDataSource basicDataSource = (BasicDataSource) annoApplicationContext.getBean("basicDataSource");

        Scanner scanner = new Scanner(System.in);

//        while (thArray != null) {
        while (true) {
            String s = scanner.nextLine();
            switch (s) {
                case "+":
                case "=":
                    addThread(basicDataSource);
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
            showBasicDataSource(basicDataSource);

        }

    }

    static void addThread(BasicDataSource basicDataSource){
        final long stringFlagStr = System.currentTimeMillis();

        synchronized (thArray){
            thArray.add(stringFlagStr);
        }
        new Thread(() -> {
            while (thArray != null && thArray.contains(stringFlagStr)) {
                try {
                    Connection connection = basicDataSource.getConnection();
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
//        thArray = null;
    }

    static void showBasicDataSource(BasicDataSource ds){

        // 反射获取DataSource中的连接池，以观察池子的变化
        if(poolWeakReference==null || poolWeakReference.get() == null) {
            try {
                Field poolField = BasicDataSource.class.getDeclaredField("connectionPool");
                poolField.setAccessible(true);
                // 使用弱引用，不要使用强引用导致原对象回收方式有变化
                poolWeakReference =
                        new WeakReference<GenericObjectPool>((GenericObjectPool) poolField.get(ds));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        GenericObjectPool pool = poolWeakReference.get();

        System.out.println(
            String.format(
                    "thNum:[%s],NumActive:[%s],NumIdle[%s],MaxActive[%s],MaxIdle[%s],MaxWait[%s]\n" +
                    "MinEvictableIdleTimeMillis[%s],TimeBetweenEvictionRunsMillis[%s],NumTestsPerEvictionRun[%s]\n" +
                    "Lifo[%s]"
                    ,thArray==null?0:thArray.size()
                    ,ds.getNumActive()                       // ,pool.getNumActive()
                    ,ds.getNumIdle()                         // ,pool.getNumIdle()
                    ,ds.getMaxActive()                       // ,pool.getMaxActive()
                    ,ds.getMaxIdle()                         // ,pool.getMaxIdle()
                    ,ds.getMaxWait()                         // ,pool.getMaxWait()

                    ,ds.getMinEvictableIdleTimeMillis()      // ,pool.getMinEvictableIdleTimeMillis()
                    ,ds.getTimeBetweenEvictionRunsMillis()   // ,pool.getTimeBetweenEvictionRunsMillis()
                    ,ds.getNumTestsPerEvictionRun()          // ,pool.getNumTestsPerEvictionRun()

                    ,pool==null?null:pool.getLifo()
            )
        );
    }
}
