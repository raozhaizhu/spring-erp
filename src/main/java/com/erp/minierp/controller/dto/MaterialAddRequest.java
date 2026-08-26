package com.erp.minierp.controller.dto;


import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class MaterialAddRequest {

    /**
     * 产品类型ID
     */
    @NotNull(message = "商品分类不能为空")
    private Long categoryId;

    /**
     * 名称
     */
    @NotBlank(message = "商品名称不能为空")
    @Size(max = 100, message = "商品名称长度不能超过100个字符")
    private String name;

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
     * 品牌
     */
    @Size(max = 100, message = "品牌长度不能超过100个字符")
    private String brand;

    /**
     * 助记码
     */
    @Size(max = 100, message = "助记码长度不能超过100个字符")
    private String mnemonic;

    /**
     * 颜色
     */
    @Size(max = 50, message = "颜色长度不能超过50个字符")
    private String color;

    /**
     * 单位-单个
     */
    @Size(max = 50, message = "单位长度不能超过50个字符")
    private String unit;

    /**
     * 备注
     */
    @Size(max = 500, message = "备注长度不能超过500个字符")
    private String remark;

    /**
     * 图片名称
     */
    @Size(max = 1000, message = "图片名称长度不能超过1000个字符")
    private String imgName;

    /**
     * 单位Id
     */
    private Long unitId;

    /**
     * 保质期天数
     */
    @Min(value = 0, message = "保质期天数不能小于0")
    private Integer expiryNum;

    /**
     * 基础重量(kg)
     */
    @DecimalMin(value = "0.00", message = "基础重量不能小于0")
    private BigDecimal weight;

    /**
     * 启用 0-禁用 1-启用
     */
    private Boolean enabled;

    /**
     * 自定义1
     */
    @Size(max = 500, message = "自定义字段1长度不能超过500个字符")
    private String otherField1;

    /**
     * 自定义2
     */
    @Size(max = 500, message = "自定义字段2长度不能超过500个字符")
    private String otherField2;

    /**
     * 自定义3
     */
    @Size(max = 500, message = "自定义字段3长度不能超过500个字符")
    private String otherField3;

    /**
     * 是否开启序列号，0否，1是
     */
    @Pattern(regexp = "[01]", message = "是否开启序列号只能为0或1")
    private String enableSerialNumber = "0"; // 对应数据库默认值

    /**
     * 是否开启批号，0否，1是
     */
    @Pattern(regexp = "[01]", message = "是否开启批号只能为0或1")
    private String enableBatchNumber = "0"; // 对应数据库默认值

    /**
     * 仓位货架
     */
    @Size(max = 100, message = "仓位货架长度不能超过100个字符")
    private String position;

    /**
     * 多属性信息
     */
    @Size(max = 1000, message = "多属性信息长度不能超过1000个字符")
    private String attribute;
}