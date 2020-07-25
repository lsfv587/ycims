package com.yc.service.optimization.helper;

import com.yc.dao.entity.op.*;
import com.yc.dao.mapper.op.op_channelBindMapper;
import common.tool.commonUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: jqyc
 * @description: 烟仓分配核心帮助类
 * @author: lsf
 * @create: 2020-07-18 10:24
 **/
public class distributeChanelCoreHelper {
    private op_channelBindMapper opChannelBindMapper;
    private distributeChannelHelper helper;
    public distributeChanelCoreHelper(distributeChannelHelper helper,op_channelBindMapper opChannelBindMapper)
    {
        this.helper = helper;
        this.opChannelBindMapper = opChannelBindMapper;
    }
    /**
     * @Description:分配有库存的烟仓产品
     * @return:
     * @Author: lsf
     * @Date: 2020/7/16
     */
    public void dispatchStockChannelProduct(op_line opLine,List<op_productLoad> opProducts,
                        List<op_channel> opChannels) {
        List<op_productBind> productBinds = null;
        //根据分拣线代码获取分拣线信息
        if (opLine.getStockModel() == 0){
            //分拣线无库存模式,分拣线烟仓设置为有库存模式
            //获取为烟仓库存模式并且有库存和绑定了烟仓的产品烟仓绑定信息
            productBinds = opChannelBindMapper.getChannelWmBinds(opLine.getLineCode());
        }
        else{
            //分拣线有库存模式
            //获取烟仓库存信息和绑定了烟仓的产品烟仓绑定信息
            productBinds = opChannelBindMapper.getProductWmBinds(opLine.getLineCode());
        }
        if (productBinds == null || productBinds.size() == 0)
            return;
        for (op_productBind bind : productBinds) {
            op_productLoad product = helper.getProductByCode(opProducts,bind.getProductCode());
            for(op_productBindDet det : bind.getDets()) {
                op_channel channel = helper.getChannelByCode(opChannels, det.getChannelCode());
                if (channel != null && !channel.isBand()) {
                    channel.setProductCode(product.getProductCode());
                    channel.setQty(det.getPriority());//设置该烟仓对应的库存数量
                    channel.setBand(true);
                }
            }
            product.setBand(true);
        }
    }
    /**
     * @Description:分配已绑定烟仓的产品
     * @return:
     * @Author: lsf
     * @Date: 2020/7/17
     */
    public void dispatchBindChannelProduct(op_line opLine,List<op_productLoad> opProducts,
                   List<op_channel> opChannels){
        List<op_channelBind> opChannelBinds;
        opChannelBinds = opChannelBindMapper.getChannelBinds(opLine.getLineCode());
        if(opChannelBinds == null || opChannelBinds.size() == 0)
            return;
        for(op_channelBind b : opChannelBinds){
            op_channel channel = helper.getChannelByCode(opChannels,b.getChannelCode());
            if (channel != null && !channel.isBand())
            {
                //获取烟仓绑定优先级高的产品
                op_channelBindDet det = b.getDets().stream()
                        .findFirst().get();
                op_productLoad product = helper.getProductByCode(opProducts,det.getProductCode());
                if (product != null){
                    channel.setProductCode(product.getProductCode());
                    channel.setBand(true);
                    product.setBand(true);
                }
            }
        }
    }
    /**
     * @Description:给其它产品分配烟仓
     * @return:
     * @Author: lsf
     * @Date: 2020/7/17
     */
public void dispatchChannelProduct (op_line opLine,List<op_productLoad> opProducts,
                                         List<op_channel> opChannels) {
        //获取未分配烟仓的产品信息
        List<op_productLoad> noBindProducts = helper.getNoBindProducts(opProducts);
        //获取未绑定产品的烟仓信息
        List<op_channel> noBindChannels = helper.getNoBindChannels(opChannels);
        if (noBindProducts == null || noBindProducts.size() == 0 ||
                noBindChannels == null || noBindChannels.size() == 0)
            return;
        for (op_productLoad p : noBindProducts) {
            op_subLine subLine;
            op_channel channel = null;
            if (p.getIsAbnor() == 1) {
                //根据异性烟根据产品尺寸获取合适的烟仓集合
                List<op_channel> suitChannels = helper.getChannelsByProductSize(noBindChannels, p);
                if (suitChannels != null && suitChannels.size() > 0) {
                    //获取符合产品尺寸的烟仓所在的子线集合
                    List<Integer> subLines = suitChannels.stream()
                            .map(c -> c.getSubLineCode())
                            .distinct()
                            .collect(Collectors.toList());
                    subLine = helper.getSubline(opLine.getSubLines(), subLines);
                    channel = helper.getNoBindChannel(suitChannels, subLine.getSubLineCode());
                }
            } else {
                //常规烟，获取产品数量较少的子线
                subLine = helper.getSubline(opLine.getSubLines());
                channel = helper.getNoBindChannel(noBindChannels, subLine.getSubLineCode());
            }
            if(channel != null) {
                channel.setProductCode(p.getProductCode());
                channel.setBand(true);
                p.setBand(true);
            }
        }
    }
    /**
     * @Description:给整件上烟烟仓分配数量
     * @return:
     * @Author: lsf
     * @Date: 2020/7/17
     */
    public void dispatchChannelQty(op_line opLine,List<op_productLoad> opProducts,
                                      List<op_channel> opChannels){
        //获取已分配烟仓的并且数量大于0的产品信息
        List<op_productLoad> bindProducts = helper.getBindProducts(opProducts);
        for (op_productLoad p : bindProducts) {
            //获取产品绑定的烟仓信息
            List<op_channel> bindChannels = helper.getBindChannelsByProduct(opChannels, p.getProductCode());
            Integer count = bindChannels.size();
            Integer syQty = p.getOpQty();
            Integer dispatchQty = syQty;
            if (count > 1 && p.getOpQty() >= p.getJqty() * count)
            {
                //多仓，每个烟仓至少能分配一件烟
                for (op_channel c : bindChannels) {
                    if (count == 1) {
                        dispatchQty = syQty;
                        if (c.getChannelType() == 2 || c.getChannelType() == 3 ||
                            c.getChannelType() == 5 || c.getChannelType() == 6) {
                            //整件上烟烟仓数量处理
                            dispatchQty = getDispatchIntQty(opLine, p, dispatchQty, c);
                        }
                    }
                    else{
                        dispatchQty = dispatchQty / count;
                        if (c.getChannelType() == 2 || c.getChannelType() == 3 ||
                                c.getChannelType() == 5 || c.getChannelType() == 6) {
                            //整件上烟烟仓数量处理
                            dispatchQty = commonUtils.getNumTimes(dispatchQty, p.getJqty());
                        }
                    }
                    p.setPrepareQty(p.getPrepareQty() +dispatchQty);
                    c.setQty(dispatchQty);
                    op_subLine subLine = helper.getSubLineByCode(opLine.getSubLines(),c.getSubLineCode());
                    subLine.setQty(subLine.getQty() + dispatchQty);
                    p.setOpQty(p.getOpQty() - dispatchQty);
                    syQty -= dispatchQty;
                    count--;
                }
            }else {
                //单仓
                op_channel c = bindChannels.stream().findFirst().get();
                op_subLine subLine = helper.getSubLineByCode(opLine.getSubLines(), c.getSubLineCode());
                if (c.getChannelType() == 2 || c.getChannelType() == 3 ||
                        c.getChannelType() == 5 || c.getChannelType() == 6) {
                    dispatchQty = getDispatchIntQty(opLine, p, dispatchQty, c);
                }
                dispatchChannelQty(c,p,subLine,dispatchQty);
            }
        }
    }
    private Integer getDispatchIntQty(op_line opLine, op_productLoad p, Integer dispatchQty, op_channel channel) {
        if (opLine.getStockModel() == 1 || channel.getStockModel() == 1) {
            //有库存模式，零烟凑整
            dispatchQty = dispatchQty - channel.getStockQty();
            dispatchQty = dispatchQty + p.getJqty() - (dispatchQty % p.getJqty());
        } else {
            //无库存模式,零条烟分配到非整件上烟烟仓
            if (dispatchQty % p.getJqty() != 0)
                p.setBand(false);
            dispatchQty = commonUtils.getNumTimes(dispatchQty, p.getJqty());
        }
        return dispatchQty;
    }

    private void dispatchChannelQty(op_channel channel, op_productLoad p, op_subLine subLine, Integer dispatchQty) {
            channel.setQty(dispatchQty);
            p.setOpQty(p.getOpQty() - dispatchQty);
            p.setPrepareQty(p.getPrepareQty() + dispatchQty);
            subLine.setQty(subLine.getQty() + dispatchQty);
    }
}
