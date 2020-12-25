package com.hzw.learn.springboot.dataSource.apache_dbcp_BasicDataSource.configs;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"com.hzw.learn.springboot.dataSource.apache_dbcp_BasicDataSource"})
public class BasicDataSourceConfig {

    @Bean(name = "basicDataSource")
    public BasicDataSource configBasicDataSource(){
        BasicDataSource ds = new BasicDataSource();

        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://127.0.0.1:3306/hzwlearn");
        ds.setUsername("root");
        ds.setPassword("root");

        ds.setTestOnBorrow(true);
        ds.setTestOnReturn(true);
        ds.setTestWhileIdle(true);
        ds.setValidationQuery("select count(1) from student");


//        GenericObjectPool 相关参数
        ds.setMaxActive(10);
        ds.setMaxIdle(10);
        ds.setMinIdle(3);
        ds.setInitialSize(0);   // 通过测试发现不是初始化多少连接（作用是什么？）

        ds.setMaxWait(-1);   // 最大等待时间（获取连接时超时抛出异常）

        ds.setMinEvictableIdleTimeMillis(10 * 1000);    //连接最小可撤消空闲时间
        ds.setTimeBetweenEvictionRunsMillis(5 * 1000);  // 驱除动作执行间隔(默认-1,不执行撤销动作)
        ds.setNumTestsPerEvictionRun(3);    // 每次驱除动作处理的数量

        return ds;
    }
}
