package com.italycalibur.ciallo.wms.basic.listener;

import cn.idev.excel.context.AnalysisContext;
import com.italycalibur.ciallo.wms.basic.dto.CustomerSupplierUploadDTO;
import com.italycalibur.ciallo.wms.basic.enums.CustomerSupplierTypeEnum;
import com.italycalibur.ciallo.wms.core.enums.BaseEnum;
import com.italycalibur.ciallo.wms.core.exception.CialloException;
import com.italycalibur.ciallo.wms.core.listener.BaseExcelListener;
import com.italycalibur.ciallo.wms.core.models.entity.CustomerSupplier;
import com.italycalibur.ciallo.wms.core.models.mapper.CustomerSupplierMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @description: 客商信息 Excel监听器
 * @author dhr
 * @date 2025-03-07 14:08:23
 * @version 1.0
 */
@Slf4j
public class CustomerSupplierExcelListener extends BaseExcelListener<CustomerSupplier, CustomerSupplierMapper, CustomerSupplierUploadDTO> {
    public CustomerSupplierExcelListener(CustomerSupplierMapper mapper) {
        super(mapper);
    }

    @Override
    public CustomerSupplier handleData(CustomerSupplierUploadDTO customerSupplierUploadDTO, AnalysisContext analysisContext) {
        CustomerSupplier customerSupplier = new CustomerSupplier();
        customerSupplier.setName(customerSupplierUploadDTO.getName());
        String type = customerSupplierUploadDTO.getType();
        if (BaseEnum.getEnumByValue(CustomerSupplierTypeEnum.class, type) == null) {
            throw new CialloException("客商类型错误");
        }
        customerSupplier.setType(type);
        return customerSupplier;
    }

    @Override
    public void insertData(List<CustomerSupplier> dataList) {
        try {
            getMapper().insert(dataList);
        } catch (Exception e) {
            log.error("批量插入客商信息失败：{}", e.getMessage());
            throw new CialloException("批量插入客商信息失败", e);
        }
    }
}
