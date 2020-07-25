package com.yc.dao.mapper.wm;

import com.yc.dao.entity.wm.wm_stock;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-16 09:55
 **/
@Mapper
@Repository
public interface wm_stockMapper {

    List<wm_stock> getProductStocks(Integer lineCode);

    List<wm_stock> getChannelStocks(Integer lineCode);
}
