package com.yc.dao.mapper.pub;

import com.yc.dao.entity.pub.pub_channel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-15 10:58
 **/
@Mapper
@Repository
public interface pub_channelMapper {
    List<pub_channel> findAll();
    void addList(@Param("list") List<pub_channel> list);
}
