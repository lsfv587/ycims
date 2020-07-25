package com.yc.dataIo.mapper;

import com.yc.dataIo.entity.io_area;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-15 17:04
 **/
@Mapper
@Repository
public interface io_areaMapper {
    List<io_area> findAll();
}
