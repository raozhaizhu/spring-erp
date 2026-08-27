package com.erp.minierp.controller.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MaterialListParam extends BasePageParam {
    private Long categoryId; // 按分类筛选
    private String name;     // 按商品名称模糊搜索
}