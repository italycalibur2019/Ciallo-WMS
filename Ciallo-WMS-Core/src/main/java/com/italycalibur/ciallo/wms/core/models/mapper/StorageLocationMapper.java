package com.italycalibur.ciallo.wms.core.models.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.italycalibur.ciallo.wms.core.models.entity.StorageLocation;


/**
 * <p>
 * 库位表 Mapper 接口
 * </p>
 *
 * @author italycalibur
 * @since 2025-03-02 20:22:42
 */
@DS("basic")
public interface StorageLocationMapper extends BaseMapper<StorageLocation> {

}

