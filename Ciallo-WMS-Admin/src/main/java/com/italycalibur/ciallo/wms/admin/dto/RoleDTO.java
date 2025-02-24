package com.italycalibur.ciallo.wms.admin.dto;

import com.italycalibur.ciallo.wms.core.models.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dhr
 * @version 1.0
 * @date 2025-02-24 21:36:30
 * @description: 角色DTO
 */
@Getter
@Setter
@Schema(description = "角色DTO")
public class RoleDTO extends Role {
}
