package com.cs.sms.service.impl;



import com.cs.sms.repo.ILoginLogRepository;
import com.cs.sms.repo.IPictureRepository;
import com.cs.sms.service.IPictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class PictureServiceImpl implements IPictureService {
    @Autowired
    private IPictureRepository pictureRepository;
    public PictureServiceImpl() {
        log.debug("创建业务逻辑对象：PictureServiceImpl");
    }

}
