package com.example.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "注册请求")
public class RegisterRequest {
    @ApiModelProperty(value = "邮箱", required = true, example = "user@example.com")
    private String email;
    
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    
    @ApiModelProperty(value = "姓名", required = true, example = "张三")
    private String name;
    
    @ApiModelProperty(value = "学校", required = true, example = "某大学")
    private String school;
    
    @ApiModelProperty(value = "专业", example = "计算机科学")
    private String major;
} 