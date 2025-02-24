package com.italycalibur.ciallo.wms.core.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author dhr
 * @version 1.0
 * @date 2025-02-24 22:08:39
 * @description: 自定义查询请求参数
 */
@Data
@Schema(description = "分页查询请求参数")
public class PageQueryRequest<T> {
    // 分页参数
    @Schema(description = "当前页码")
    private Integer pageNum = 1;
    @Schema(description = "每页记录数")
    private Integer pageSize = 10;

    // 排序参数
    @Schema(description = "排序字段")
    private List<OrderItem> sorters = new ArrayList<>();

    // 动态查询条件（支持多种匹配方式）
    @Schema(description = "动态查询条件")
    private List<QueryCondition> conditions = new ArrayList<>();

    // 范围查询（时间区间示例）
    @Schema(description = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @Schema(description = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    // 构建查询条件的快捷方法
    public QueryWrapper<T> buildWrapper(Class<T> entityClass) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        Field[] fields = entityClass.getDeclaredFields();

        // 处理基础字段条件
        for (QueryCondition condition : conditions) {
            String field = condition.getField();
            Object value = condition.getValue();
            QueryType type = condition.getType();

            if (isValidField(field, fields)) {
                switch (type) {
                    case EQ -> wrapper.eq(field, value);
                    case LIKE -> wrapper.like(field, value);
                    case GT -> wrapper.gt(field, value);
                    case LT -> wrapper.lt(field, value);
                    case GE -> wrapper.ge(field, value);
                    case LE -> wrapper.le(field, value);
                    case IN -> wrapper.in(field, (Collection<?>) value);
                    case BETWEEN -> wrapper.between(field, condition.getMin(), condition.getMax());
                }
            }
        }

        // 处理时间范围
        if (startTime != null && endTime != null) {
            wrapper.between("create_time", startTime, endTime);
        }

        return wrapper;
    }

    private boolean isValidField(String fieldName, Field[] fields) {
        return Arrays.stream(fields)
                .anyMatch(f -> f.getName().equals(fieldName));
    }

    // 查询条件类型枚举
    public enum QueryType {
        EQ,      // 等于
        LIKE,    // 模糊查询
        GT,      // 大于
        LT,      // 小于
        GE,      // 大于等于
        LE,      // 小于等于
        IN,      // 包含
        BETWEEN  // 区间
    }

    // 查询条件包装类
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(description = "查询条件")
    public static class QueryCondition {
        @Schema(description = "查询字段")
        private String field;
        @Schema(description = "查询类型")
        private QueryType type;
        @Schema(description = "查询值")
        private Object value;
        @Schema(description = "最小值（BETWEEN-AND专用）")
        private Object min; // BETWEEN专用
        @Schema(description = "最大值（BETWEEN-AND专用）")
        private Object max; // BETWEEN专用
    }

    // 查询排序类
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "查询排序")
    public static class OrderItem {
        @Schema(description = "排序字段")
        private String field;    // 排序字段
        @Schema(description = "排序方式")
        private boolean asc;     // 排序方式（true升序，false降序）

        // 快速构建方法
        public static OrderItem of(String field, boolean asc) {
            return new OrderItem(field, asc);
        }
    }
}
