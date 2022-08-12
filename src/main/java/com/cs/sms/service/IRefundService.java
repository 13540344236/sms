package com.cs.sms.service;

import com.cs.sms.pojo.dto.RefundAddNewDTO;
import com.cs.sms.pojo.dto.RefundEditDTO;
import com.cs.sms.pojo.vo.RefundListItemVO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IRefundService {

    //新增商品
    void addNew(RefundAddNewDTO refundAddNewDTO);


    void deleteByPrimaryKey(Long id);

    List<RefundListItemVO> list();

    void deleteById(Long id);

    void update(Long id, RefundEditDTO refundEditDTO);

    void createExcel(HttpServletResponse response) throws IOException;
}
