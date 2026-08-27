package com.erp.minierp.controller.dto.partner;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PartnerBaseRequest {

    @NotBlank(message = "单位名称不能为空")
    @Size(max = 150, message = "单位名称长度不能超过150个字符")
    private String name;

    @NotNull(message = "单位类型不能为空")
    @Min(value = 1, message = "单位类型必须为1、2或3")
    @Max(value = 3, message = "单位类型必须为1、2或3")
    private Byte type;

    @Size(max = 50, message = "联系人长度不能超过50个字符")
    private String contactPerson;

    @Size(max = 30, message = "联系电话长度不能超过30个字符")
    private String contactPhone;

    @Size(max = 50, message = "电子邮箱长度不能超过50个字符")
    private String email;

    @Size(max = 30, message = "传真长度不能超过30个字符")
    private String fax;

    @Size(max = 200, message = "详细地址长度不能超过200个字符")
    private String address;

    @Size(max = 50, message = "纳税人识别号长度不能超过50个字符")
    private String taxNum;

    @Size(max = 100, message = "开户行长度不能超过100个字符")
    private String bankName;

    @Size(max = 50, message = "银行账号长度不能超过50个字符")
    private String accountNumber;

    @DecimalMin(value = "0.00", message = "默认税率不能小于0")
    @DecimalMax(value = "100.00", message = "默认税率不能大于100")
    private BigDecimal taxRate = new BigDecimal("0.00");

    @DecimalMin(value = "0.0000", message = "期初预收款不能小于0")
    private BigDecimal advanceIn = new BigDecimal("0.0000");

    @DecimalMin(value = "0.0000", message = "期初应收不能小于0")
    private BigDecimal beginNeedGet = new BigDecimal("0.0000");

    @DecimalMin(value = "0.0000", message = "期初应付不能小于0")
    private BigDecimal beginNeedPay = new BigDecimal("0.0000");

    @Min(value = 0, message = "排序值不能小于0")
    private Integer sort = 0;

    private Boolean enabled = true;

    @Size(max = 500, message = "备注长度不能超过500个字符")
    private String remark;

    private Long tenantId;
}
