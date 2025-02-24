package com.italycalibur.ciallo.wms.core.service;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.italycalibur.ciallo.wms.core.common.PageQueryRequest;
import com.italycalibur.ciallo.wms.core.exception.CialloException;
import com.italycalibur.ciallo.wms.core.models.BaseEntity;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.regex.Pattern;

/**
 * @author dhr
 * @version 1.0
 * @date 2025-02-24 22:17:02
 * @description: 自定义服务接口
 */
public interface BaseService<T extends BaseEntity> extends IService<T> {

    // 字段名正则校验规则（防SQL注入）
    Pattern FIELD_NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]+$");

    default Page<T> queryPage(PageQueryRequest<T> pageQuery) {
        Page<T> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        QueryWrapper<T> wrapper = pageQuery.buildWrapper(getEntityClass());

        // 处理多字段排序
        handleSorters(pageQuery, wrapper);

        return getBaseMapper().selectPage(page, wrapper);
    }

    private void handleSorters(PageQueryRequest<T> pageQuery, QueryWrapper<T> wrapper) {
        // 处理排序规则
        pageQuery.getSorters().forEach(order -> {
            String fieldName = order.getField();
            if (isValidSortField(fieldName)) {
                // 获取数据库字段名（支持@TableField映射）
                String columnName = getColumnName(fieldName);
                wrapper.orderBy(true, order.isAsc(), columnName);
            }
        });
    }

    // 校验排序字段合法性
    private boolean isValidSortField(String fieldName) {
        // 基础格式校验
        if (!FIELD_NAME_PATTERN.matcher(fieldName).matches()) {
            throw new CialloException("非法排序字段格式: " + fieldName);
        }

        // 实体字段存在性校验
        try {
            Field field = getEntityClass().getDeclaredField(fieldName);
            return !Modifier.isStatic(field.getModifiers());
        } catch (NoSuchFieldException e) {
            throw new CialloException("不存在的排序字段: " + fieldName);
        }
    }

    // 获取数据库列名（支持@TableField注解映射）
    private String getColumnName(String fieldName) {
        try {
            Field field = getEntityClass().getDeclaredField(fieldName);
            TableField tableField = field.getAnnotation(TableField.class);
            return (tableField != null && StringUtils.isNotBlank(tableField.value()))
                    ? tableField.value()
                    : fieldName;
        } catch (NoSuchFieldException e) {
            return fieldName;
        }
    }
}
