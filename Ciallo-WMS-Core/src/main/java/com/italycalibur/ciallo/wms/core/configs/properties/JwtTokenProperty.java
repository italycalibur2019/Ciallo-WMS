package com.italycalibur.ciallo.wms.core.configs.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author dhr
 * @version 1.0
 * @date 2025-02-09 16:31:29
 * @description: jwt配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtTokenProperty {
    /**
     * 密钥
     */
    private String secretKey;
    /**
     * 过期时间（1小时）
     */
    private long expireTime;
    /**
     * 刷新过期时间（8小时）
     */
    private long refreshExpireTime;
    /**
     * 前缀
     */
    private String prefix;
    /**
     * 头文件名
     */
    private String header;
}
