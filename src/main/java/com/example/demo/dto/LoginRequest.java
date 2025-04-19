package com.example.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "登录请求")
public class LoginRequest {
    @ApiModelProperty(value = "邮箱", required = true, example = "user@example.com")
    private String email;
    
    @ApiModelProperty(value = "密码", required = true)
    private String password;
} 