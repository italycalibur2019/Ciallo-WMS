package com.italycalibur.ciallo.wms.admin.dto;

import com.italycalibur.ciallo.wms.core.annotation.EnumValid;
import com.italycalibur.ciallo.wms.core.enums.MenuTypeEnum;
import com.italycalibur.ciallo.wms.core.models.entity.Permission;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dhr
 * @version 1.0
 * @date 2025-02-24 21:36:41
 * @description: 权限DTO
 */
@Getter
@Setter
@Schema(name = "PermissionDTO", description = "权限DTO")
public class PermissionDTO extends Permission {
    /**
     * 用来接收前端传递过来的菜单类型字符串
     */
    @NotNull(message = "菜单类型不能为空")
    @EnumValid(enumClass = MenuTypeEnum.class, message = "无效的菜单类型")
    @Schema(description = "菜单类型")
    private String menuTypeStr;
}
