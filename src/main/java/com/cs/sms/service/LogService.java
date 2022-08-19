package com.cs.sms.service;


import com.cs.sms.pojo.entity.Log;
import com.cs.sms.pojo.vo.UserVO;

import java.util.List;

/**
 * 日志业务接口
 */
public interface LogService {
    void insert(Log log);
    /**
     * 查询所有日志信息
     * @return
     */
    List<Log> list();
}
