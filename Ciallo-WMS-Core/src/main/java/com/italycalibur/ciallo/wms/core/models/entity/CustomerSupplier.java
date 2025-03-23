package com.italycalibur.ciallo.wms.core.models.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.italycalibur.ciallo.wms.core.models.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
/**
 * <p>
 * <h1>客商信息表</h1>
 * </p>
 *
 * @author italycalibur
 * @since 2025-03-02 20:22:42
 */
@Getter
@Setter
@ToString(callSuper = true)
@TableName("basic.td_customer_supplier")
@Schema(name = "CustomerSupplier", description = "客商信息表")
public class CustomerSupplier extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 客商名称
     */
    @Schema(description = "客商名称")
    private String name;

    /**
     * 客商类型，客户/供应商
     */
    @TableField("type")
    @Schema(description = "客商类型，客户/供应商")
    private String type;
}
