package com.italycalibur.ciallo.wms.admin.vo;

import com.italycalibur.ciallo.wms.core.models.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: 角色表VO
 * @author dhr
 * @date 2025-02-25 08:36:58
 * @version 1.0
 */
@Getter
@Setter
@Schema(description = "角色表VO")
public class RoleVO extends Role {
}
