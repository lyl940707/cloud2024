package com.lyl.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class AbstractRequestBodyGatewayFilterFactory<T> extends AbstractGatewayFilterFactory<T> {

    private final List<HttpMessageReader<?>> messageReaders;

    public AbstractRequestBodyGatewayFilterFactory() {
        super((Class<T>) Object.class);
        this.messageReaders = HandlerStrategies.withDefaults().messageReaders();
    }

    public AbstractRequestBodyGatewayFilterFactory(Class<T> configClass) {
        super(configClass);
        this.messageReaders = HandlerStrategies.withDefaults().messageReaders();
    }

    public AbstractRequestBodyGatewayFilterFactory(
            List<HttpMessageReader<?>> messageReaders) {
        super((Class<T>) Object.class);
        this.messageReaders = messageReaders;
    }


    @Override
    public GatewayFilter apply(T config) {
        return (exchange, chain)->{
            ServerRequest serverRequest = ServerRequest.create(exchange,
                    messageReaders);
            Mono<HashMap> modifiedBody = serverRequest.bodyToMono(LinkedHashMap.class)
                    .flatMap(originalBody -> Mono.just(modifyBody(config, originalBody, exchange))
                            .switchIfEmpty(Mono.empty()));
            BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody,
                    HashMap.class);
            HttpHeaders headers = new HttpHeaders();
            headers.putAll(exchange.getRequest().getHeaders());
            headers.remove(HttpHeaders.CONTENT_LENGTH);
            CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(
                    exchange, headers);
            // 修改请求体
            return bodyInserter.insert(outputMessage, new BodyInserterContext())
                    .then(Mono.defer(() -> {
                        ServerHttpRequest decorator = decorate(exchange, headers,
                                outputMessage);
                        return chain
                                .filter(exchange.mutate().request(decorator).build());
                    }));
        };
    }

    public abstract LinkedHashMap<String, Object> modifyBody(T config, LinkedHashMap<String, Object> originBody, ServerWebExchange exchange);

    public LinkedHashMap<String, Object> emptyBody() {
        return new LinkedHashMap<String, Object>();
    }

    /**
     * 修改请求体
     * @param exchange
     * @param headers
     * @param outputMessage
     * @return
     */
    ServerHttpRequestDecorator decorate(ServerWebExchange exchange, HttpHeaders headers,
                                        CachedBodyOutputMessage outputMessage) {
        return new ServerHttpRequestDecorator(exchange.getRequest()) {
            @Override
            public HttpHeaders getHeaders() {
                long contentLength = headers.getContentLength();
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.putAll(headers);
                if (contentLength > 0) {
                    httpHeaders.setContentLength(contentLength);
                } else {
                    httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                }
                return httpHeaders;
            }
            @Override
            public Flux<DataBuffer> getBody() {
                return outputMessage.getBody();
            }
        };
    }
}
