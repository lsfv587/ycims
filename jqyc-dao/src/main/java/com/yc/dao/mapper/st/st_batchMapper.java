package com.yc.dao.mapper.st;

import com.yc.dao.entity.st.st_batch;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface st_batchMapper {
    st_batch findStBatch(Integer lineCode);
    void addBatch(st_batch batch);
    void delete(String batchId);
}
