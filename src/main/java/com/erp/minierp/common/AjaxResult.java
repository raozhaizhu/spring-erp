package com.erp.minierp.common;

import lombok.Data;

@Data
public class AjaxResult <T>{
    private int code;
    private String msg;
    private  T data;

    public static <T> AjaxResult<T> success(T data){
        AjaxResult<T> result = new AjaxResult<>();
        result.setCode(200);
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    public static <T> AjaxResult<T> error(int code, String msg){
        AjaxResult<T> result = new AjaxResult<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
