package com.italycalibur.ciallo.wms.core.service;

import com.italycalibur.ciallo.wms.core.models.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:13
 */
public interface IUserService extends IService<User> {

    boolean assignRole(Long userId, List<Long> roleIds);
}
