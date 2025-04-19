package com.example.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "登录响应")
public class LoginResponse {
    @ApiModelProperty(value = "用户ID")
    private String userId;
    
    @ApiModelProperty(value = "访问令牌")
    private String token;
    
    @ApiModelProperty(value = "用户姓名")
    private String name;
    
    @ApiModelProperty(value = "用户邮箱")
    private String email;
    
    @ApiModelProperty(value = "用户学校")
    private String school;
    
    @ApiModelProperty(value = "用户专业")
    private String major;
} 