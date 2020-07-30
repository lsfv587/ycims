package com.yc.dao.mapper.dl;

import com.yc.dao.entity.dl.dl_path;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-15 15:18
 **/
@Mapper
@Repository
public interface dl_pathMapper {
    List<dl_path> getDlPaths(Integer lineCode);
    void updateSelectedPath(@Param("list") List<String> list);
    void addList(@Param("list") List<dl_path> list);
    void delete(Integer lineCode);
}
