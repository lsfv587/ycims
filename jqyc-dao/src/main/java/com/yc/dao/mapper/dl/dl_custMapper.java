package com.yc.dao.mapper.dl;

import com.yc.dao.entity.dl.dl_cust;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-15 15:06
 **/
@Mapper
@Repository
public interface dl_custMapper {
    void addList(@Param("list") List<dl_cust> list);
    void delete(Integer lineCode);
}
