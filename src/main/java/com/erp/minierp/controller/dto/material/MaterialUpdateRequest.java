package com.erp.minierp.controller.dto.material;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MaterialUpdateRequest extends MaterialBaseRequest {


    /**
     * 产品 ID（修改时必填）
     */
    @NotNull(message = "修改时必须指定产品 id")
    private Long id;
}