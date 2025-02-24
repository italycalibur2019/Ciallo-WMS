package com.italycalibur.ciallo.wms.core.service.impl;

import com.italycalibur.ciallo.wms.core.exception.CialloException;
import com.italycalibur.ciallo.wms.core.models.entity.User;
import com.italycalibur.ciallo.wms.core.models.mapper.UserMapper;
import com.italycalibur.ciallo.wms.core.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public boolean assignRole(Long userId, List<Long> roleIds) {
        // 参数校验
        if (userId == null || roleIds == null) {
            throw new CialloException("参数不能为空");
        }

        // 清除现有角色关联
        baseMapper.deleteUserRoles(userId);

        // 插入新角色关联（过滤空值）
        return roleIds.stream()
                .filter(Objects::nonNull)
                .allMatch(roleId -> baseMapper.insertUserRole(userId, roleId) > 0);
    }
}
