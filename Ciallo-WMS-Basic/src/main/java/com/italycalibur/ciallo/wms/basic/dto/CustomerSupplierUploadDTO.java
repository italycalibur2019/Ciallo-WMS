package com.italycalibur.ciallo.wms.basic.dto;

import cn.idev.excel.annotation.ExcelProperty;
import com.italycalibur.ciallo.wms.core.common.BaseUploadDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @description: 客商上传DTO
 * @author dhr
 * @date 2025-03-07 14:06:24
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(name = "CustomerSupplierUploadDTO", description = "客商信息上传DTO")
public class CustomerSupplierUploadDTO extends BaseUploadDTO {
    /**
     * 客商名称
     */
    @ExcelProperty("客商名称")
    @Schema(description = "客商名称")
    private String name;

    /**
     * 客商类型，客户/供应商
     */
    @ExcelProperty("客商类型")
    @Schema(description = "客商类型，客户/供应商")
    private String type;
}
