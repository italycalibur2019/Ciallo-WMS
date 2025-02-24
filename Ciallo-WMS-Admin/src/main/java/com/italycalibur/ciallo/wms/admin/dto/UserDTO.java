package com.italycalibur.ciallo.wms.admin.dto;

import com.italycalibur.ciallo.wms.core.models.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dhr
 * @version 1.0
 * @date 2025-02-24 21:35:54
 * @description: 用户DTO
 */
@Getter
@Setter
@Schema(name = "UserDTO", description = "用户DTO")
public class UserDTO extends User {

    /**
     * 接收前端原始密码
     */
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+])[A-Za-z\\d!@#$%^&*()_+]{8,16}$",
            message = "密码需8-16位，包含大小写字母、数字和特殊符号")
    @Schema(description = "接收前端原始密码")
    private String rawPassword;
}
