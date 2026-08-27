package com.erp.minierp.controller.dto.depot;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DepotUpdateRequest extends DepotBaseRequest {

    /**
     * 仓库 ID（修改时必填）
     */
    @NotNull(message = "修改时必须指定仓库 id")
    private Long id;

}