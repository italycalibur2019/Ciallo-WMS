package com.italycalibur.ciallo.wms.admin.vo;

import com.italycalibur.ciallo.wms.core.models.entity.Permission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: 权限表VO
 * @author dhr
 * @date 2025-02-25 08:36:39
 * @version 1.0
 */
@Getter
@Setter
@Schema(description = "权限表VO")
public class PermissionVO extends Permission {
    @Schema(description = "父级菜单名称")
    private String parentMenuName;

    @Schema(description = "菜单类型描述")
    private String menuTypeDesc;
}
