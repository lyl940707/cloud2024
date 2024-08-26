package com.lyl.gateway.handle;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GatewayExceptionHandle implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        return response.writeWith(Mono.just(response.bufferFactory().wrap("Gateway error".getBytes())));
    }
}
