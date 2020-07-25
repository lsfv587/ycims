package com.yc.dao.mapper.pub;

import com.yc.dao.entity.pub.pub_line;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-16 10:05
 **/
@Mapper
@Repository
public interface pub_lineMapper {
    pub_line getLineByLineCode(Integer lineCode);
}
