package com.italycalibur.ciallo.wms.admin.service;

import com.italycalibur.ciallo.wms.admin.vo.RoleVO;
import com.italycalibur.ciallo.wms.core.service.system.IRoleService;

import java.util.List;

/**
 * @author dhr
 * @version 1.0
 * @description: 角色表 服务类
 * @date 2025-02-25 08:40:44
 */
public interface RoleService extends IRoleService {
    List<RoleVO> listAll();

    RoleVO findRoleById(Long id);
}
