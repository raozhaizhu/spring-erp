package com.erp.minierp.controller.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class DepotAddRequest {

    /**
     * 父仓库ID（0表示顶级仓库）
     */
    private Long parentId = 0L;

    /**
     * 仓库名称
     */
    @NotBlank(message = "仓库名称不能为空")
    @Size(max = 50, message = "仓库名称长度不能超过50个字符")
    private String name;

    /**
     * 仓库地址
     */
    @Size(max = 100, message = "仓库地址长度不能超过100个字符")
    private String address;

    /**
     * 仓储费
     */
    @DecimalMin(value = "0.00", message = "仓储费不能小于0")
    private BigDecimal warehousing;

    /**
     * 搬运费
     */
    @DecimalMin(value = "0.00", message = "搬运费不能小于0")
    private BigDecimal truckage;

    /**
     * 类型
     */
    @NotNull(message = "仓库类型不能为空")
    private Integer type;

    /**
     * 排序
     */
    @Size(max = 10, message = "排序字段长度不能超过10个字符")
    private String sort;

    /**
     * 描述
     */
    @Size(max = 100, message = "描述长度不能超过100个字符")
    private String remark;

    /**
     * 负责人ID
     */
    private Long principal;

    /**
     * 启用状态
     */
    private Boolean enabled;

    /**
     * 是否默认
     */
    private Boolean isDefault;
}