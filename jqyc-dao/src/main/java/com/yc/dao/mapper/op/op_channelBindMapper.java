package com.yc.dao.mapper.op;

import com.yc.dao.entity.op.op_channelBind;
import com.yc.dao.entity.op.op_productBind;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-16 15:53
 **/
@Mapper
@Repository
public interface op_channelBindMapper {
    List<op_channelBind> getChannelBinds(Integer lineCode);
    List<op_productBind> getProductWmBinds(Integer lineCode);
    List<op_productBind> getChannelWmBinds(Integer lineCode);
}
