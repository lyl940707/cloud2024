package com.lyl.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

public class MyPageUtils<T> {

    public static <T> Page<T> getPageInfo(Map<String,Object> reqMap){
        int pageNum = Integer.parseInt(String.valueOf(reqMap.getOrDefault("pageNum","0")));
        int pageSize = Integer.parseInt(String.valueOf(reqMap.getOrDefault("pageSize","0")));
        if(pageNum < 1){
            pageNum = 1;
        }
        if(pageSize < 1 ){
            pageSize = 1;
        }
        if(pageSize > 1000){
            pageSize =1000;
        }
        Page<T> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        return page;
    }

}
