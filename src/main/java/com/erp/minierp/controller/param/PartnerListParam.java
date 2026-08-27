package com.erp.minierp.controller.param;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PartnerListParam extends BasePageParam {

    private String name;

    /** 单位类型：1-供应商，2-客户，3-既是客户又是供应商。 */
    @Min(value = 1, message = "单位类型必须为1、2或3")
    @Max(value = 3, message = "单位类型必须为1、2或3")
    private Byte type;

    private Boolean enabled;
}
