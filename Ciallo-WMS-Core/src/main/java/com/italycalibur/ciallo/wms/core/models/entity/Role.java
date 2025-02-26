package com.italycalibur.ciallo.wms.core.models.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.italycalibur.ciallo.wms.core.models.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * <p>
 * <h1>角色表</h1>
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:13
 */
@Getter
@Setter
@ToString(callSuper = true)
@TableName("sys.sys_role")
@Schema(name = "Role", description = "角色表")
public class Role extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    @TableField("role_name")
    @Schema(description = "角色名称")
    @NotNull(message = "角色名称不能为空")
    private String roleName;

    /**
     * 角色描述
     */
    @TableField("role_desc")
    @Schema(description = "角色描述")
    @NotNull(message = "角色描述不能为空")
    private String roleDesc;

    /**
     * 权限集合
     */
    @TableField(exist = false)
    private List<Permission> permissions = new ArrayList<>();
}
