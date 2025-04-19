package com.example.demo.service.impl;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String register(RegisterRequest request) {
        // 检查邮箱是否已被注册
        User existingUser = userMapper.findByEmail(request.getEmail());
        if (existingUser != null) {
            throw new RuntimeException("该邮箱已注册");
        }
        
        // 创建用户
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setSchool(request.getSchool());
        user.setMajor(request.getMajor());
        
        // 保存用户
        userMapper.insert(user);
        
        return user.getId();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        // 根据邮箱查询用户
        User user = userMapper.findByEmail(request.getEmail());
        
        // 验证用户是否存在
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        
        // 验证密码
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("密码错误");
        }
        
        // 生成JWT令牌
        String token = jwtUtil.generateToken(user.getId());
        
        // 构建响应
        LoginResponse response = new LoginResponse();
        response.setUserId(user.getId());
        response.setToken(token);
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setSchool(user.getSchool());
        response.setMajor(user.getMajor());
        
        return response;
    }

    @Override
    public User getUserById(String id) {
        return userMapper.findById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.findByEmail(email);
    }
} 