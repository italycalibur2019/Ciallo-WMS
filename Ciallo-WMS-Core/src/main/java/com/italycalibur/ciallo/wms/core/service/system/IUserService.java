package com.italycalibur.ciallo.wms.core.service.system;

import com.italycalibur.ciallo.wms.core.models.entity.User;
import com.italycalibur.ciallo.wms.core.service.BaseService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类基类
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:13
 */
public interface IUserService extends BaseService<User> {

    boolean assignRole(Long userId, List<Long> roleIds);
}
