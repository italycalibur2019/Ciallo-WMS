package com.italycalibur.ciallo.wms.core.annotation;

import com.italycalibur.ciallo.wms.core.validator.EnumValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author dhr
 * @version 1.0
 * @date 2025-02-24 21:19:56
 * @description: 自定义枚举校验注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValidator.class)
public @interface EnumValid {
    Class<? extends Enum<?>> enumClass();

    String message() default "无效的枚举值";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
