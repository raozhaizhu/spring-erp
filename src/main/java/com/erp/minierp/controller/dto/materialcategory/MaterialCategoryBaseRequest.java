package com.erp.minierp.controller.dto.materialcategory;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MaterialCategoryBaseRequest {

    @NotNull(message = "租户ID不能为空")
    private Long tenantId;

    @NotBlank(message = "分类名称不能为空")
    @Size(max = 50, message = "分类名称长度不能超过50个字符")
    private String name;

    @NotBlank(message = "分类编号不能为空")
    @Size(max = 64, message = "分类编号长度不能超过64个字符")
    private String serialNo;

    @NotNull(message = "排序值不能为空")
    @Min(value = 0, message = "排序值不能小于0")
    private Integer sort = 0;

    @Size(max = 255, message = "备注长度不能超过255个字符")
    private String remark;
}
