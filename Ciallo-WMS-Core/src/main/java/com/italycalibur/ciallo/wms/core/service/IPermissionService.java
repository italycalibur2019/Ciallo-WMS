package com.italycalibur.ciallo.wms.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.italycalibur.ciallo.wms.core.models.entity.Permission;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:13
 */
public interface IPermissionService extends IService<Permission> {
    boolean hasPerm(String permKey);
}
