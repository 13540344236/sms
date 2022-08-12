package com.cs.sms.repo;

import com.cs.sms.pojo.vo.RefundListItemVO;

import java.util.List;

public interface IRefundRepository {
    String KEY_BRAND_LIST = "purchase:list";
    List<RefundListItemVO> getList();
}


