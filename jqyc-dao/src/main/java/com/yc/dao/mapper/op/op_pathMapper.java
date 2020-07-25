package com.yc.dao.mapper.op;

import com.yc.dao.entity.op.op_path;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-21 16:34
 **/
@Mapper
@Repository
public interface op_pathMapper {
    List<op_path> getChosePath(@Param("lineCode") Integer lineCode,
                               @Param("batchId") String batchId);
    void addList(@Param("list") List<op_path> list);
    void delete(Integer lineCode);
}
