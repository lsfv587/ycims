package com.yc.dao.mapper.op;

import com.yc.dao.entity.op.op_area;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface op_areaMapper {
    List<op_area> getChoseArea(@Param("lineCode") Integer lineCode,
                               @Param("batchId") String batchId);
    void addList(@Param("list") List<op_area> list);
    void delete(Integer lineCode);
}
