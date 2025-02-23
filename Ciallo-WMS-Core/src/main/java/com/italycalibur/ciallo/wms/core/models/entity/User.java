package com.italycalibur.ciallo.wms.core.models.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.italycalibur.ciallo.wms.core.models.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * <h1>用户表</h1>
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:13
 */
@Getter
@Setter
@ToString(callSuper = true)
@TableName("sys.sys_user")
@Schema(name = "User", description = "用户表")
public class User extends BaseEntity implements Serializable, UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @TableField("username")
    @Schema(description = "用户名")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    @Schema(description = "密码")
    private String password;

    /**
     * 所属部门主键
     */
    @TableField("dept_id")
    @Schema(description = "所属部门主键")
    private Long deptId;

    /**
     * 所属公司主键
     */
    @TableField("company_id")
    @Schema(description = "所属公司主键")
    private Long companyId;

    /**
     * 是否可用
     */
    @TableField("is_enabled")
    @Schema(description = "是否可用")
    private Boolean isEnabled;

    /**
     * 角色集合
     */
    @TableField(exist = false)
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(perm -> new SimpleGrantedAuthority(perm.getPermKey()))
                .toList();
    }
}
