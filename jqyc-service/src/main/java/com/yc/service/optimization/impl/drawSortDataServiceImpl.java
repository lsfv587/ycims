package com.yc.service.optimization.impl;

import com.yc.dao.entity.op.*;
import com.yc.dao.mapper.dl.dl_orderMapper;
import com.yc.dao.mapper.op.*;
import com.yc.service.optimization.drawSortDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: jqyc
 * @description:提取已选择的分拣数据
 * @author: lsf
 * @create: 2020-07-21 14:28
 **/
@Service
public class drawSortDataServiceImpl implements drawSortDataService {

    @Resource
    private dl_orderMapper dlOrderMapper;
    @Resource
    private op_batchMapper opBatchMapper;
    @Resource
    private op_areaMapper opAreaMapper;
    @Resource
    private op_pathMapper opPathMapper;
    @Resource
    private op_custMapper opCustMapper;
    @Resource
    private op_productMapper opProductMapper;
    @Resource
    private op_orderMapper opOrderMapper;
    @Override
    public void drawSortDataOp(Integer lineCode) {
        //生成订单批次信息
        op_batch opBatch = createOrderBatch(lineCode);
        //获取选中的分拣区域信息
        List<op_area> opAreas = opAreaMapper.getChoseArea(lineCode,opBatch.getId());
        //获取选中的分拣线路信息
        List<op_path> opPaths = opPathMapper.getChosePath(lineCode,opBatch.getId());
        //获取选中的分拣客户信息
        List<op_cust> opCusts = opCustMapper.getChoseCust(lineCode,opBatch.getId());
        //获取选中的分拣产品信息
        List<op_product> opProducts = opProductMapper.getChoseProducts(lineCode,opBatch.getId());
        //获取选中的分拣订单信息
        List<op_order> opOrders = opOrderMapper.getChoseOrders(lineCode,opBatch.getId());

        if (opAreas.size() == 0 || opPaths.size() == 0 ||opCusts.size() == 0 ||
            opProducts.size() == 0 ||opOrders.size() == 0){
            return ;
        }
        Integer qty = opOrders.stream()
                .mapToInt(op_order::getQty).sum();
        opBatch.setQty(qty);
        opBatch.setCustNum(opCusts.size());
        opBatch.setCustAvgQty((int) (Math.round(qty*1.0) / opBatch.getCustNum()));
        insertOpData(opBatch,opAreas,opPaths,opCusts,opProducts,opOrders);
    }

    @Transactional
    @Override
    public void insertOpData(op_batch batch, List<op_area> areas, List<op_path> paths,
                             List<op_cust> custs, List<op_product> products, List<op_order> orders) {
        //插入数据之前删除原有数据
        opOrderMapper.deleteOrderChannel(batch.getLineCode());
        opOrderMapper.delete(batch.getLineCode());
        opProductMapper.delete(batch.getLineCode());
        opCustMapper.delete(batch.getLineCode());
        opPathMapper.delete(batch.getLineCode());
        opAreaMapper.delete(batch.getLineCode());
        opBatchMapper.delete(batch.getLineCode());
        //插入数据
        opBatchMapper.add(batch);
        opAreaMapper.addList(areas);
        opPathMapper.addList(paths);
        opCustMapper.addList(custs);
        opProductMapper.addList(products);
        opOrderMapper.addList(orders);
    }

    //生成订单批次信息
    private op_batch createOrderBatch(Integer lineCode)
    {
        op_batch batch = new op_batch();
        //设订单日期
        String orderDate = dlOrderMapper.getOrderDate(lineCode);
        batch.setOrderDate(orderDate);
        batch.setLineCode(lineCode);
        //设置批次号和批次顺序
        batch.setBatchCode("1");
        batch.setBatchSequence(1);
        //设置分拣日期
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        batch.setPickDate(sdf.format(now));
        //设置批次id
        batch.setId(orderDate + lineCode.toString() + batch.getBatchCode());
        return  batch;
    }
}
