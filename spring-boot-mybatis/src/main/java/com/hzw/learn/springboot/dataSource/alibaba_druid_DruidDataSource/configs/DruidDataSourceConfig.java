package com.hzw.learn.springboot.dataSource.alibaba_druid_DruidDataSource.configs;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"com.hzw.learn.springboot.dataSource.alibaba_druid_DruidDataSource"})
public class DruidDataSourceConfig {

    @Bean(name = "druidDataSource")
    public DruidDataSource configDruidDataSource(){
        DruidDataSource ds = new DruidDataSource();

//        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://127.0.0.1:3306/hzwlearn");
        ds.setUsername("root");
        ds.setPassword("root");

        ds.setTestOnBorrow(true);
        ds.setTestOnReturn(true);
        ds.setTestWhileIdle(true);
        ds.setValidationQuery("select count(1) from student");

        ds.setMaxActive(10);
        ds.setMaxIdle(7);
        ds.setMinIdle(3);
        ds.setInitialSize(2);

        ds.setMinEvictableIdleTimeMillis(10 * 1000);
        ds.setMaxEvictableIdleTimeMillis(100 * 1000);
        ds.setTimeBetweenEvictionRunsMillis(5 * 1000);
        ds.setNumTestsPerEvictionRun(3);
        return ds;
    }

}
