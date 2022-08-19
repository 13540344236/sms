package com.cs.sms.service.impl;


import com.cs.sms.mapper.LogMapper;
import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.entity.Log;
import com.cs.sms.service.LogService;
import com.cs.sms.web.JsonPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    //@Async
    @Override
    public void insert(Log log) {
        logMapper.insert(log);
    }

    @Override
    public List<Log> list() {
        return logMapper.list();
    }

    public JsonPage<Log> getAllLogByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        log.debug("num = {},Size = {}",pageNum,pageSize);
        List<Log> list = logMapper.getAllLog();
        return JsonPage.restPage(new PageInfo<>(list));
    }
}
