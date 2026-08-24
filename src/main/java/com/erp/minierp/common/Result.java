package com.erp.minierp.common;


import lombok.Data;
import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 状态码：0-成功，其他数字代表特定业务错误或警告 */
    private Integer code;

    /** 提示信息 */
    private String msg;

    /** 泛型数据对象 */
    private T data;

    public static <T> Result<T> success() {
        return success("操作成功", null);
    }

    public static <T> Result<T> success(T data) {
        return success("操作成功", data);
    }

    public static <T> Result<T> success(String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(0); // 0 代表成功
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(String msg) {
        return error(500, msg);
    }

    public static <T> Result<T> error(Integer code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}