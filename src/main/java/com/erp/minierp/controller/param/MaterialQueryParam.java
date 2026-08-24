package com.erp.minierp.controller.param;

import lombok.Data;

@Data
public class MaterialQueryParam {
    private Integer pageNum = 1;
    private Integer pageSize = 10;

    private Long categoryId; // 按分类筛选
    private String name;     // 按商品名称模糊搜索
}