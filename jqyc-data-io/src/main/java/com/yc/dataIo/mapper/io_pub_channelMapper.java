package com.yc.dataIo.mapper;

import com.yc.dataIo.entity.io_pub_channel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-15 12:20
 **/
@Mapper
@Repository
public interface io_pub_channelMapper {

    List<io_pub_channel> findAll();
}
