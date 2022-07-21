package com.cs.sms.ex.handler;


import com.cs.sms.ex.ServiceException;
import com.cs.sms.web.JsonResult;
import com.cs.sms.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.StringJoiner;

/**
 * 统一处理异常
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    public GlobalExceptionHandler() {
        log.debug("创建统一处理异常的对象：GlobalExceptionHandler");
    }

    @ExceptionHandler
    public JsonResult handleServiceException(ServiceException e) {
        log.error("统一处理ServiceException，将向客户端响应：{}", e.getMessage());
        return JsonResult.fail(e);
    }

    @ExceptionHandler
    public JsonResult handleBindException(BindException e) {
        log.error("统一处理BindException，将向客户端响应：{}", e.getMessage());
        // String message = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringJoiner stringJoiner = new StringJoiner("；", "", "。");
        for (FieldError fieldError : fieldErrors) {
            stringJoiner.add(fieldError.getDefaultMessage());
        }
        String message = stringJoiner.toString();
        return JsonResult.fail(ServiceCode.ERR_BAD_REQUEST, message);
    }

    @ExceptionHandler
    public JsonResult handleThrowable(Throwable e) {
        log.error("统一处理未明确处理的异常【{}】，将向客户端响应：{}", e.getClass().getName(), e.getMessage());
        String message = "服务器忙，请联系管理员！";
        return JsonResult.fail(ServiceCode.ERR_UNKNOWN, message);
    }

}
