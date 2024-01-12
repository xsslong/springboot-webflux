package com.xsslong.webflux.router.router;

import com.xsslong.webflux.router.handler.PostHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author HelloWood
 * @date 2019-01-10 17:11
 */
@Component
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(PostHandler postHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/posts"), postHandler::list)
                .andRoute(RequestPredicates.POST("/posts").and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), postHandler::save)
                .andRoute(RequestPredicates.GET("/posts/{id}"), postHandler::get)
                .andRoute(RequestPredicates.PUT("/posts/{id}").and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), postHandler::update)
                .andRoute(RequestPredicates.DELETE("/posts/{id}"), postHandler::delete);
    }
}
