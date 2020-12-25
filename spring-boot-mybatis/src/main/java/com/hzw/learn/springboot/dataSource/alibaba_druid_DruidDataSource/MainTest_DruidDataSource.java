package com.hzw.learn.springboot.dataSource.alibaba_druid_DruidDataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.hzw.learn.springboot.dataSource.alibaba_druid_DruidDataSource.configs.DruidDataSourceConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class MainTest_DruidDataSource {
    public static void main(String[] args) throws SQLException {
        int threadNum = 11;
        int threadSleep = 5;

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DruidDataSourceConfig.class);

        DruidDataSource ds = (DruidDataSource) context.getBean("druidDataSource");

        for (int i=1; i <= threadNum; i++){
            new Thread(() -> {
                while (true) {
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

    }

}
