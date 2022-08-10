package com.cs.sms.repo.impl;


import com.cs.sms.pojo.vo.GoodsListVO;
import com.cs.sms.repo.IGoodsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class GoodsRepositoryImpl implements IGoodsRepository {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public void put(GoodsListVO goodsListVO) {
        String key=KEY_PREFIX_GOOD_ITEM+goodsListVO.getId();
        redisTemplate.opsForValue().set(key,goodsListVO);
    }

    @Override
    public GoodsListVO get(Long id) {
        GoodsListVO good=null;
        String key=KEY_PREFIX_GOOD_ITEM+id;
        Serializable serializable = redisTemplate.opsForValue().get(key);
        if (serializable!=null){
            if (serializable instanceof Serializable){
                good= (GoodsListVO) serializable;
            }
        }
        return good;
    }

    @Override
    public void deleteItem(Long id) {
        String key=KEY_PREFIX_GOOD_ITEM+id;
        redisTemplate.delete(key);
    }

    @Override
    public void putList(List<GoodsListVO> list) {
        for (GoodsListVO goodsListVO : list) {
            redisTemplate.opsForList().rightPush(KEY_GOOD_LIST, goodsListVO);
        }
    }

    @Override
    public List<GoodsListVO> getList() {
        List<Serializable> list = redisTemplate.opsForList().range(KEY_GOOD_LIST, 0, -1);
        List<GoodsListVO> good = new ArrayList<>();
        for (Serializable serializable : list) {
            good.add((GoodsListVO) serializable);
        }
        return good;
    }

    @Override
    public void deleteList() {
        redisTemplate.delete(KEY_GOOD_LIST);
    }
}
