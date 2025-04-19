package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Api(tags = "用户管理")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result<String> register(@RequestBody RegisterRequest request) {
        try {
            String userId = userService.register(request);
            return Result.success(userId);
        } catch (Exception e) {
            return Result.error(400, e.getMessage());
        }
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = userService.login(request);
            return Result.success(response);
        } catch (Exception e) {
            return Result.error(401, e.getMessage());
        }
    }
    
    @GetMapping("/checkEmail")
    @ApiOperation("检查邮箱是否已注册")
    public Result<Boolean> checkEmail(@RequestParam String email) {
        try {
            boolean exists = userService.getUserByEmail(email) != null;
            return Result.success(exists);
        } catch (Exception e) {
            return Result.error(500, e.getMessage());
        }
    }
} 