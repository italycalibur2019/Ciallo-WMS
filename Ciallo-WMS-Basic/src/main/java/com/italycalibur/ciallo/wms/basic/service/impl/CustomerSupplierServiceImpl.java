package com.italycalibur.ciallo.wms.basic.service.impl;

import cn.idev.excel.FastExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.italycalibur.ciallo.wms.basic.dto.CustomerSupplierUploadDTO;
import com.italycalibur.ciallo.wms.basic.listener.CustomerSupplierExcelListener;
import com.italycalibur.ciallo.wms.basic.service.ICustomerSupplierService;
import com.italycalibur.ciallo.wms.core.common.Result;
import com.italycalibur.ciallo.wms.core.listener.BaseExcelListener;
import com.italycalibur.ciallo.wms.core.models.entity.CustomerSupplier;
import com.italycalibur.ciallo.wms.core.models.mapper.CustomerSupplierMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 客商信息表 服务实现类
 * </p>
 *
 * @author italycalibur
 * @since 2025-03-02 20:22:42
 */
@Service
public class CustomerSupplierServiceImpl extends ServiceImpl<CustomerSupplierMapper, CustomerSupplier>
        implements ICustomerSupplierService {
    @Override
    public Result<String> upload(MultipartFile file) throws IOException {
        BaseExcelListener<CustomerSupplier, CustomerSupplierMapper, CustomerSupplierUploadDTO> excelListener = new CustomerSupplierExcelListener(baseMapper);
        FastExcel.read(file.getInputStream(), CustomerSupplier.class, excelListener).sheet().doRead();
        return Result.ok("文件上传并处理成功！");
    }
}
