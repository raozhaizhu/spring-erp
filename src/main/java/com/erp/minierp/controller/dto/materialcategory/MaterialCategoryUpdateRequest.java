package com.erp.minierp.controller.dto.materialcategory;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MaterialCategoryUpdateRequest extends MaterialCategoryBaseRequest {

    @NotNull(message = "修改时必须指定产品分类 id")
    private Long id;
}
