package com.lyl.cloud.controller;

import com.lyl.cloud.dto.LoginDto;
import com.lyl.cloud.feign.OutboundFeign;
import com.lyl.cloud.service.LoginService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Slf4j
@RefreshScope
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    OutboundFeign outboundFeign;

    /**
     * 登录
     * @param loginDto
     * @return
     */
    @RequestMapping("/login")
    public Map<String,Object> login(@RequestBody @Valid LoginDto loginDto) {
        String token = loginService.login(loginDto);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","登录成功");
        map.put("token",token);
        return map;
    }

    @Value("${lyl.name}")
    private String name;
    @RequestMapping("/test")
    public Map<String,Object> test(@RequestBody @Valid LoginDto loginDto) {
        log.info("name:{}",name);
        Map<String,Object> map = new HashMap<>();
        map.put("userId",loginDto.getUserId());
        map.put("password",loginDto.getPassword());
        String test = outboundFeign.test(map);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",200);
        resultMap.put("result",test);
        return resultMap;
    }

}
