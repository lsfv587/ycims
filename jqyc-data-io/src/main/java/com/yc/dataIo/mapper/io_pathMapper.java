package com.yc.dataIo.mapper;

import com.yc.dataIo.entity.io_path;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-14 12:21
 **/
@Repository
@Mapper
public interface io_pathMapper {
    List<io_path> findAll();
}
