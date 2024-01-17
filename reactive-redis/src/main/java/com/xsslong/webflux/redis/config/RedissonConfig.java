//package com.xsslong.webflux.redis.config;
//
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
//import java.io.IOException;
//
//@Configuration
//public class RedissonConfig {
//
//
////    @Bean
////    public RedissonClient getRedisson() {
////        Config config = new Config();
////        config.useSingleServer()
////                .setAddress("redis://192.168.6.52:6379").setPassword("123456")
////                .setRetryInterval(5000)
////                .setTimeout(10000)
////                .setConnectTimeout(10000);
////        return Redisson.create(config);
////    }
////
////    @Bean
////    public RedissonClient getRedissionClient(){
////        Config config=new Config();
////        //集群模式,集群节点的地址须使用“redis://”前缀，否则将会报错。
////        //此例集群为3节点，各节点1主1从
////        config.useClusterServers().addNodeAddress("redis://192.168.37.134:7001","redis://192.168.37.134:7002",
////                "redis://192.168.37.134:7003","redis://192.168.37.134:7004","redis://192.168.37.134:7005","redis://192.168.37.134:7006");
////        return Redisson.create(config);
////    }
//}
