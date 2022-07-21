package com.cs.sms.repo.impl;





import com.cs.sms.repo.IPictureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class PictureRepositoryImpl implements IPictureRepository {

    public PictureRepositoryImpl() {
        log.debug("创建数据访问对象：PictureRepositoryImpl");
    }

}
