package com.italycalibur.ciallo.wms.core.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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
    private T conditions;

    // 查询排序类
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "查询排序")
    protected static class OrderItem {
        @Schema(description = "排序字段")
        private String field;    // 排序字段
        @Schema(description = "排序方式")
        private boolean asc;     // 排序方式（true升序，false降序）
    }
    
    // 动态排序处理
    public void dynamicSortingProcessing(QueryWrapper<?> queryWrapper) {
        
        if (!CollectionUtils.isEmpty(this.sorters)) {
            this.sorters.forEach(order -> {
                if (order.isAsc()) {
                    queryWrapper.orderByAsc(order.getField());
                } else {
                    queryWrapper.orderByDesc(order.getField());
                }
            });
        }
    }
}
