package com.xsslong.weflux.multiple.controller;

import com.xsslong.weflux.multiple.entity.Result;
import com.xsslong.weflux.multiple.entity.User;
import com.xsslong.weflux.multiple.service.JpaEntityServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Mono 和 Flux 适用于两个场景，即：
 * Mono：实现发布者，并返回 0 或 1 个元素，即单对象。
 * Flux：实现发布者，并返回 N 个元素，即 List 列表对象。
 * 有人会问，这为啥不直接返回对象，比如返回 City/Long/List。
 * 原因是，直接使用 Flux 和 Mono 是非阻塞写法，相当于回调方式。
 * 利用函数式可以减少了回调，因此会看不到相关接口。这恰恰是 WebFlux 的好处：集合了非阻塞 + 异步
 * @author xutong
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserReactiveController {

    @Resource
    private JpaEntityServiceImpl jpaEntityService;

    @RequestMapping(value = "/hello")
    public Mono<Result<String>> hello(@RequestParam(name = "name") String name) {
        log.info("方法 hello 被调用了");
        return Mono.just(Result.onSuccess("hello " + name));
    }

    @PostMapping("/add/v1")
    public Mono<User> userAdd(@RequestBody User dto) {
        //命令式写法
//        jpaEntityService.delUser(dto);

        //响应式写法
        return Mono.create(monoSink -> monoSink.success(jpaEntityService.addUser(dto)));
    }

    @PostMapping("/del/v1")
    public Mono<User> userDel(@RequestBody User dto) {
        //命令式写法
//        jpaEntityService.delUser(dto);

        //响应式写法
        return Mono.create(monoSink -> monoSink.success(jpaEntityService.delUser(dto)));
    }

    @PostMapping("/list/v1")
    public Flux<User> listAllUser() {
        log.info("方法 listAllUser 被调用了");

        //命令式写法 改为响应式 以下语句，需要在流中执行
//        List<User> list = jpaEntityService.selectAllUser();
        //响应式写法
        Flux<User> userFlux = Flux.fromIterable(jpaEntityService.selectAllUser());
        return userFlux;
    }

    @PostMapping("/detail/v1")
    public Mono<User> getUser(@RequestBody User dto) {
        log.info("方法 getUser 被调用了");

        //构造流
        return Mono.justOrEmpty(jpaEntityService.selectOne(dto.getUserId()));
    }

    @PostMapping("/detail/v2")
    public Result<User> getUserV2(@RequestBody User dto) {
        log.info("方法 getUserV2 被调用了");

        User user = jpaEntityService.selectOne(dto.getUserId());
        return Result.onSuccess(user);
    }
}