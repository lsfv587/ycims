package com.yc.dao.mapper.op;

import com.yc.dao.entity.op.op_channel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-16 11:06
 **/
@Mapper
@Repository
public interface op_channelMapper {
    List<op_channel> getChannels(Integer lineCode);
    List<op_channel> getOpChannels(Integer lineCode);
    void addList(@Param("list") List<op_channel> list);
    void delete(Integer lineCode);
}
