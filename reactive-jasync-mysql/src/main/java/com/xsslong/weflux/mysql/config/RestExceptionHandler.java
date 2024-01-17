package com.xsslong.weflux.mysql.config;

import com.xsslong.weflux.mysql.common.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * @author hellowoodes
 */
@Component
@Slf4j
@Order(-2)
public class RestExceptionHandler implements WebExceptionHandler {
    @Override
    public @NotNull Mono<Void> handle(@NotNull ServerWebExchange exchange, @NotNull Throwable ex) {
        if (ex instanceof NotFoundException) {
            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
            return exchange.getResponse().setComplete();
        }
        return Mono.error(ex);
    }
}
