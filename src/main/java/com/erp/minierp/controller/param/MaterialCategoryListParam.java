package com.erp.minierp.controller.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MaterialCategoryListParam extends BasePageParam {

    private Long tenantId;

    private Long parentId;

    private String name;
}
