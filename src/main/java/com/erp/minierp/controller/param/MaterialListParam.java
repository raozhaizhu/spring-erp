package com.erp.minierp.controller.param;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MaterialQueryParam  extends BasePageParam{
    private Long categoryId; // 按分类筛选
    private String name;     // 按商品名称模糊搜索
}