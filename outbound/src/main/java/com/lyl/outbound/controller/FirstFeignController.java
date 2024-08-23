package com.lyl.outbound.controller;

import com.lyl.outbound.service.FirstFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/firstFeign")
public class FirstFeignController {

    @Autowired
    FirstFeign firstFeign;

    @RequestMapping("/test")
    public String test(Map<String, Object> map){
        return firstFeign.test(map);
    }
}
