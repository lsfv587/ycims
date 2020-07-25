package com.yc.dao.mapper.pub;

import com.yc.dao.entity.pub.pub_batch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-21 15:14
 **/
@Mapper
@Repository
public interface pub_batchMapper {
    pub_batch getMinBatch(Integer type,Integer batchSequence);
    void addList(@Param("list") List<pub_batch> list);
}
