package com.lyl.common.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class MyDataSource extends DruidDataSource {

//    @Override
//    public void setUsername(String userName) {
//        super.setUsername(userName);
//    }
//
//    @Override
//    public void setPassword(String password) {
//        super.setPassword(password);
//    }
//
//    @Override
//    public void setUrl(String jdbcUrl) {
//        super.setUrl(jdbcUrl);
//    }

}

