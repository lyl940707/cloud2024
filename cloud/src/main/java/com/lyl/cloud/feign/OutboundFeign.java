package com.lyl.cloud.feign;

import com.lyl.cloud.feign.fallback.OutboundFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;


/**
 * 测试Feign
 */
@FeignClient(name = "outbound-service",fallback = OutboundFeignFallback.class)
public interface OutboundFeign {

    @PostMapping("/firstFeign/test")
    String test(Map<String, Object> map);

}

