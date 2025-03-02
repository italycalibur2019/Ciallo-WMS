package com.italycalibur.ciallo.wms.core.models.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.italycalibur.ciallo.wms.core.models.entity.Warehouse;


/**
 * <p>
 * 库房信息表 Mapper 接口
 * </p>
 *
 * @author italycalibur
 * @since 2025-03-02 20:22:42
 */
@DS("basic")
public interface WarehouseMapper extends BaseMapper<Warehouse> {

}

