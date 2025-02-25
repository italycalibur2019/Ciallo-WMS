package com.italycalibur.ciallo.wms.core.models.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.italycalibur.ciallo.wms.core.models.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
/**
 * <p>
 * <h1>部门表</h1>
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:12
 */
@Getter
@Setter
@ToString(callSuper = true)
@TableName("system.sys_dept")
@Schema(name = "Dept", description = "部门表")
public class Dept extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 父级部门主键，总公司默认为0
     */
    @TableField("parent_id")
    @Schema(description = "父级部门主键，总公司默认为0")
    @NotNull(message = "父级部门不能为空")
    private Long parentId;

    /**
     * 部门名称
     */
    @TableField("dept_name")
    @Schema(description = "部门名称")
    @NotNull(message = "部门名称不能为空")
    private String deptName;

    /**
     * 部门类型，C：总公司 D：子部门
     */
    @TableField("dept_type")
    @Schema(description = "部门类型，C：总公司 D：子部门")
    @Pattern(regexp = "^[C|D]$", message = "无效的部门类型")
    private String deptType;
}
