package com.yc.dao.mapper.pub;

import com.yc.dao.entity.pub.pub_optimizationStep;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-28 15:54
 **/
@Mapper
@Repository
public interface pub_optimizationStepMapper {
    List<pub_optimizationStep> getOpSteps();
}
