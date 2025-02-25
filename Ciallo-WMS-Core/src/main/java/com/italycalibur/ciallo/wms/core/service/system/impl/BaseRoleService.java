package com.italycalibur.ciallo.wms.core.service.system.impl;

import com.italycalibur.ciallo.wms.core.exception.CialloException;
import com.italycalibur.ciallo.wms.core.models.entity.Role;
import com.italycalibur.ciallo.wms.core.models.mapper.RoleMapper;
import com.italycalibur.ciallo.wms.core.service.system.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 角色表 服务实现类基类
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:13
 */
@Service
public abstract class BaseRoleService extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public boolean assignPerm(Long roleId, List<Long> permIds) {
        // 参数校验
        if (roleId == null || permIds == null) {
            throw new CialloException("参数不能为空");
        }

        // 清除现有权限关联
        baseMapper.deleteRolePerms(roleId);

        // 插入新权限关联（过滤空值）
        return permIds.stream()
                .filter(Objects::nonNull)
                .allMatch(permId -> baseMapper.insertRolePerm(roleId, permId) > 0);
    }
}
