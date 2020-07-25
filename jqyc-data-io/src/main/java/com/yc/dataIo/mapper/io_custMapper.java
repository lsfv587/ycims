package com.yc.dataIo.mapper;

import com.yc.dataIo.entity.io_cust;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-14 11:54
 **/
@Mapper
@Repository
public interface io_custMapper {
    List<io_cust> findAll();
}
