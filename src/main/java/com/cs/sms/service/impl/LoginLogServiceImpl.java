package com.cs.sms.service.impl;



import com.cs.sms.repo.IGoodsRepository;
import com.cs.sms.repo.ILoginLogRepository;
import com.cs.sms.service.ILoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class LoginLogServiceImpl implements ILoginLogService {
    @Autowired
    private ILoginLogRepository loginLogRepository;
    public LoginLogServiceImpl() {
        log.debug("创建业务逻辑对象：LoginLogServiceImpl");
    }

}
