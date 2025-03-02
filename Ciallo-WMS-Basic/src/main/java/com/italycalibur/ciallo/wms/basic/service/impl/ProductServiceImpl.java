package com.italycalibur.ciallo.wms.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.italycalibur.ciallo.wms.basic.service.IProductService;
import com.italycalibur.ciallo.wms.core.models.entity.Product;
import com.italycalibur.ciallo.wms.core.models.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author italycalibur
 * @since 2025-03-02 20:22:42
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
