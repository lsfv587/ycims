package com.yc.dao.mapper.pub;

import com.yc.dao.entity.pub.pub_product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-15 14:24
 **/
@Mapper
@Repository
public interface pub_productMapper {
    List<pub_product> findAll();
    void addList(@Param("list") List<pub_product> list);
}
