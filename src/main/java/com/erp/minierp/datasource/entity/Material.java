package com.erp.minierp.datasource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 产品表
 * </p>
 *
 * @author raozhaizhu
 * @since 2026-08-24
 */
@Getter
@Setter
@TableName("material")
public class Material implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 产品类型id
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 制造商
     */
    @TableField("mfrs")
    private String mfrs;

    /**
     * 型号
     */
    @TableField("model")
    private String model;

    /**
     * 规格
     */
    @TableField("standard")
    private String standard;

    /**
     * 品牌
     */
    @TableField("brand")
    private String brand;

    /**
     * 助记码
     */
    @TableField("mnemonic")
    private String mnemonic;

    /**
     * 颜色
     */
    @TableField("color")
    private String color;

    /**
     * 单位-单个
     */
    @TableField("unit")
    private String unit;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 图片名称
     */
    @TableField("img_name")
    private String imgName;

    /**
     * 单位Id
     */
    @TableField("unit_id")
    private Long unitId;

    /**
     * 保质期天数
     */
    @TableField("expiry_num")
    private Integer expiryNum;

    /**
     * 基础重量(kg)
     */
    @TableField("weight")
    private BigDecimal weight;

    /**
     * 启用 0-禁用 1-启用
     */
    @TableField("enabled")
    private Boolean enabled;

    /**
     * 自定义1
     */
    @TableField("other_field1")
    private String otherField1;

    /**
     * 自定义2
     */
    @TableField("other_field2")
    private String otherField2;

    /**
     * 自定义3
     */
    @TableField("other_field3")
    private String otherField3;

    /**
     * 是否开启序列号，0否，1是
     */
    @TableField("enable_serial_number")
    private String enableSerialNumber;

    /**
     * 是否开启批号，0否，1是
     */
    @TableField("enable_batch_number")
    private String enableBatchNumber;

    /**
     * 仓位货架
     */
    @TableField("position")
    private String position;

    /**
     * 多属性信息
     */
    @TableField("attribute")
    private String attribute;

    /**
     * 租户id
     */
    @TableField("tenant_id")
    private Long tenantId;

    /**
     * 删除标记，0未删除，1删除
     */
    @TableField("delete_flag")
    private String deleteFlag;
}
