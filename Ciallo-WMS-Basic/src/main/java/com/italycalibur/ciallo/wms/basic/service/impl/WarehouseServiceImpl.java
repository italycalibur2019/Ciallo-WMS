package com.italycalibur.ciallo.wms.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.italycalibur.ciallo.wms.basic.service.IWarehouseService;
import com.italycalibur.ciallo.wms.core.models.entity.Warehouse;
import com.italycalibur.ciallo.wms.core.models.mapper.WarehouseMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 库房信息表 服务实现类
 * </p>
 *
 * @author italycalibur
 * @since 2025-03-02 20:22:42
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements IWarehouseService {

}
