package com.erp.minierp.controller.dto.depot;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepotBaseRequest {

    /**
     * 租户 ID
     */
    @NotNull(message = "租户ID不能为空")
    private Long tenantId;

    /**
     * 仓库编码（采用语义化规范，如 WH-WZ-CENTRAL）
     */
    @NotBlank(message = "仓库编码不能为空")
    @Size(max = 64, message = "仓库编码长度不能超过64个字符")
    @Pattern(regexp = "^[A-Z0-9-]+$", message = "仓库编码只能包含大写字母、数字和中划线")
    private String code;

    /**
     * 仓库名称
     */
    @NotBlank(message = "仓库名称不能为空")
    @Size(max = 50, message = "仓库名称长度不能超过50个字符")
    private String name;

    /**
     * 仓库地址
     */
    @Size(max = 200, message = "仓库地址长度不能超过200个字符")
    private String address;

    /**
     * 仓储费率/单价
     */
    @DecimalMin(value = "0.00", message = "仓储费不能小于0")
    private BigDecimal warehousing = new BigDecimal("0.0000");

    /**
     * 搬运费率/单价
     */
    @DecimalMin(value = "0.00", message = "搬运费不能小于0")
    private BigDecimal truckage = new BigDecimal("0.0000");

    /**
     * 仓库类型：1-普通仓，2-冷藏仓，3-虚拟仓，4-中转仓
     */
    @NotNull(message = "仓库类型不能为空")
    private Integer type;

    /**
     * 显示顺序（数值越小越靠前）
     */
    @NotNull(message = "排序值不能为空")
    @Min(value = 0, message = "排序值不能小于0")
    private Integer sort = 0;

    /**
     * 描述/备注
     */
    @Size(max = 255, message = "描述长度不能超过255个字符")
    private String remark;

    /**
     * 负责人ID（关联用户表）
     */
    private Long principalId;

    /**
     * 启用状态：0-禁用，1-启用
     */
    private Boolean enabled = true;

    /**
     * 是否默认仓库：0-否，1-是
     */
    private Boolean isDefault = false;
}