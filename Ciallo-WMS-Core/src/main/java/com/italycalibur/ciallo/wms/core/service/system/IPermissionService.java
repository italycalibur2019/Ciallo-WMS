package com.italycalibur.ciallo.wms.core.service.system;

import com.italycalibur.ciallo.wms.core.dto.MenuTree;
import com.italycalibur.ciallo.wms.core.models.entity.Permission;
import com.italycalibur.ciallo.wms.core.service.BaseService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类基类
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:13
 */
public interface IPermissionService extends BaseService<Permission> {

    boolean hasPerm(String permKey);

    List<MenuTree> menuTree();
}
