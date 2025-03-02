package com.italycalibur.ciallo.wms.core.models.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * <h1>库位表</h1>
 * </p>
 *
 * @author italycalibur
 * @since 2025-03-02 20:22:42
 */
@Getter
@Setter
@ToString(callSuper = true)
@TableName("basic.td_storage_location")
@Schema(name = "StorageLocation", description = "库位表")
public class StorageLocation extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 库房主键
     */
    @TableField("warehouse_id")
    @Schema(description = "库房主键")
    private Long warehouseId;

    /**
     * 库区类型
     */
    @TableField("zone_type")
    @Schema(description = "库区类型")
    private String zoneType;

    /**
     * 库位编号
     */
    @TableId("location_code")
    @Schema(description = "库位编号")
    private String locationCode;

    /**
     * 库位状态，如空闲/占用/锁定
     */
    @TableField("status")
    @Schema(description = "库位状态，如空闲/占用/锁定")
    private String status;

    /**
     * 最大存储体积
     */
    @TableField("max_volume")
    @Schema(description = "最大存储体积")
    private BigDecimal maxVolume;

    /**
     * 最大承重
     */
    @TableField("max_weight")
    @Schema(description = "最大承重")
    private BigDecimal maxWeight;
}
