package com.cs.sms.easy.dao;

import com.cs.sms.pojo.vo.GoodsListVO;

import java.util.List;

public class GoodsListVODAO {
    public void save(List<GoodsListVO> list) {
        // 如果是mybatis,尽量别直接调用多次insert,自己写一个mapper里面新增一个方法batchInsert,所有数据一次性插入
    }
}
