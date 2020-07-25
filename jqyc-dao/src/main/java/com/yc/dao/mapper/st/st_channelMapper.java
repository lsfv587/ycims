package com.yc.dao.mapper.st;

import com.yc.dao.entity.st.st_channel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-23 16:51
 **/
@Mapper
@Repository
public interface st_channelMapper {
    List<st_channel> findStChannel(Integer lineCode);
    void addList(@Param("list") List<st_channel> list);
    void delete(String batchId);
}
