package com.yc.dao.mapper.st;

import com.yc.dao.entity.st.st_product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-23 17:15
 **/
@Mapper
@Repository
public interface st_productMapper {
    List<st_product> findStProduct(Integer lineCode);
    void addList(@Param("list") List<st_product> list);
    void delete(String batchId);
}
