package com.italycalibur.ciallo.wms.core.models.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.italycalibur.ciallo.wms.core.models.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:13
 */
@DS("sys")
public interface PermissionMapper extends BaseMapper<Permission> {

}

