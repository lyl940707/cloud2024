package com.lyl.cloud.service.impl;

import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyl.cloud.dto.LoginDto;
import com.lyl.cloud.mapper.UserInfoMapper;
import com.lyl.cloud.pojo.UserInfo;
import com.lyl.cloud.service.LoginService;
import com.lyl.common.pojo.MyConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserInfoMapper userInfoMapper;


    @Override
    public String login(LoginDto loginDto) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", loginDto.getUserId())
                .eq("password", loginDto.getPassword());
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        if (userInfo == null) {
            throw new RuntimeException("用户名或密码错误");
        }
        log.info("用户名:{},密码:{}", loginDto.getUserId(), loginDto.getPassword());
        String token = this.createToken(userInfo);
        return token;
    }

    private String createToken(UserInfo userInfo) {
        Map<String ,Object> body = new HashMap<>();
        body.put("userId", userInfo.getUserId());
        body.put("userName", userInfo.getUserName());
//        body.put("role", userInfo.getRole());
        String token = JWTUtil.createToken(body, MyConstant.JWT_KEY.getBytes());
        return token;
    }
}
