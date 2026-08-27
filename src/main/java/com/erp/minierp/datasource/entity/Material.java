package com.erp.minierp.datasource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 产品表
 * </p>
 *
 * @author raozhaizhu
 * @since 2026-08-27
 */
@Getter
@Setter
@TableName("material")
public class Material implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户 ID
     */
    @TableField("tenant_id")
    private Long tenantId;

    /**
     * 产品类型 ID
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 产品名称
     */
    @TableField("name")
    private String name;

    /**
     * 品牌
     */
    @TableField("brand")
    private String brand;

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
     * 助记码（用于快速检索）
     */
    @TableField("mnemonic")
    private String mnemonic;

    /**
     * 颜色
     */
    @TableField("color")
    private String color;

    /**
     * 单位 ID（关联计量单位表）
     */
    @TableField("unit_id")
    private Long unitId;

    /**
     * 基础重量 (kg)
     */
    @TableField("weight")
    private BigDecimal weight;

    /**
     * 保质期天数
     */
    @TableField("expiry_num")
    private Integer expiryNum;

    /**
     * 默认仓位货架
     */
    @TableField("position")
    private String position;

    /**
     * 启用状态：0-禁用，1-启用
     */
    @TableField("enabled")
    private Byte enabled;

    /**
     * 是否开启序列号管理：0-否，1-是
     */
    @TableField("enable_serial_number")
    private Byte enableSerialNumber;

    /**
     * 是否开启批号管理：0-否，1-是
     */
    @TableField("enable_batch_number")
    private Byte enableBatchNumber;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 动态扩展属性
     */
    @TableField("extra_attributes")
    private String extraAttributes;

    /**
     * 逻辑删除标记：0-未删除，1-已删除
     */
    @TableField("delete_flag")
    private Byte deleteFlag;

    /**
     * 创建人 ID
     */
    @TableField("create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新人 ID
     */
    @TableField("update_by")
    private Long updateBy;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;
}
