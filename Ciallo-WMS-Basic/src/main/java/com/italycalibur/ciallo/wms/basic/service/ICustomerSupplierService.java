package com.italycalibur.ciallo.wms.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.italycalibur.ciallo.wms.core.common.Result;
import com.italycalibur.ciallo.wms.core.models.entity.CustomerSupplier;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 客商信息表 服务类
 * </p>
 *
 * @author italycalibur
 * @since 2025-03-02 20:22:42
 */
public interface ICustomerSupplierService extends IService<CustomerSupplier> {

    Result<String> upload(MultipartFile file) throws IOException;
}
