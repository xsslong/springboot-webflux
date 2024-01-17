package com.xsslong.webflux.multiple.handler;

import com.xsslong.webflux.multiple.entity.User;
import com.xsslong.webflux.multiple.service.JpaEntityService;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author xutong
 */
@Slf4j
@Component
public class UserReactiveHandler {

    @Resource
    private JpaEntityService jpaEntityService;

    /**
     * 得到所有用户
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> getAllUser(ServerRequest request) {
        log.info("方法 getAllUser 被调用了");
        Flux<User> userFlux = jpaEntityService.selectAllUser();
        return ServerResponse.ok().
                contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(userFlux, User.class);

    }

    /**
     * 创建用户
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> createUser(ServerRequest request) {
        /**Mono 使用响应式的,时候都是一个流,是一个发布者,任何时候都不能调用发布者的订阅方法
         也就是不能消费它, 最终的消费还是交给我们的Springboot来对它进行消费,任何时候不能调用它的
         user.subscribe();
         不能调用block
         把异常放在统一的地方来处理
         */
        Mono<User> userRequestMono = request.bodyToMono(User.class);

        Mono<User> mono = userRequestMono.filter(dto -> {
            // 校验代码需要放在这里
            if (StringUtils.isBlank(dto.getName())) {
                try {
                    throw new Exception("用户名不能为空");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return true;
        });
        Mono<User> monoResultMono = Mono.create(cityMonoSink -> cityMonoSink.success(jpaEntityService.addUser(mono.block())));

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(monoResultMono, User.class);
    }

    /**
     * 根据id删除用户
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> deleteUserById(ServerRequest request) {
        String id = request.pathVariable("id");
        // 校验代码需要放在这里
        if (StringUtils.isBlank(id)) {
            try {
                throw new Exception("id不能为空");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        User dto = new User();
        dto.setUserId(Long.parseLong(id));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.create(cityMonoSink -> cityMonoSink.success(jpaEntityService.delUser(dto))), User.class);
    }

}