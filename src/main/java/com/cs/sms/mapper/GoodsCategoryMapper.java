package com.cs.sms.mapper;

import com.cs.sms.pojo.entity.GoodSCategory;
import com.cs.sms.pojo.entity.RoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsCategoryMapper {

/*    int deleteByCategoryId(Long categoryId);*/

    int insert(Long goodsId,Long categoryId);

/*    List<GoodSCategory> selectByRoleId(Long categoryId);*/
}
