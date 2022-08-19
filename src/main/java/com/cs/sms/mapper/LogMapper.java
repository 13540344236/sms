package com.cs.sms.mapper;


import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.entity.Log;
import com.cs.sms.web.JsonPage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogMapper {
    int insert(Log log);
    List<Log> list();

    List<Log> getAllLog();
}
