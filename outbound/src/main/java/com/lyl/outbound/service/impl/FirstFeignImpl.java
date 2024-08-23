package com.lyl.outbound.service.impl;

import com.lyl.outbound.service.FirstFeign;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FirstFeignImpl implements FirstFeign {

    @Override
    public String test(Map<String, Object> map) {
        int i = 1/0;
        return "123";
    }

}
