package com.italycalibur.ciallo.wms.core.enums;

/**
 * @author dhr
 * @version 1.0
 * @description: 通用枚举接口
 * @date 2025-03-07 14:51:07
 */
public interface BaseEnum<T> {
    /**
     * 获取枚举值
     * @return T
     */
    T getValue();

    /**
     * 根据枚举值获取枚举对象
     * @param enumType 枚举类型
     * @param value 枚举值
     * @return 枚举对象
     * @param <E> 枚举类型
     * @param <T> 值类型
     */
    static <E extends Enum<E> & BaseEnum<T>, T> E getEnumByValue(Class<E> enumType, T value) {
        for (E enumConstant : enumType.getEnumConstants()) {
            if (enumConstant.getValue().equals(value)) {
                return enumConstant;
            }
        }
        return null;
    }
}
