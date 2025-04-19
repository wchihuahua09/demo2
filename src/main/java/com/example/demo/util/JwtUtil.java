package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000; // 5小时

    /**
     * 从令牌中获取用户ID
     * @param token 令牌
     * @return 用户ID
     */
    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * 从令牌中获取过期时间
     * @param token 令牌
     * @return 过期时间
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * 从令牌中获取指定的声明
     * @param token 令牌
     * @param claimsResolver 声明解析器
     * @return 声明值
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 从令牌中获取所有声明
     * @param token 令牌
     * @return 所有声明
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    }

    /**
     * 检查令牌是否过期
     * @param token 令牌
     * @return 是否过期
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * 为用户生成令牌
     * @param userId 用户ID
     * @return 令牌
     */
    public String generateToken(String userId) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userId);
    }

    /**
     * 创建令牌
     * @param claims 声明
     * @param subject 主题（用户ID）
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SECRET_KEY)
                .compact();
    }

    /**
     * 验证令牌
     * @param token 令牌
     * @param userId 用户ID
     * @return 是否有效
     */
    public Boolean validateToken(String token, String userId) {
        final String extractedUserId = extractUserId(token);
        return (extractedUserId.equals(userId) && !isTokenExpired(token));
    }
} 