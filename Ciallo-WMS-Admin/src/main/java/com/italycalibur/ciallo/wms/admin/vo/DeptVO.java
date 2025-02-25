package com.italycalibur.ciallo.wms.admin.vo;

import com.italycalibur.ciallo.wms.core.models.entity.Dept;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: 部门表VO
 * @author dhr
 * @date 2025-02-25 08:36:29
 * @version 1.0
 */
@Getter
@Setter
@Schema(description = "部门表VO")
public class DeptVO extends Dept {
    @Schema(description = "父级部门名称")
    private String parentCompanyName;
    
    @Schema(description = "部门类型描述")
    private String deptTypeDesc;
}
