package com.yc.dao.mapper.pub;

import com.yc.dao.entity.pub.pub_subLine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-16 09:00
 **/
@Mapper
@Repository
public interface pub_subLineMapper {
    List<pub_subLine> getSubLines(Integer lineCode);
}
