package com.hzw.learn.hibernate;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

/**
 * @ClassName HibernateAppConfig
 * @Description TODO
 * @Author houzw
 * @Date 2023/8/15
 **/
@Configuration
public class HibernateAppConfig {

    @Bean
    public DruidDataSource druidDataSource(){
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://127.0.0.1:3306/test");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

        HibernateJpaVendorAdapter jva = new HibernateJpaVendorAdapter();

        emf.setJpaVendorAdapter(jva);
        emf.setPackagesToScan("com.hzw.learn.hibernate");
//        emf.setPersistenceProvider();

        return emf;

    }
}
