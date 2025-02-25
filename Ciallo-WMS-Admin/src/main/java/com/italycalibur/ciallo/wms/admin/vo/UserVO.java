package com.italycalibur.ciallo.wms.admin.vo;

import com.italycalibur.ciallo.wms.core.models.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: 用户表VO
 * @author dhr
 * @date 2025-02-25 08:37:05
 * @version 1.0
 */
@Getter
@Setter
@Schema(description = "用户表VO")
public class UserVO extends User {
    @Schema(description = "所属部门名称")
    private String deptName;
    @Schema(description = "所属公司名称")
    private String companyName;
}
