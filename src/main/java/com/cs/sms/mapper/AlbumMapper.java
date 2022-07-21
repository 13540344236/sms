package com.cs.sms.mapper;

import com.cs.sms.pojo.entity.Album;

public interface AlbumMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Album record);

    int insertSelective(Album record);

    Album selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Album record);

    int updateByPrimaryKey(Album record);
}