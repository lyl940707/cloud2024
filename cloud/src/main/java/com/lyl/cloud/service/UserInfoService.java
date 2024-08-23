package com.lyl.cloud.service;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyl.cloud.pojo.UserInfo;

import java.util.Map;

public interface UserInfoService {
    UserInfo save(Map<String,Object> requestMap);

    void delete(Map<String,Object> requestMap);

    UserInfo update(Map<String,Object> requestMap);

    UserInfo select(Map<String,Object> requestMap);

    Page<UserInfo> selectPage(Map<String,Object> requestMap);
}
