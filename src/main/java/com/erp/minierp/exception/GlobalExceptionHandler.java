package com.erp.minierp.exception;

import com.erp.minierp.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 1. 唯一索引冲突 (如：新增/修改了重复的商品名称、条码)
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Result<Void> handleDuplicateKeyException(DuplicateKeyException e) {
        return Result.error(400, "数据已被占用，请勿重复录入（如编码或名称重复）");
    }

    /**
     * 2. 数据库完整性约束破坏 (如：必填字段传了 null、字段长度超出数据库限定)
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result<Void> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.warn("数据约束校验失败: {}", e.getMessage());
        return Result.error(400, "数据格式不符合要求（必填项缺失或超长）");
    }

    /**
     * 3. 前端传参格式错误 (如：JSON 格式非法、ID 应该传数字却传了字符串)
     */
    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public Result<Void> handleHttpRequestException(Exception e) {
        return Result.error(400, "请求参数解析失败，请检查数据格式或类型是否正确");
    }

    /**
     * 4. DTO 实体校验失败 (如果实体类加了 @NotBlank, @NotNull 等注解)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleValidException(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldError() != null
                ? e.getBindingResult().getFieldError().getDefaultMessage()
                : "参数校验不通过";
        return Result.error(400, msg);
    }

    /**
     * 5. 终极兜底：处理所有未知的系统内部异常 (空指针、数据库连接中断等)
     * 策略：打印完整 Error 日志，返回 HTTP 500 触发运维监控
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Result<Void>> handleThrowable(Throwable e) {
        log.error("系统发生严重未捕获异常", e);
        Result<Void> result = Result.error(500, "服务器开小差了，请联系管理员");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }
}