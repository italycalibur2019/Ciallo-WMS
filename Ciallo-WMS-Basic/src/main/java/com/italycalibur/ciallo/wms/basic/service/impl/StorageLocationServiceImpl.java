package com.italycalibur.ciallo.wms.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.italycalibur.ciallo.wms.basic.service.IStorageLocationService;
import com.italycalibur.ciallo.wms.core.models.entity.StorageLocation;
import com.italycalibur.ciallo.wms.core.models.mapper.StorageLocationMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 库位表 服务实现类
 * </p>
 *
 * @author italycalibur
 * @since 2025-03-02 20:22:42
 */
@Service
public class StorageLocationServiceImpl extends ServiceImpl<StorageLocationMapper, StorageLocation> implements IStorageLocationService {

}
