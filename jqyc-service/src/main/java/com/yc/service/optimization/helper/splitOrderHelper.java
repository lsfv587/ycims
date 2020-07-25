package com.yc.service.optimization.helper;

import com.yc.dao.entity.op.op_order;
import com.yc.dao.entity.op.op_orderChannel;
import com.yc.dao.entity.pub.pub_subLine;
import com.yc.dao.entity.st.st_orderDet;
import com.yc.dao.entity.st.st_orderMas;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: jqyc
 * @description:订单拆分服务帮助类
 * @author: lsf
 * @create: 2020-07-23 15:57
 **/
public class splitOrderHelper {
    public List<st_orderMas> splitOrderProcess(List<op_order> orderMas,
                                                List<op_orderChannel> orderDets, List<pub_subLine> subLines){
        List<st_orderMas> stOrderMas = new ArrayList<>(orderMas.size());
        Integer serilaNo = 1;//订单流水号
        Integer subSerialNo = 1;//子线流水号
        Integer tempSubSerialNo = 1;//临时子线流水号
        String lastOrderCode = "";
        for (op_order mas : orderMas){
            tempSubSerialNo = tempSubSerialNo > subSerialNo ? tempSubSerialNo : subSerialNo;
            if (!mas.getOrderCode().equals(lastOrderCode)) {
                lastOrderCode = mas.getOrderCode();
                serilaNo = tempSubSerialNo;
            }
            subSerialNo = serilaNo;
            st_orderMas tp_order = createOrderMas(mas);
            tp_order.setSerialNo(subSerialNo);
            //获取子线缓存
            pub_subLine subLine = getSubLine(subLines,mas.getSubLineCode());
            //获取订单明细
            List<op_orderChannel> dets =getOrderDets(orderDets,mas.getOrderCode(),mas.getSubLineCode());
            //判断子线数量是否小于等于缓存数量
            if (mas.getSubLineQty() <= subLine.getMaxCacheNum()){
                //子线数量小于缓存数量
                for (op_orderChannel det: dets){
                    st_orderDet tp_det = createOrderDet(det,tp_order.getId());
                    tp_det.setQty(det.getChannelQty());
                    tp_order.getDets().add(tp_det);
                    tp_order.setSubLineQty(tp_order.getSubLineQty() + tp_det.getQty());
                }
            }
            else{
                //子线数量大于缓存数量
                Integer cursublinecache = subLine.getMaxCacheNum();//子线缓存
                for (op_orderChannel det : dets){
                    Integer curDetQty = det.getChannelQty();
                    while (curDetQty > 0){
                        if (curDetQty > cursublinecache){
                            if (cursublinecache == 0 ){
                                cursublinecache = curDetQty > subLine.getMaxCacheNum()
                                        ? subLine.getMaxCacheNum() :curDetQty;
                            }
                            //单一品牌数量大于子线缓存
                            st_orderDet tp_det = createOrderDet(det,tp_order.getId());
                            if (curDetQty - cursublinecache < cursublinecache)
                            {
                                //当剩余数量小于子线最大缓存数的时候,平均数量
                                cursublinecache = curDetQty / 2;
                            }
                            tp_det.setQty(cursublinecache);
                            tp_order.getDets().add(tp_det);
                            tp_order.setSubLineQty(tp_order.getSubLineQty() + tp_det.getQty());
                            stOrderMas.add(tp_order);
                            //当前品牌数量减少
                            curDetQty -= cursublinecache;
                            //子线单号增加
                            subSerialNo ++;
                            tp_order = createOrderMas(mas);
                            tp_order.setSerialNo(subSerialNo);
                            cursublinecache = subLine.getMaxCacheNum();//子线缓存
                        }
                        else{
                            //单一品牌数量小于子线缓存
                            st_orderDet tp_det = createOrderDet(det,tp_order.getId());
                            tp_det.setQty(curDetQty);
                            tp_order.setSubLineQty(tp_order.getSubLineQty() + tp_det.getQty());
                            tp_order.getDets().add(tp_det);
                            cursublinecache -= tp_det.getQty();
                            //当前品牌数量减少
                            curDetQty = 0;
                        }
                    }
                }
            }
            if (tp_order.getDets().size() > 0){
                stOrderMas.add(tp_order);
            }
            subSerialNo ++;
        }
        return stOrderMas;
    }
    private st_orderMas createOrderMas(op_order mas)
    {
        st_orderMas tp_order = new st_orderMas();
        tp_order.setId(UUID.randomUUID().toString().replaceAll("-",""));
        tp_order.setOrderCode(mas.getOrderCode());
        tp_order.setCustCode(mas.getCustCode());
        tp_order.setSubLineQty(0);
        tp_order.setSubLineCode(mas.getSubLineCode());
        tp_order.setDets(new ArrayList<>());
        return tp_order;
    }
    private st_orderDet createOrderDet(op_orderChannel det,String orderMasId)
    {
        st_orderDet tp_det = new st_orderDet();
        tp_det.setOrderMasId(orderMasId);
        tp_det.setId(UUID.randomUUID().toString().replaceAll("-",""));
        tp_det.setChannelCode(det.getChannelCode());
        tp_det.setProductCode(det.getProductCode());
        return tp_det;
    }
    private pub_subLine getSubLine(List<pub_subLine> subLines,Integer subLineCode)
    {
        Optional<pub_subLine> optional = subLines.stream()
                .filter(s -> s.getSubLineCode() == subLineCode)
                .findFirst();
        if (optional.isPresent())
            return optional.get();
        else
            return  null;
    }
    private List<op_orderChannel> getOrderDets(List<op_orderChannel> dets,String orderCode,Integer sublineCode)
    {
        return dets.stream()
                .filter(d -> d.getOrderCode().equals(orderCode) && d.getSubLineCode().equals(sublineCode))
                .collect(Collectors.toList());
    }
}
