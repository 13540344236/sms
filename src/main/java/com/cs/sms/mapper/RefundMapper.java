package com.cs.sms.mapper;

import com.cs.sms.pojo.entity.Refund;
import com.cs.sms.pojo.vo.RefundDetailVO;
import com.cs.sms.pojo.vo.RefundListItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefundMapper {
    /**
     * 插入商品列表数据
     * @param refund 商品列表数据
     * @return 受影响的行数，成功插入数据时，将返回1
     */

    int insert(Refund refund);
    /**
     * 根据商品ID删除商品列表数据
     * @param id 商品编号(ID)
     * @return 受影响的行数，成功删除数据时，将返回1
     */
    int deleteById(Long id);


    int updateById(Refund refund);

    int updateNameById(Long id, String name);

    int count();
    
    int countByName(String name);

    RefundDetailVO getById(Long id);

    RefundDetailVO getByName(String name);

    List<RefundListItemVO> list();

    List<RefundListItemVO> listPage(Integer offset, Integer count);

    int deleteByPrimaryKey(Long id);

    int countById(Long id);



    RefundDetailVO selectById(Long id);
}
