package com.italycalibur.ciallo.wms.core.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.italycalibur.ciallo.wms.core.dto.MenuTree;
import com.italycalibur.ciallo.wms.core.models.entity.Permission;

import java.util.List;

/**
 * <p>
 * 权限表 服务类基类
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:13
 */
public interface IPermissionService extends IService<Permission> {

    boolean hasPerm(String permKey);

    List<MenuTree> menuTree();
}
