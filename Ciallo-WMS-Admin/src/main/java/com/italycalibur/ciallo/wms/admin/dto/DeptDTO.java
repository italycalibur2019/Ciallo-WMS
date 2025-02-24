package com.italycalibur.ciallo.wms.admin.dto;

import com.italycalibur.ciallo.wms.core.models.entity.Dept;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dhr
 * @version 1.0
 * @date 2025-02-24 21:36:06
 * @description: 部门DTO
 */
@Getter
@Setter
@Schema(name = "DeptDTO", description = "部门DTO")
public class DeptDTO extends Dept {
}
