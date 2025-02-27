package com.italycalibur.ciallo.wms.core.models.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.italycalibur.ciallo.wms.core.models.entity.LoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * <p>
 * 登录日志 Mapper 接口
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-27 08:46:14
 */
@DS("log")
public interface LoginLogMapper extends BaseMapper<LoginLog> {

}

