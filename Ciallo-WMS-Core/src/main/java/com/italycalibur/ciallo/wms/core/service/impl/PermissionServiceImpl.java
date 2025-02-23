package com.italycalibur.ciallo.wms.core.service.impl;

import com.italycalibur.ciallo.wms.core.models.entity.Permission;
import com.italycalibur.ciallo.wms.core.models.mapper.PermissionMapper;
import com.italycalibur.ciallo.wms.core.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:13
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
