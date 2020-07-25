package com.yc.dao.mapper.st;

import com.yc.dao.entity.st.st_path;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-23 16:31
 **/
@Mapper
@Repository
public interface st_pathMapper {
    List<st_path> findStPath(Integer lineCode);
    void addList(@Param("list") List<st_path> list);
    void delete(String batchId);
}
