package com.italycalibur.ciallo.wms.core.utils;

import com.italycalibur.ciallo.wms.core.configs.properties.JwtTokenProperty;
import com.italycalibur.ciallo.wms.core.constants.CommonConstants;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;

/**
 * @author dhr
 * @version 1.0
 * @date 2025-02-09 16:27:58
 * @description: jwt工具类
 */
@Component
@RequiredArgsConstructor
public class JwtUtils {
    private final JwtTokenProperty jwtTokenProperty;
    private final RedisUtils redisUtils;

    /**
     * 生成jwt到期时间
     * @param flag 标记，用于生成不同到期时间的token
     * @return Date
     */
    private Date generateExp(String flag) {
        return switch (flag) {
            case CommonConstants.TOKEN_TYPE_ACCESS ->
                    new Date(System.currentTimeMillis() + jwtTokenProperty.getExpireTime());
            case CommonConstants.TOKEN_TYPE_REFRESH ->
                    new Date(System.currentTimeMillis() + jwtTokenProperty.getRefreshExpireTime());
            default -> null;
        };
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
        String token = Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(generateExp(CommonConstants.TOKEN_TYPE_ACCESS))
                .signWith(generateKey())
                .compact();
        // redis存储token
        redisUtils.set(CommonConstants.TOKEN_TYPE_ACCESS + ":" + token.substring(0, 20), token, Duration.ofHours(24));
        return token;
    }

    /**
     * 生成token
     * 使用HS256算法, 密钥和有效时间从配置文件上取
     * @param token 令牌
     * @return String
     */
    public String generateToken(String token) {
        String refreshToken = Jwts.builder()
                .subject(getUsernameFromToken(token))
                .issuedAt(new Date())
                .expiration(generateExp(CommonConstants.TOKEN_TYPE_REFRESH))
                .signWith(generateKey())
                .compact();
        // redis存储token
        redisUtils.set(CommonConstants.TOKEN_TYPE_REFRESH + ":" + token.substring(0, 20), refreshToken, Duration.ofHours(24));
        return refreshToken;
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

    /**
     * 验证令牌有效性
     * @param token 令牌
     * @return 有效结果
     */
    public boolean validateToken(String token) {
        try {
            if (redisUtils.exists(CommonConstants.TOKEN_TYPE_ACCESS + ":" + token.substring(0, 20))) {
                Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(token);
                return true;
            }
            return false;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * 解析令牌中的用户名
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    /**
     * 登出给令牌设置黑名单
     * @param token 令牌
     */
    public void addBlackListToken(String token) {
        if (redisUtils.exists(CommonConstants.TOKEN_TYPE_ACCESS + ":" + token.substring(0, 20))) {
            // 设置黑名单
            redisUtils.rename(CommonConstants.TOKEN_TYPE_ACCESS + ":" + token.substring(0, 20),
                    CommonConstants.BLACK_LIST_TOKEN + token.substring(0, 20));
            // 删除刷新token
            redisUtils.remove(CommonConstants.TOKEN_TYPE_REFRESH + ":" + token.substring(0, 20));
        }
    }

    /**
     * 判断token是否在黑名单中
     * @param token 令牌
     * @return 返回 true, 否则返回 false
     */
    public boolean isInBlackList(String token) {
        return redisUtils.exists(CommonConstants.BLACK_LIST_TOKEN + token.substring(0, 20));
    }

    /**
     * 从redis中获取刷新token
     * @param token 令牌
     * @return String
     */
    public String getRefreshToken(String token) {
        return redisUtils.find(CommonConstants.TOKEN_TYPE_REFRESH + ":" + token.substring(0, 20));
    }

    /**
     * 判断token是否过期
     * @param token 令牌
     * @return 过期返回 true, 否则返回 false
     */
    public boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return expiration.before(new Date());
    }
}
