package com.lyl.cloud.feign.fallback;

import com.lyl.cloud.feign.OutboundFeign;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 熔断降级处理
 */
@Component
public class OutboundFeignFallback implements OutboundFeign {

    @Override
    public String test(Map<String, Object> map) {
        throw new NoFallbackAvailableException("Boom!", new RuntimeException());
    }
}
