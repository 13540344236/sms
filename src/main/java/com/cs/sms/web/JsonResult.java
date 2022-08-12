package com.cs.sms.web;

import com.cs.sms.ex.ServiceException;
import lombok.Data;

import java.io.Serializable;

/**
 * 用于封装服务器端向客户端响应结果的类型
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Data
public final class JsonResult<T> implements Serializable {

    /**
     * 业务状态码
     */
    private Integer code;
    /**
     * 错误时的消息
     */
    private String message;
    /**
     * 处理成功时，需要响应到客户端的数据
     */
    private Object data;

    public static JsonResult<Void> ok() {
        return ok(null);
    }

    public static JsonResult ok(Object data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.code = ServiceCode.OK;
        jsonResult.data = data;
        return jsonResult;
    }


    public static <T> JsonResult<T> ok(String message, T data) {
        JsonResult<T> jsonResult = new JsonResult<>();
        jsonResult.setCode(ServiceCode.OK);
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult<Void> fail(ServiceException e) {
        return fail(e.getServiceCode(), e.getMessage());
    }

    public static JsonResult<Void> fail(Integer code, String message) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        jsonResult.code = code;
        jsonResult.message = message;
        return jsonResult;
    }
}
