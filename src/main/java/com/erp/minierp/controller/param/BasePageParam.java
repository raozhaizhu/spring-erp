package com.erp.minierp.controller.param;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class BasePageParam {

    @Min(value = 1, message = "页码最小为 1")
    private Integer pageNum = 1;

    @Range(min = 1, max = 100, message = "每页条数必须在 1 到 100 之间")
    private Integer pageSize = 10;
}