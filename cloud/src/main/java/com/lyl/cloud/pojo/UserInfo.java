package com.lyl.cloud.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String userId;
    private String userName;
    private String gender;
    private String mobile;
    private String email;
    private String address;
    private String password;
    private Date createTime;
    private Date updateTime;
}
