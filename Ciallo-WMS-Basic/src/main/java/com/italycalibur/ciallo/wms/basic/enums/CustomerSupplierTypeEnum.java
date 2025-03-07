package com.italycalibur.ciallo.wms.basic.enums;

import com.italycalibur.ciallo.wms.core.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dhr
 * @version 1.0
 * @date 2025-03-02 20:09:46
 * @description: 客商类型枚举
 */
@Getter
@AllArgsConstructor
public enum CustomerSupplierTypeEnum implements BaseEnum<String> {
    /**
     * 客户
     */
    CUSTOMER("客户"),
    /**
     * 供应商
     */
    SUPPLIER("供应商");

    private final String value;
}
