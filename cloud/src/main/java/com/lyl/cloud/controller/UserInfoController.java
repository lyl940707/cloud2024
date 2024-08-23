package com.lyl.cloud.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyl.cloud.pojo.UserInfo;
import com.lyl.cloud.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;


    @RequestMapping(value = "/insert")
    public Map<String,Object> insertUserInfo(@RequestBody Map<String,Object> requestMap){
        UserInfo userInfo = userInfoService.save(requestMap);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("errorCode","200");
        resultMap.put("errorMsg","");
        resultMap.put("userInfo",userInfo);
        return resultMap;
    }

    @RequestMapping(value = "/update")
    public Map<String,Object> updateUserInfo(@RequestBody Map<String,Object> requestMap){
        UserInfo userInfo = userInfoService.update(requestMap);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("errorCode","200");
        resultMap.put("errorMsg","");
        resultMap.put("userInfo",userInfo);
        return resultMap;
    }

    @RequestMapping(value = "/delete")
    public Map<String,Object> deleteUserInfo(@RequestBody Map<String,Object> requestMap){
        userInfoService.delete(requestMap);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("errorCode","200");
        resultMap.put("errorMsg","");
        return resultMap;
    }

    @RequestMapping(value = "/select")
    public Map<String,Object> selectUserInfo(@RequestBody Map<String,Object> requestMap){
        UserInfo userInfo = userInfoService.select(requestMap);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("errorCode","200");
        resultMap.put("errorMsg","");
        resultMap.put("userInfo",userInfo);
        return resultMap;
    }

    @RequestMapping(value = "/selectPage")
    public Map<String,Object> selectUserInfoPage(@RequestBody Map<String,Object> requestMap){
        Page<UserInfo> userInfoPage = userInfoService.selectPage(requestMap);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("errorCode","200");
        resultMap.put("errorMsg","");
        resultMap.put("userInfos",userInfoPage.getRecords());
        resultMap.put("totalRecords", userInfoPage.getTotal()); // 总记录数
        resultMap.put("totalPages", userInfoPage.getPages()); // 总页数
        return resultMap;
    }
}
