package com.italycalibur.ciallo.wms.core.validator;

import com.italycalibur.ciallo.wms.core.annotation.EnumValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

/**
 * @author dhr
 * @version 1.0
 * @date 2025-02-24 21:20:49
 * @description: 枚举校验实现逻辑
 */
public class EnumValidator implements ConstraintValidator<EnumValid, String> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(EnumValid constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return Arrays.stream(enumClass.getEnumConstants())
                .anyMatch(e -> e.name().equals(value));
    }
}
