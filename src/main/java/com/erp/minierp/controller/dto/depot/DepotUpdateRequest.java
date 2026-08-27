package com.erp.minierp.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DepotUpdateRequest  extends DepotAddRequest{

    @NotNull(message = "修改时必须指定仓库 id")
    private Long id;
}
