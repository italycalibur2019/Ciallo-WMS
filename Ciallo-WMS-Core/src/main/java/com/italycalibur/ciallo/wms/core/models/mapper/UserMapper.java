package com.italycalibur.ciallo.wms.core.models.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.italycalibur.ciallo.wms.core.models.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:13
 */
@DS("system")
public interface UserMapper extends BaseMapper<User> {
    User findByUsernameWithRoles(@Param("username") String username);

    @Delete("DELETE FROM sys.sys_user_role WHERE user_id = #{userId}")
    void deleteUserRoles(@Param("userId") Long userId);

    @Insert("INSERT INTO sys.sys_user_role(user_id, role_id) VALUES(#{userId}, #{roleId})")
    int insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

}

