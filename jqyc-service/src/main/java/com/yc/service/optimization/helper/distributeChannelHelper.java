package com.yc.service.optimization.helper;


import com.yc.dao.entity.op.op_channel;
import com.yc.dao.entity.op.op_product;
import com.yc.dao.entity.op.op_productLoad;
import com.yc.dao.entity.op.op_subLine;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @program: jqyc
 * @description: 烟仓分配帮助类
 * @author: lsf
 * @create: 2020-07-16 10:28
 **/
public class distributeChannelHelper {

    //根据产品代码获取产品信息对象
    public op_productLoad getProductByCode(List<op_productLoad> products, String productCode)
    {
        Optional<op_productLoad> optional = products.stream()
                .filter(p -> p.getProductCode().equals(productCode))
                .findFirst();
        if(optional.isPresent()){
            return optional.get();
        }else{
            return null;
        }
    }
    //根据烟仓代码获取烟仓产品信息
    public op_channel getChannelByCode(List<op_channel> channels,String channelCode)
    {
        Optional<op_channel> optional = channels.stream()
                .filter(p ->  p.getChannelCode().equals(channelCode))
                .findFirst();
        if(optional.isPresent()){
            return optional.get();
        }else{
            return null;
        }
    }
    //根据子线代码获取子线信息
    public op_subLine getSubLineByCode(List<op_subLine> subLines,Integer subLineCode)
    {
        Optional<op_subLine> optional = subLines.stream()
                .filter(p -> p.getSubLineCode().equals(subLineCode))
                .findFirst();
        if(optional.isPresent()){
            return optional.get();
        }else{
            return null;
        }
    }
    //根据产品获取合适的未分配产品的烟仓
    public List<op_channel> getChannelsByProductSize(List<op_channel> noBindChannels,op_productLoad p)
    {
        List<op_channel> pub_channels = noBindChannels.stream()
                .filter(c -> !c.isBand() && p.getProductHeight() <= c.getMaxHeight() && p.getProductHeight() >= c.getMinHeight()
                        && p.getProductLength() <= c.getMaxLength() && p.getProductLength() >= c.getMinLength()
                        && p.getProductWidth() <= c.getMaxWidth() && p.getProductWidth() >= c.getMinWidth())
                .collect(Collectors.toList());
        return pub_channels;
    }
    //根据产品获取该产品绑定的烟仓集合信息,根据烟仓的类型排序
    public List<op_channel> getBindChannelsByProduct(List<op_channel> channels,String productCode)
    {
        List<op_channel> bindChannels = channels.stream()
                .filter(c -> c.isBand() && c.getProductCode().equals(productCode) &&
                        c.getQty() == 0)
                .sorted(Comparator.comparingInt(op_channel::getChannelType))
                .collect(Collectors.toList());
        return bindChannels;
    }
    //筛选未绑定的烟仓信息,按烟仓代码排序
    public List<op_channel> getNoBindChannels(List<op_channel> channels){
        return channels.stream()
                .filter(c -> !c.isBand())
                .sorted((o1,o2) -> o1.getChannelCode().compareToIgnoreCase(o2.getChannelCode()))
                .collect(Collectors.toList());
    }
    //筛选未绑定的产品信息,并按产品数量降序排序
    public List<op_productLoad> getNoBindProducts(List<op_productLoad> products)
    {
        return products.stream()
                .filter(p -> !p.isBand())
                .sorted((o1, o2) -> o2.getOpQty() - o1.getOpQty())
                .collect(Collectors.toList());
    }
    //筛选已绑定的产品信息,并按产品数量降序排序
    public List<op_productLoad> getBindProducts(List<op_productLoad> products)
    {
        return products.stream()
                .filter(p -> p.isBand() && p.getOpQty() > 0)
                .sorted((o1, o2) -> o2.getOpQty() - o1.getOpQty())
                .collect(Collectors.toList());
    }
    //获取子线分配烟最少的烟仓
    public op_subLine getSubline(List<op_subLine> opSubLines,List<Integer> p_sublines){
        op_subLine subline =opSubLines.stream()
                .filter(s -> p_sublines.contains(s.getSubLineCode()))
                .sorted(Comparator.comparingInt(op_subLine::getQty))
                .findFirst().get();
        return subline;
    }
    public op_subLine getSubline(List<op_subLine> opSubLines){
        op_subLine subline =opSubLines.stream()
                .sorted(Comparator.comparingInt(op_subLine::getQty))
                .findFirst().get();
        return subline;
    }
    public op_channel getNoBindChannel(List<op_channel> channels,Integer sublinecode){
        op_channel channel =channels.stream()
                .filter(c -> !c.isBand() && c.getSubLineCode() == sublinecode)
                .sorted((o1,o2) -> o1.getChannelCode().compareToIgnoreCase(o2.getChannelCode()))
                .findFirst().get();
        return channel;
    }
    public List<op_channel> getBindChannels(List<op_channel> channels)
    {
        return channels.stream()
                .filter(c -> c.isBand())
                .collect(Collectors.toList());
    }
    //获取大于整件的产品信息
    public List<op_productLoad> getProductsByInt(List<op_productLoad> products)
    {
        return products.stream()
                .filter(p -> !p.isBand() && p.getOpQty() >= p.getJqty())
                .collect(Collectors.toList());
    }
    //获取通道，卧式整件上烟分拣的烟仓信息
    public List<op_channel> getChannelsByInt(List<op_channel> channels)
    {
        return channels.stream()
                .filter(c -> !c.isBand() &&
                        (c.getChannelType() == 3 || c.getChannelType() == 6 ||
                        c.getChannelType() == 2 || c.getChannelType() == 5))
                .collect(Collectors.toList());
    }
    //获取非整件上烟分拣的烟仓信息
    public List<op_channel> getChannelsByNormal(List<op_channel> channels)
    {
        return channels.stream()
                .filter(c -> !c.isBand() && (c.getChannelType() == 1 || c.getChannelType() == 4))
                .collect(Collectors.toList());
    }
    //获取非整件上烟分拣的烟仓信息
    public List<op_channel> getChannelsByTag(List<op_channel> channels)
    {
        return channels.stream()
                .filter(c -> !c.isBand() && c.getChannelType() == 7)
                .collect(Collectors.toList());
    }
}
