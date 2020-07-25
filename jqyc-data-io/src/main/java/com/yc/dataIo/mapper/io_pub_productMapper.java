package com.yc.dataIo.mapper;

import com.yc.dataIo.entity.io_pub_product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-14 14:32
 **/
@Mapper
@Repository
public interface io_pub_productMapper {
    List<io_pub_product> findAll();
}
