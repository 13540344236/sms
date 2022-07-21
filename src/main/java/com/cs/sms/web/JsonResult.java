package com.cs.sms.web;

import com.cs.sms.ex.ServiceException;
import lombok.Data;

/**
 * 用于封装服务器端向客户端响应结果的类型
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Data
public final class JsonResult {

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

    public static JsonResult ok() {
        return ok(null);
    }

    public static JsonResult ok(Object data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.code = ServiceCode.OK;
        jsonResult.data = data;
        return jsonResult;
    }

    public static JsonResult fail(ServiceException e) {
        return fail(e.getServiceCode(), e.getMessage());
    }

    public static JsonResult fail(Integer code, String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.code = code;
        jsonResult.message = message;
        return jsonResult;
    }

}
