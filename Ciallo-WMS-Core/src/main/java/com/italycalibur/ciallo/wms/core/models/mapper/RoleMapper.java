package com.italycalibur.ciallo.wms.core.models.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.italycalibur.ciallo.wms.core.models.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:13
 */
@DS("sys")
public interface RoleMapper extends BaseMapper<Role> {
    Role findByRoleNameWithPermissions(@Param("roleName") String roleName);

    @Delete("DELETE FROM sys.sys_role_permission WHERE role_id = #{roleId}")
    void deleteRolePerms(@Param("roleId") Long roleId);

    @Insert("INSERT INTO sys.sys_role_permission(role_id, perm_id) VALUES(#{roleId}, #{permId})")
    int insertRolePerm(@Param("roleId") Long roleId, @Param("permId") Long permId);
}

