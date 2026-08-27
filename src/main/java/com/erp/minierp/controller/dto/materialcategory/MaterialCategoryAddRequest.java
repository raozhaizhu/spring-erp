package com.erp.minierp.controller.dto.materialcategory;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MaterialCategoryAddRequest extends MaterialCategoryBaseRequest {

    /** 父分类 ID，0 表示顶级分类。 */
    private Long parentId = 0L;
}
