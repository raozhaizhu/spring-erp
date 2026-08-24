package com.erp.minierp.common;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeletedStatusEnum {
    DRAFT(0, "正常/未删除"),
    AUDITED(1, "已删除");


    @EnumValue // MyBatis: 存入数据库时自动使用该字段值
    private final Integer code;
    private final String desc;
}
