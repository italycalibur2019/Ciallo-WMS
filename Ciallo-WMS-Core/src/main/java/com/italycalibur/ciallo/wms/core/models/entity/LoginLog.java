package com.italycalibur.ciallo.wms.core.models.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.italycalibur.ciallo.wms.core.models.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * <p>
 * <h1>登录日志</h1>
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-27 08:46:14
 */
@Getter
@Setter
@ToString(callSuper = true)
@TableName("log.t_login_log")
@Schema(name = "LoginLog", description = "登录日志")
public class LoginLog extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @TableField("username")
    @Schema(description = "用户名")
    private String username;

    /**
     * IP地址
     */
    @TableField("ip")
    @Schema(description = "IP地址")
    private String ip;

    /**
     * 登录时间
     */
    @TableField("login_time")
    @Schema(description = "登录时间")
    private Date loginTime;

    /**
     * 登录处理耗时
     */
    @TableField("duration")
    @Schema(description = "登录处理耗时")
    private Long duration;
}
