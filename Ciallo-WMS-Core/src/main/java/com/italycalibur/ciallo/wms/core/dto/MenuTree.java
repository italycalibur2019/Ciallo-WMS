package com.italycalibur.ciallo.wms.core.dto;

import com.italycalibur.ciallo.wms.core.models.entity.Permission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 权限菜单树形结构
 * @author dhr
 * @date 2025-02-24 13:37:26
 * @version 1.0
 */
@Getter
@Setter
@Schema(description = "权限菜单树形结构")
public class MenuTree extends Permission {
    @Schema(description = "子菜单/操作")
    private List<MenuTree> children = new ArrayList<>();
}
