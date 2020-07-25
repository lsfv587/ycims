package com.yc.dataIo.mapper;

import com.yc.dataIo.entity.io_pub_batch;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-21 15:36
 **/
@Mapper
@Repository
public interface io_pub_batchMapper {
    List<io_pub_batch> findAll();
}
