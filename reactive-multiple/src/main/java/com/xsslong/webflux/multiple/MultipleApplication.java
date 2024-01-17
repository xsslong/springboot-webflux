package com.xsslong.webflux.multiple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author HelloWood
 */
@ComponentScan(basePackages ="com.xsslong.webflux.multiple.repository")
@SpringBootApplication
public class MultipleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleApplication.class, args);
    }

}

