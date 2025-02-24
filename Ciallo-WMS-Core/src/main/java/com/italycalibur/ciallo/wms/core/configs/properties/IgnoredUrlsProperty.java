package com.italycalibur.ciallo.wms.core.configs.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description: 放行地址
 * @author dhr
 * @date 2025-02-24 08:52:48
 * @version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "ignored")
public class IgnoredUrlsProperty {
    private String[] urls;
}
