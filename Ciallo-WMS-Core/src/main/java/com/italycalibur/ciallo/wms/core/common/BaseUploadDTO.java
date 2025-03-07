package com.italycalibur.ciallo.wms.core.common;

import com.italycalibur.ciallo.wms.core.exception.CialloException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: 通用导入DTO
 * @author dhr
 * @date 2025-03-07 15:14:15
 * @version 1.0
 */ 
public abstract class BaseUploadDTO {
    public void validate() {
        List<String> errors = new ArrayList<>();
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(this);

                // 替换空白字符
                if (field.getType() == String.class && value != null) {
                    String strValue = (String) value;
                    // 先替换非换行空白
                    String cleaned = strValue.replaceAll("[\u3000\u00A0\u2002-\u200B\t\\x0B\\f]+", " ");
                    // 再单独处理换行符
                    cleaned = cleaned.replaceAll("\n+", "\n");
                    if (!strValue.equals(cleaned)) {
                        field.set(this, cleaned.trim());
                    }
                }

                // 日期校验逻辑
                if (isDateType(field.getType()) && value != null) {
                    DateFormat formatAnnotation = field.getAnnotation(DateFormat.class);
                    if (formatAnnotation == null) {
                        errors.add(field.getName() + ": 缺少@DateFormat注解");
                        continue;
                    }

                    try {
                        String pattern = formatAnnotation.value();
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        sdf.setLenient(false);
                        sdf.parse(value.toString());
                    } catch (ParseException e) {
                        errors.add(field.getName() + ": 日期格式不合法，要求格式：" + formatAnnotation.value());
                    }
                }
            } catch (IllegalAccessException e) {
                errors.add(field.getName() + ": 字段访问异常");
            }
        }

        if (!errors.isEmpty()) {
            throw new CialloException("数据校验失败: \n" + String.join("\n", errors));
        }
    }

    // 辅助方法判断是否是日期类型
    private boolean isDateType(Class<?> type) {
        return type == Date.class ||
                type == LocalDate.class ||
                type == LocalDateTime.class;
    }

    // 需要定义日期格式注解
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface DateFormat {
        String value() default "yyyy-MM-dd";
    }

}
