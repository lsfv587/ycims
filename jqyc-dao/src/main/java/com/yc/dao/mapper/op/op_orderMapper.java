package com.yc.dao.mapper.op;

import com.yc.dao.entity.op.op_order;
import com.yc.dao.entity.op.op_orderChannel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-22 09:44
 **/
public interface op_orderMapper {
    List<op_order> getChoseOrders(@Param("lineCode") Integer lineCode,
                                  @Param("batchId") String batchId);
    List<op_order> getOrders(Integer lineCode);
    List<op_order> getOrderMas(Integer lineCode);
    List<op_orderChannel> getOrderDets(Integer lineCode);
    void addList(@Param("list") List<op_order> list);
    void delete(Integer lineCode);
    void addOrderChannels(@Param("list") List<op_orderChannel> list);
    void deleteOrderChannel(Integer lineCode);
}
