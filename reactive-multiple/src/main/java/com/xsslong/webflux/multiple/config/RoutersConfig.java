package com.xsslong.webflux.multiple.config;

import com.xsslong.webflux.multiple.handler.UserReactiveHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.WebFilter;

@Configuration
public class RoutersConfig {

    @Value("${server.servlet.context-path:test}")
    private String contextPath;

    @Bean
    public RouterFunction<ServerResponse> routes(UserReactiveHandler handler) {
        // 下面的相当于类里面的 @RequestMapping
        // 得到所有用户
        return RouterFunctions.route()
                .GET("/getAllUser", handler::getAllUser)
                // 创建用户
                .POST("/createUser", handler::createUser)
                // 删除用户
                .DELETE("/deleteUserById/{id}", handler::deleteUserById)
                .build();
    }

    //处理上下文路径，没有上下文路径，此函数可以忽略
    @Bean
    public WebFilter contextPathWebFilter() {
        return (exchange, chain) ->
        {
            ServerHttpRequest request = exchange.getRequest();

            String requestPath = request.getURI().getPath();
            if (requestPath.startsWith(contextPath)) {
                return chain.filter(
                        exchange.mutate()
                                .request(request.mutate().contextPath(contextPath).build())
                                .build());
            }
            return chain.filter(exchange);
        };
    }
}
