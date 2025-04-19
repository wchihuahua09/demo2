package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;

public interface UserService {
    /**
     * 用户注册
     * @param request 注册请求
     * @return 用户ID
     */
    String register(RegisterRequest request);
    
    /**
     * 用户登录
     * @param request 登录请求
     * @return 登录响应
     */
    LoginResponse login(LoginRequest request);
    
    /**
     * 通过ID获取用户
     * @param id 用户ID
     * @return 用户信息
     */
    User getUserById(String id);
    
    /**
     * 通过邮箱获取用户
     * @param email 邮箱
     * @return 用户信息
     */
    User getUserByEmail(String email);
} 