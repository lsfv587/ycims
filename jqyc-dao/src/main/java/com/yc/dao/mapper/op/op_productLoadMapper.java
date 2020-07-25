package com.yc.dao.mapper.op;

import com.yc.dao.entity.op.op_productLoad;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-22 11:36
 **/
@Mapper
@Repository
public interface op_productLoadMapper {
    List<op_productLoad> getProducts(Integer lineCode);
    void addList(@Param("list") List<op_productLoad> list);
    void delete(Integer lineCode);
}
