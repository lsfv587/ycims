package com.yc.dao.mapper.dl;

import com.yc.dao.entity.dl.dl_order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-15 15:13
 **/
@Mapper
@Repository
public interface dl_orderMapper {
    String getOrderDate(Integer lineCode);
    List<dl_order> getDlOrders(String orderDate);
    void addList(@Param("list") List<dl_order> list);
    void delete(Integer lineCode);
}
