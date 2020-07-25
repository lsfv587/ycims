package com.yc.dao.mapper.pub;

import com.yc.dao.entity.pub.pub_channelBind;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-16 15:18
 **/
@Mapper
@Repository
public interface pub_channelBindMapper {
    List<pub_channelBind> getChannelBinds(Integer lineCode);
    void addList(@Param("list")List<pub_channelBind> list);
}
