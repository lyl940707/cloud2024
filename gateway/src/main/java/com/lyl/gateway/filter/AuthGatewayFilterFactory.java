package com.lyl.gateway.filter;

import cn.hutool.jwt.JWTUtil;
import com.lyl.common.pojo.MyConstant;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@ConfigurationProperties(prefix = "lyl.notauth")
public class AuthGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthGatewayFilterFactory.Config> {

    @Getter
    @Setter
    private List<String> paths;

    public AuthGatewayFilterFactory() {super(Config.class);}


    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();
            // 放行
            if(paths.contains(path)){
                return chain.filter(exchange);
            }
            String token = exchange.getRequest().getHeaders().getFirst("Authorization");
            // 校验token
            if (token == null || !JWTUtil.verify(token, MyConstant.JWT_KEY.getBytes())) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            return chain.filter(exchange);
        };
    }

    @Data
    public static class Config {
        private String name;
    }
}
