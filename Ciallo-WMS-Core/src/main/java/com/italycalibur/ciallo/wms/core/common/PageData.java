package com.italycalibur.ciallo.wms.core.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @author dhr
 * @version 1.0
 * @date 2025-02-24 22:07:02
 * @description: 自定义分页结果
 */
@Schema(description = "自定义分页结果")
public record PageData<T>(@Schema(description = "分页数据") List<T> rows,
                          @Schema(description = "总记录数") Long total,
                          @Schema(description = "总页数") Long pageNum,
                          @Schema(description = "每页记录数") Long pageSize) {
    public static <T> PageData<T> empty() {
        return new PageData<>(List.of(), 0L, 0L, 0L);
    }

    public static <T> PageData<T> of(Page<T> page) {
        return new PageData<>(page.getRecords(), page.getTotal(), page.getCurrent(), page.getSize());
    }

    public static <T> PageData<T> of(List<T> list, Long total, Long pageSize) {
        return new PageData<>(list, total, 1L, pageSize);
    }
}
