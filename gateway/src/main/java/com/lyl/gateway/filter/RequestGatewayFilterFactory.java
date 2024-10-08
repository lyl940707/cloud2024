package com.lyl.gateway.filter;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.LinkedHashMap;

@Component
public class RequestGatewayFilterFactory extends AbstractRequestBodyGatewayFilterFactory<RequestGatewayFilterFactory.Config> {

    public RequestGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public LinkedHashMap<String, Object> modifyBody(Config config, LinkedHashMap<String, Object> originBody, ServerWebExchange exchange) {
        LinkedHashMap<String, Object> changeMap = new LinkedHashMap<>(originBody);
        changeMap.put("code","01");
        return changeMap;
    }

    @Data
    public static class Config {
        private String name = "AuthGatewayFilterConfig";
    }
}
