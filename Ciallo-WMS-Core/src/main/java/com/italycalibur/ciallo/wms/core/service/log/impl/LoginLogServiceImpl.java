package com.italycalibur.ciallo.wms.core.service.log.impl;

import com.italycalibur.ciallo.wms.core.models.entity.LoginLog;
import com.italycalibur.ciallo.wms.core.models.mapper.LoginLogMapper;
import com.italycalibur.ciallo.wms.core.service.log.ILoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录日志 服务实现类
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-27 08:46:14
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {

}
