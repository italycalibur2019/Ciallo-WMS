package com.italycalibur.ciallo.wms.admin.service;

import com.italycalibur.ciallo.wms.admin.vo.PermissionVO;
import com.italycalibur.ciallo.wms.core.service.system.IPermissionService;

import java.util.List;

/**
 * @author dhr
 * @version 1.0
 * @description: 权限表 服务类
 * @date 2025-02-25 08:40:32
 */
public interface PermissionService extends IPermissionService {
    List<PermissionVO> listAll();

    PermissionVO findPermById(Long id);
}
