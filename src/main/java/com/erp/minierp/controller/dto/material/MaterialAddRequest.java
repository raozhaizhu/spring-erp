package com.erp.minierp.controller.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MaterialAddRequest {

    /**
     * 产品类型 ID
     */
    @NotNull(message = "产品类型不能为空")
    private Long categoryId;

    /**
     * 租户 ID
     */
    @NotNull(message = "租户ID不能为空")
    private Long tenantId;

    /**
     * 产品名称
     */
    @NotBlank(message = "产品名称不能为空")
    @Size(max = 100, message = "产品名称长度不能超过100个字符")
    private String name;


    /**
     * 品牌
     */
    @Size(max = 100, message = "品牌长度不能超过100个字符")
    private String brand;

    /**
     * 制造商
     */
    @Size(max = 50, message = "制造商长度不能超过50个字符")
    private String mfrs;

    /**
     * 型号
     */
    @Size(max = 100, message = "型号长度不能超过100个字符")
    private String model;

    /**
     * 规格
     */
    @Size(max = 100, message = "规格长度不能超过100个字符")
    private String standard;

    /**
     * 助记码（用于快速检索）
     */
    @Size(max = 100, message = "助记码长度不能超过100个字符")
    private String mnemonic;

    /**
     * 颜色
     */
    @Size(max = 50, message = "颜色长度不能超过50个字符")
    private String color;

    /**
     * 单位 ID（关联计量单位表）
     */
    @NotNull(message = "计量单位不能为空")
    private Long unitId;

    /**
     * 基础重量 (kg)
     */
    @DecimalMin(value = "0.000000", message = "基础重量不能小于0")
    private BigDecimal weight = new BigDecimal("0.000000");

    /**
     * 保质期天数
     */
    @Min(value = 0, message = "保质期天数不能小于0")
    private Integer expiryNum;

    /**
     * 默认仓位货架
     */
    @Size(max = 100, message = "默认仓位货架长度不能超过100个字符")
    private String position;

    /**
     * 启用状态：0-禁用，1-启用
     */
    private Boolean enabled = true;

    /**
     * 是否开启序列号管理：0-否，1-是
     */
    private Boolean enableSerialNumber = false;

    /**
     * 是否开启批号管理：0-否，1-是
     */
    private Boolean enableBatchNumber = false;

    /**
     * 备注（严格对齐数据库 varchar(255)）
     */
    @Size(max = 255, message = "备注长度不能超过255个字符")
    private String remark;

    /**
     * 动态扩展属性（接收前端传来的 JSON 字符串或对象映射）
     */
    private String extraAttributes;
}