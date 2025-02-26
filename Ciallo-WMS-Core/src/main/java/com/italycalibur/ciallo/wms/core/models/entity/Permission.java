package com.italycalibur.ciallo.wms.core.models.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.italycalibur.ciallo.wms.core.enums.MenuTypeEnum;
import com.italycalibur.ciallo.wms.core.models.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
/**
 * <p>
 * <h1>权限表</h1>
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-24 20:31:47
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
    @NotNull(message = "权限名称不能为空")
    private String permName;

    /**
     * 权限代码，如：user:create
     */
    @TableField("perm_key")
    @Schema(description = "权限代码，如：user:create")
    @NotNull(message = "权限代码不能为空")
    private String permKey;

    /**
     * 菜单类型
     */
    @TableField("menu_type")
    @Schema(description = "菜单类型")
    @EnumValue
    private MenuTypeEnum menuType;

    /**
     * 父级菜单主键
     */
    @TableField("parent_id")
    @Schema(description = "父级菜单主键")
    @NotNull(message = "父级菜单不能为空")
    private Long parentId;

    /**
     * 路由路径
     */
    @TableField("path")
    @Schema(description = "路由路径")
    private String path;

    /**
     * 组件路径
     */
    @TableField("component")
    @Schema(description = "组件路径")
    private String component;

    /**
     * 菜单图标
     */
    @TableField("icon")
    @Schema(description = "菜单图标")
    private String icon;

    /**
     * 排序
     */
    @TableField("sort")
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 是否显示
     */
    @TableField("visible")
    @Schema(description = "是否显示")
    private Boolean visible;
}
