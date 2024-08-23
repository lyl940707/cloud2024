package com.lyl.cloud.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyl.cloud.mapper.UserInfoMapper;
import com.lyl.cloud.pojo.UserInfo;
import com.lyl.cloud.service.UserInfoService;
import com.lyl.common.utils.MyPageUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Map;


@Service
public class UserInfoServiceImpl  implements UserInfoService{

    @Autowired
    UserInfoMapper userInfoMapper;


    @Override
    public UserInfo save(Map<String,Object> requestMap) {
        UserInfo userInfo = this.queryInfoByUserId(requestMap.get("userId").toString());
        if(userInfo != null){
            throw new RuntimeException("已存在该用户");
        }
        userInfo = new UserInfo();
        userInfo.setUserId(requestMap.get("userId").toString());
        userInfo.setUserName(requestMap.get("userName").toString());
        userInfo.setAddress(requestMap.get("address").toString());
        userInfo.setEmail(requestMap.get("email").toString());
        userInfo.setGender(requestMap.get("gender").toString());
        userInfo.setMobile(requestMap.get("mobile").toString());
        userInfo.setCreateTime(DateUtil.date());
        userInfo.setUpdateTime(DateUtil.date());
        userInfoMapper.insert(userInfo);
        return userInfo;
    }


    @Override
    public void delete(Map<String, Object> requestMap) {
        Long id = Long.parseLong(requestMap.get("id").toString());
        UserInfo userInfo = this.queryInfoById(id);
        if(userInfo == null){
            throw new RuntimeException("该用户不存在");
        }
        userInfoMapper.deleteById(id);
    }

    @Override
    /**
     * 更新用户信息
     *
     * @param requestMap 包含更新信息的键值对集合
     *                     其中键为字段名称，值为字段值
     * @return 更新后的用户信息
     */
    public UserInfo update(Map<String, Object> requestMap) {
        Long id = Long.parseLong(requestMap.get("id").toString());
        UserInfo userInfo = this.queryInfoById(id);
        if(userInfo == null){
            throw new RuntimeException("该用户不存在");
        }
        BeanUtils.copyProperties(requestMap,userInfo);
        userInfoMapper.updateById(userInfo);
        return userInfo;
    }

    @Override
    public UserInfo select(Map<String, Object> requestMap) {
        Long id = Long.parseLong(requestMap.get("id").toString());
        return this.queryInfoById(id);
    }

    @Override
    public Page<UserInfo> selectPage(Map<String, Object> requestMap) {
        Object savepoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        String userId = String.valueOf(requestMap.getOrDefault("userId",""));
        String userName = String.valueOf(requestMap.getOrDefault("userName",""));
        String mobile = String.valueOf(requestMap.getOrDefault("mobile",""));
        Page<UserInfo> pageInfo = MyPageUtils.getPageInfo(requestMap);
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(userId),"user_id",userId)
                .like(StrUtil.isNotBlank(userName),"user_name",userName)
                .like(StrUtil.isNotBlank(mobile),"mobile",mobile)
                .orderByDesc("create_time");
        Page<UserInfo> userInfoPage = userInfoMapper.selectPage(pageInfo, queryWrapper);
        TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savepoint);
        return userInfoPage;
    }


    private UserInfo queryInfoByUserId(String userId) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return userInfoMapper.selectOne(queryWrapper);
    }

    private UserInfo queryInfoById(long id) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return userInfoMapper.selectOne(queryWrapper);
    }
}
