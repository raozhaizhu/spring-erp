package com.erp.minierp.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MaterialUpdateRequest extends MaterialAddRequest {

    @NotNull(message = "修改时必须指定商品 ID")
    private Long id;
}