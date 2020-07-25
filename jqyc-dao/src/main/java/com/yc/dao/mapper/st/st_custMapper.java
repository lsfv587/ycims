package com.yc.dao.mapper.st;

import com.yc.dao.entity.st.st_cust;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-23 16:42
 **/
@Mapper
@Repository
public interface st_custMapper {
    List<st_cust> findStCust(Integer lineCode);
    void addList(@Param("list") List<st_cust> list);
    void delete(String batchId);
}
