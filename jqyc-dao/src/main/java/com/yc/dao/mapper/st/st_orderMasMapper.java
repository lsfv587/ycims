package com.yc.dao.mapper.st;

import com.yc.dao.entity.st.st_orderMas;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-24 08:36
 **/
@Mapper
@Repository
public interface st_orderMasMapper {
    void addList(@Param("list")List<st_orderMas> list);
    void delete(String batchId);
}
