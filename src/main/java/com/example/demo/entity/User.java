package com.example.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "用户信息")
public class User {
    @ApiModelProperty(value = "用户ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private String id;
    
    @ApiModelProperty(value = "邮箱", required = true, example = "user@example.com")
    private String email;
    
    @ApiModelProperty(value = "密码哈希", required = true)
    private String passwordHash;
    
    @ApiModelProperty(value = "姓名", required = true, example = "张三")
    private String name;
    
    @ApiModelProperty(value = "头像URL", example = "https://example.com/avatar.jpg")
    private String avatarUrl;
    
    @ApiModelProperty(value = "学校", required = true, example = "某大学")
    private String school;
    
    @ApiModelProperty(value = "专业", example = "计算机科学")
    private String major;
    
    @ApiModelProperty(value = "个人简介")
    private String bio;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;
    
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;
    
    // 用于注册的明文密码，不存入数据库
    @ApiModelProperty(value = "密码", required = true)
    private String password;
} 