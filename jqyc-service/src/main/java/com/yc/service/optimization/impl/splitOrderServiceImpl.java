package com.yc.service.optimization.impl;

import com.yc.dao.entity.op.*;
import com.yc.dao.entity.pub.pub_subLine;
import com.yc.dao.entity.st.*;
import com.yc.dao.mapper.op.*;
import com.yc.dao.mapper.pub.pub_subLineMapper;
import com.yc.dao.mapper.st.*;
import com.yc.service.optimization.helper.splitOrderHelper;
import com.yc.service.optimization.splitOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.dc.pr.PRError;

import javax.annotation.Resource;
import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-20 15:10
 **/
@Service
public class splitOrderServiceImpl implements splitOrderService {
    @Resource
    private pub_subLineMapper pubSubLineMapper;
    @Resource
    private op_orderMapper opOrderMapper;
    @Resource
    private st_batchMapper stBatchMapper;
    @Resource
    private st_pathMapper stPathMapper;
    @Resource
    private st_custMapper stCustMapper;
    @Resource
    private st_channelMapper stChannelMapper;
    @Resource
    private st_productMapper stProductMapper;
    @Resource
    private st_orderMasMapper stOrderMasMapper;
    @Resource
    private st_orderDetMapper stOrderDetMapper;
    private splitOrderHelper helper;

    @Transactional
    @Override
    public void splitOrderOp(Integer lineCode) {
        helper = new splitOrderHelper();
        //获取子线缓存信息
        List<pub_subLine> subLines = pubSubLineMapper.getSubLines(lineCode);
        //获取订单主表信息
        List<op_order> orderMas = opOrderMapper.getOrderMas(lineCode);
        //获取订单明细表信息
        List<op_orderChannel> orderDets = opOrderMapper.getOrderDets(lineCode);
        //进行订单拆分
        List<st_orderMas> stOrderMas = helper.splitOrderProcess(orderMas,orderDets,subLines);
        List<st_orderDet> stOrderDets = new ArrayList<>(orderDets.size());
        //获取批次数据
        st_batch stBatch = stBatchMapper.findStBatch(lineCode);
        //获取线路信息
        List<st_path> stPaths = stPathMapper.findStPath(lineCode);
        //获取客户信息
        List<st_cust> stCusts = stCustMapper.findStCust(lineCode);
        //获取烟仓信息
        List<st_channel> stChannels = stChannelMapper.findStChannel(lineCode);
        //获取产品信息
        List<st_product> stProducts = stProductMapper.findStProduct(lineCode);
        stBatch.setCustNum(stCusts.size());
        Map<Integer,Integer> maps = stOrderMas.stream()
                .collect(Collectors.groupingBy(st_orderMas::getSerialNo,
                        Collectors.summingInt(st_orderMas::getSubLineQty)));
        for (st_orderMas mas : stOrderMas)
        {
            mas.setTotalQty(maps.get(mas.getSerialNo()));
            mas.setBatchId(stBatch.getId());
            stOrderDets.addAll(mas.getDets());
        }
        stOrderDetMapper.delete(stBatch.getId());
        stOrderMasMapper.delete(stBatch.getId());
        stCustMapper.delete(stBatch.getId());
        stChannelMapper.delete(stBatch.getId());
        stProductMapper.delete(stBatch.getId());
        stPathMapper.delete(stBatch.getId());
        stBatchMapper.delete(stBatch.getId());

        stBatchMapper.addBatch(stBatch);
        stPathMapper.addList(stPaths);
        stCustMapper.addList(stCusts);
        stChannelMapper.addList(stChannels);
        stProductMapper.addList(stProducts);
        stOrderMasMapper.addList(stOrderMas);
        stOrderDetMapper.addList(stOrderDets);
    }
}
