package com.italycalibur.ciallo.wms.core.common;

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
                          @Schema(description = "总页数") Integer pageNum,
                          @Schema(description = "每页记录数") Integer pageSize) {
}
