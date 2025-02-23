package com.italycalibur.ciallo.wms.core.utils;

import com.italycalibur.ciallo.wms.core.configs.properties.JwtTokenProperty;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author dhr
 * @version 1.0
 * @date 2025-02-09 16:27:58
 * @description: jwt工具类
 */
@Component
public class JwtUtils {
    @Resource
    private JwtTokenProperty jwtTokenProperty;

    /**
     * 生成jwt到期时间
     * @return Date
     */
    private Date generateExp() {
        return new Date(System.currentTimeMillis() + jwtTokenProperty.getExpireTime());
    }

    /**
     * 生成密钥
     * @return SecretKey
     */
    private SecretKey generateKey() {
        return Keys.hmacShaKeyFor(jwtTokenProperty.getSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 生成token
     * 使用HS256算法, 密钥和有效时间从配置文件上取
     * @param userDetails 用户信息，如：用户名，角色，权限等
     * @return String
     */
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(generateExp())
                .signWith(generateKey())
                .compact();
    }

    /**
     * 从请求中获取token
     * @param request 请求
     * @return token
     */
    public String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader(jwtTokenProperty.getHeader());
        if (StringUtils.hasText(header) && header.startsWith(jwtTokenProperty.getPrefix())) {
            return header.substring(jwtTokenProperty.getPrefix().length());
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
