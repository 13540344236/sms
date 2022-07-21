package com.cs.sms.ex;

/**
 * 业务异常
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
public class ServiceException extends RuntimeException {

    private Integer serviceCode;

    public ServiceException(Integer serviceCode, String message) {
        super(message);
        this.serviceCode = serviceCode;
    }

    public Integer getServiceCode() {
        return serviceCode;
    }

}
