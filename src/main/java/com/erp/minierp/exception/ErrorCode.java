package com.erp.minierp.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    PARAM_ERROR(400, "请求参数错误"),
    MATERIAL_EXIST(40001, "材料名称已存在"),
    SYSTEM_ERROR(500, "系统内部错误");

    private final int code;
    private final String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
