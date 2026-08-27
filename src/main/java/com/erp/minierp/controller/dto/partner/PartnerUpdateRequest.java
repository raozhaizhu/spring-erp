package com.erp.minierp.controller.dto.partner;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PartnerUpdateRequest extends PartnerBaseRequest {

    @NotNull(message = "修改时必须指定往来单位id")
    private Long id;
}
