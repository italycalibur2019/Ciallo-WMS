package com.italycalibur.ciallo.wms.core.dto;

import com.italycalibur.ciallo.wms.core.models.entity.Dept;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 部门树形结构
 * @author dhr
 * @date 2025-02-24 13:37:26
 * @version 1.0
 */
@Getter
@Setter
@Schema(description = "部门树形结构")
public class DeptTree extends Dept {
    @Schema(description = "子部门")
    private List<DeptTree> children = new ArrayList<>();
}
