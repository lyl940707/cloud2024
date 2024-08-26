package com.lyl.cloud.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDto {
    @NotNull(message = "用户id不能为空")
    private String userId;
    @NotEmpty(message = "密码不能为空")
    private String password;
    private String code;
    private String code2;
}
