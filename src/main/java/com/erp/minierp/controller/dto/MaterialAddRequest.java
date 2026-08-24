package com.erp.minierp.controller.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class MaterialAddRequest {

    private Long categoryId;

    @NotBlank(message = "商品名称不能为空")
    private String name;

    private String mfrs;
    private String model;
    private String standard;
    private String brand;
    private String mnemonic;
    private String color;
    private String unit;
    private String remark;
    private String imgName;
    private Long unitId;
    private Integer expiryNum;
    private BigDecimal weight;
    private Boolean enabled;
    private String otherField1;
    private String otherField2;
    private String otherField3;
    private String enableSerialNumber;
    private String enableBatchNumber;
    private String position;
    private String attribute;
}