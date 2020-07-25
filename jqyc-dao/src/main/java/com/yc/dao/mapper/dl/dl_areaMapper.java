package com.yc.dao.mapper.dl;

import com.yc.dao.entity.dl.dl_area;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-15 11:04
 **/
@Repository
@Mapper
public interface dl_areaMapper {
    List<dl_area> findAll();
    void addList(@Param("list") List<dl_area> list);
    void delete(Integer lineCode);
}
