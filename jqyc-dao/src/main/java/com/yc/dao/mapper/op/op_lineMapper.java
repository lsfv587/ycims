package com.yc.dao.mapper.op;

import com.yc.dao.entity.op.op_line;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-16 10:59
 **/
@Mapper
@Repository
public interface op_lineMapper {
    op_line getLine(Integer lineCode);
}
