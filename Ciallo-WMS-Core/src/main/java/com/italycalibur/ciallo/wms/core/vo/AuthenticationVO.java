package com.italycalibur.ciallo.wms.core.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author dhr
 * @version 1.0
 * @date 2025-03-03 21:59:44
 * @description: 认证VO
 */
@Data
@AllArgsConstructor
@Schema(description = "认证VO")
public class AuthenticationVO {
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "访问令牌")
    private String accessToken;
    @Schema(description = "刷新令牌")
    private String refreshToken;
}
