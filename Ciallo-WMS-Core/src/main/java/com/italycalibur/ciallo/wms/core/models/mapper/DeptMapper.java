package com.italycalibur.ciallo.wms.core.models.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.italycalibur.ciallo.wms.core.models.entity.Dept;


/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author italycalibur
 * @since 2025-02-23 15:30:12
 */
@DS("sys")
public interface DeptMapper extends BaseMapper<Dept> {

}

