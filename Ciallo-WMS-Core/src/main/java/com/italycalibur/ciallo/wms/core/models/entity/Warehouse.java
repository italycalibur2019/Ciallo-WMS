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
import java.math.BigDecimal;
/**
 * <p>
 * <h1>库房信息表</h1>
 * </p>
 *
 * @author italycalibur
 * @since 2025-03-02 20:22:42
 */
@Getter
@Setter
@ToString(callSuper = true)
@TableName("basic.td_warehouse")
@Schema(name = "Warehouse", description = "库房信息表")
public class Warehouse extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 库房名称
     */
    @Schema(description = "库房名称")
    @TableField("warehouse_name")
    private String warehouseName;

    /**
     * 库房地址
     */
    @TableField("address")
    @Schema(description = "库房地址")
    private String address;

    /**
     * 库管员主键
     */
    @TableField("manager")
    @Schema(description = "库管员主键")
    private Long manager;

    /**
     * 库房容量
     */
    @TableField("capacity")
    @Schema(description = "库房容量")
    private BigDecimal capacity;

    /**
     * 库房可用状态——0 不可用，1 可用
     */
    @TableField("status")
    @Schema(description = "库房可用状态——0 不可用，1 可用")
    private String status;
}
