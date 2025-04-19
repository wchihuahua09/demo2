package com.example.demo.mapper.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public class UserMapperImpl implements UserMapper {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private static final RowMapper<User> USER_ROW_MAPPER = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setEmail(rs.getString("email"));
            user.setPasswordHash(rs.getString("password_hash"));
            user.setName(rs.getString("name"));
            user.setAvatarUrl(rs.getString("avatar_url"));
            user.setSchool(rs.getString("school"));
            user.setMajor(rs.getString("major"));
            user.setBio(rs.getString("bio"));
            user.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            user.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
            return user;
        }
    };

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        List<User> users = jdbcTemplate.query(sql, USER_ROW_MAPPER, email);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public int insert(User user) {
        String sql = "INSERT INTO users (id, email, password_hash, name, avatar_url, school, major, bio) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        return jdbcTemplate.update(sql,
                user.getId() != null ? user.getId() : UUID.randomUUID().toString(),
                user.getEmail(),
                user.getPasswordHash(),
                user.getName(),
                user.getAvatarUrl(),
                user.getSchool(),
                user.getMajor(),
                user.getBio());
    }

    @Override
    public User findById(String id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        List<User> users = jdbcTemplate.query(sql, USER_ROW_MAPPER, id);
        return users.isEmpty() ? null : users.get(0);
    }
} 