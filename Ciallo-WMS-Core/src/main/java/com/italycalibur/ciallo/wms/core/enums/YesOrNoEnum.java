package com.italycalibur.ciallo.wms.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dhr
 * @version 1.0
 * @date 2025-03-02 20:04:27
 * @description: 是否枚举
 */
@Getter
@AllArgsConstructor
public enum YesOrNoEnum {
    /**
     * 是
     */
    YES("1", true, "是"),
    /**
     * 否
     */
    NO("0", false, "否");

    private final String code;
    private final Boolean value;
    private final String desc;
}
