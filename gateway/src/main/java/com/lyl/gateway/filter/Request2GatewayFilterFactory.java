package com.lyl.gateway.filter;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.LinkedHashMap;

@Component
public class Request2GatewayFilterFactory extends AbstractRequestBodyGatewayFilterFactory<Request2GatewayFilterFactory.Config> {

    public Request2GatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public LinkedHashMap<String, Object> modifyBody(Config config, LinkedHashMap<String, Object> originBody, ServerWebExchange exchange) {
        LinkedHashMap<String, Object> changeMap = new LinkedHashMap<>(originBody);
        changeMap.put("code2","02");
        return changeMap;
    }

    @Data
    public static class Config {
        private String name = "AuthGatewayFilterConfig";
    }
}
