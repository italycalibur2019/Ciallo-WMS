package com.italycalibur.ciallo.wms.core.service;

import com.italycalibur.ciallo.wms.core.models.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:13
 */
public interface IRoleService extends BaseService<Role> {

    boolean assignPerm(Long roleId, List<Long> permIds);
}
