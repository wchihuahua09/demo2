package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    /**
     * 根据邮箱查询用户
     * @param email 邮箱
     * @return 用户信息
     */
    User findByEmail(String email);
    
    /**
     * 插入用户
     * @param user 用户信息
     * @return 影响行数
     */
    int insert(User user);
    
    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户信息
     */
    User findById(String id);
} 