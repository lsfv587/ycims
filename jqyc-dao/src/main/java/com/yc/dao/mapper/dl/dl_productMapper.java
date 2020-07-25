package com.yc.dao.mapper.dl;

import com.yc.dao.entity.dl.dl_product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-15 15:21
 **/
@Mapper
@Repository
public interface dl_productMapper {
    List<dl_product> getProducts();
    void addList(@Param("list") List<dl_product> list);
    void delete(Integer lineCode);
}
