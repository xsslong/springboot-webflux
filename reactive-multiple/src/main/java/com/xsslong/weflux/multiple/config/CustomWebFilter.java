package com.xsslong.weflux.multiple.config;

import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author HelloWood
 * @date 2019-01-11 16:16
 */
@Component
public class CustomWebFilter implements WebFilter {

    @Override
    @NonNull
    public Mono<Void> filter(ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        if ("/".equals(exchange.getRequest().getURI().getPath())) {
            return chain.filter(
                    exchange.mutate()
                            .request(
                                    exchange.getRequest()
                                            .mutate()
                                            .path("/index.html")
                                            .build()
                            ).build()
            );
        }
        return chain.filter(exchange);
    }
}
