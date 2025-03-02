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
 * <h1>商品信息表</h1>
 * </p>
 *
 * @author italycalibur
 * @since 2025-03-02 20:22:42
 */
@Getter
@Setter
@ToString(callSuper = true)
@TableName("basic.td_product")
@Schema(name = "Product", description = "商品信息表")
public class Product extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品编号
     */
    @TableField("product_code")
    @Schema(description = "商品编号")
    private String productCode;

    /**
     * 商品名称
     */
    @TableField("product_name")
    @Schema(description = "商品名称")
    private String productName;

    /**
     * 商品规格信息
     */
    @TableField("specification")
    @Schema(description = "商品规格信息")
    private String specification;

    /**
     * 商品种类
     */
    @TableField("category")
    @Schema(description = "商品种类")
    private String category;

    /**
     * 商品包装单位
     */
    @TableField("unit")
    @Schema(description = "商品包装单位")
    private String unit;

    /**
     * 商品重量
     */
    @TableField("weight")
    @Schema(description = "商品重量")
    private BigDecimal weight;

    /**
     * 商品体积
     */
    @TableField("volume")
    @Schema(description = "商品体积")
    private BigDecimal volume;

    /**
     * 安全库存数量
     */
    @TableField("safety_stock")
    @Schema(description = "安全库存数量")
    private Integer safetyStock;
}
