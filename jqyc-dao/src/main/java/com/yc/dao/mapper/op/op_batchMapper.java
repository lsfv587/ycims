package com.yc.dao.mapper.op;

import com.yc.dao.entity.op.op_batch;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-21 14:33
 **/
@Mapper
@Repository
public interface op_batchMapper {
    op_batch getMaxBatchNo(String orderDate,Integer lineCode);
    op_batch getBatchByLineCode(Integer lineCode);
    void add(op_batch batch);
    void delete(Integer lineCode);
}
