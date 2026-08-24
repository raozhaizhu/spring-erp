package com.erp.minierp.common;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DocumentStatusEnum {
    DRAFT(0, "草稿"),
    AUDITED(1, "已审核"),
    REJECTED(2,"已驳回");


    @EnumValue // MyBatis: 存入数据库时自动使用该字段值
    private final Integer code;
    private final String desc;
}
