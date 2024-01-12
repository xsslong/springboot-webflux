package com.xsslong.webflux.router;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/**
 * @author HelloWood
 */
@EnableMongoAuditing
@SpringBootApplication(scanBasePackages = "com.xsslong.webflux.redis.repository")
public class ReactiveRouterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveRouterApplication.class, args);
    }

}

