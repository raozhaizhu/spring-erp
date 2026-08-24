package com.erp.minierp.constants;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnableStatusEnum {
    DISABLE("0", "否"),
    ENABLE("1", "是");

    @EnumValue
    private final String code;
    private final String desc;
}