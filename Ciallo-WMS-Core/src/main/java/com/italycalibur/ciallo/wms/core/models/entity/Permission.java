package com.italycalibur.ciallo.wms.core.models.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.italycalibur.ciallo.wms.core.models.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * <p>
 * <h1>权限表</h1>
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:13
 */
@Getter
@Setter
@ToString(callSuper = true)
@TableName("sys.sys_permission")
@Schema(name = "Permission", description = "权限表")
public class Permission extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 权限名称
     */
    @TableField("perm_name")
    @Schema(description = "权限名称")
    private String permName;

    /**
     * 权限代码，如：user:create
     */
    @TableField("perm_key")
    @Schema(description = "权限代码，如：user:create")
    private String permKey;
}
