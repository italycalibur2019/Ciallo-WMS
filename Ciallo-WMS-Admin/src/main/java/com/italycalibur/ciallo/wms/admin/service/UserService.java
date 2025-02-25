package com.italycalibur.ciallo.wms.admin.service;

import com.italycalibur.ciallo.wms.admin.vo.UserVO;
import com.italycalibur.ciallo.wms.core.service.system.IUserService;

import java.util.List;

/**
 * @author dhr
 * @version 1.0
 * @description: 用户表 服务类
 * @date 2025-02-25 08:40:21
 */
public interface UserService extends IUserService {
    List<UserVO> listAll();

    UserVO findUserById(Long id);
}
