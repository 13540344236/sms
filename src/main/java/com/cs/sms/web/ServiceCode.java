package com.cs.sms.web;

/**
 * 业务状态码
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
public final class ServiceCode {

    /**
     * 成功
     */
    public static final int OK = 20000;
    /**
     * 错误：数据格式有误
     */
    public static final int ERR_BAD_REQUEST = 40000;
    /**
     * 错误：JWT数据错误，可能被恶意篡改
     */
    public static final int ERR_JWT_INVALID = 40001;
    /**
     * 错误：JWT过期
     */
    public static final int ERR_JWT_EXPIRED = 40300;
    /**
     * 错误：数据不存在
     */
    public static final int ERR_NOT_FOUND = 40400;
    /**
     * 错误：冲突，通常是因为出现了重复的数据
     */
    public static final int ERR_CONFLICT = 40900;
    /**
     * 错误：插入数据失败
     */
    public static final int ERR_INSERT = 50000;
    /**
     * 错误：删除数据失败
     */
    public static final int ERR_DELETE = 50001;
    /**
     * 错误：更新数据失败
     */
    public static final int ERR_UPDATE = 50002;
    /**
     * 错误：未处理的异常
     */
    public static final int ERR_UNKNOWN = 59999;

}
