package com.hzw.learn.springboot.dataSource.apache_dbcp_BasicDataSource;

import com.hzw.learn.springboot.dataSource.apache_dbcp_BasicDataSource.configs.BasicDataSourceConfig;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.invoke.VolatileCallSite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class MainTest_BasicDataSource {
    public static void main(String[] args) throws SQLException {

        int threadNum = 11;
        int threadSleep = 5;

        AnnotationConfigApplicationContext annoApplicationContext
                = new AnnotationConfigApplicationContext(BasicDataSourceConfig.class);

        BasicDataSource basicDataSource = (BasicDataSource) annoApplicationContext.getBean("basicDataSource");

        for (int i=1; i <= threadNum; i++){
            new Thread(() -> {
                while (true) {
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





    }

}
