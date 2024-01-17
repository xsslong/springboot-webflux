package com.xsslong.weflux.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * @author HelloWood
 */
@EnableR2dbcRepositories
@SpringBootApplication(exclude = R2dbcAutoConfiguration.class)
public class MySQLApplication {
    public static void main(String[] args) {
        SpringApplication.run(MySQLApplication.class, args);
    }
}

