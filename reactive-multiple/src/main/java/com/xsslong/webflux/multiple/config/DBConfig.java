package com.xsslong.webflux.multiple.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
public class DBConfig {
    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test_user?useSSL=false");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //连接池最小空闲的连接数
        dataSource.setMinIdle(5);
        //连接池最大活跃的连接数
        dataSource.setMaxActive(20);
        //初始化连接池时创建的连接数
        dataSource.setInitialSize(10);
        return dataSource;
    }
}