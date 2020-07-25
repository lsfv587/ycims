package com.yc.service.optimization.impl;

import com.yc.dao.entity.op.*;
import com.yc.dao.mapper.op.*;
import com.yc.service.optimization.distributeChannelService;
import com.yc.service.optimization.helper.distributeChanelCoreHelper;
import com.yc.service.optimization.helper.distributeChannelHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: jqyc
 * @description: 分配烟仓服务实现类
 * @author: lsf
 * @create: 2020-07-16 08:49
 **/
@Service
public class distributeChannelServiceImpl implements distributeChannelService {

    @Resource
    private op_productLoadMapper opProductMapper;
    @Resource
    private op_lineMapper opLineMapper;
    @Resource
    private op_channelMapper opChannelMapper;
    @Resource
    private op_channelBindMapper opChannelBindMapper;
    private distributeChannelHelper helper;
    private distributeChanelCoreHelper coreHelper;
    @Override
    public void distributeChannel(Integer lineCode) {
        helper = new distributeChannelHelper();
        coreHelper = new distributeChanelCoreHelper(helper,opChannelBindMapper);
        //根据分拣线代码获取分拣信息
        op_line opLine = opLineMapper.getLine(lineCode);
        //获取所有产品分拣数量信息
        List<op_productLoad> opProducts = opProductMapper.getProducts(lineCode);
        //根据分拣线获取烟仓信息
        List<op_channel> opChannels = opChannelMapper.getChannels(lineCode);
        //首先分配有有库存的烟仓产品
        coreHelper.dispatchStockChannelProduct(opLine,opProducts,opChannels);
        //其次分配已绑定烟仓的产品
        coreHelper.dispatchBindChannelProduct(opLine,opProducts,opChannels);
        //给已绑定产品的烟仓分配数量
        coreHelper.dispatchChannelQty(opLine,opProducts,opChannels);
        //优先给整件上烟烟仓分配产品
        distributeIntChannel(opLine,opProducts,opChannels);
        //其次给非整件上烟烟仓分配产品
        distributeNoIntChannel(opLine,opProducts,opChannels);
        //最后给电子标签烟仓分配产品
        distributeTagChannel(opLine,opProducts,opChannels);
        //给已绑定产品的烟仓分配数量
        coreHelper.dispatchChannelQty(opLine,opProducts,opChannels);
        List<op_channel> channels = helper.getBindChannels(opChannels);
        //保存数据到数据库
        insertData(lineCode,channels,opProducts);
    }

    @Transactional
    @Override
    public void insertData(Integer lineCode,List<op_channel> channels, List<op_productLoad> productLoads) {
        //插入数据之前删除原先的数据
        opChannelMapper.delete(lineCode);
        opProductMapper.delete(lineCode);
        //插入数据
        opChannelMapper.addList(channels);
        opProductMapper.addList(productLoads);
    }

    //给整件上烟烟仓分配产品
    private void distributeIntChannel(op_line opLine,List<op_productLoad> opProducts,
                                          List<op_channel> opChannels)
    {
        //获取整件上烟的烟仓信息
        List<op_channel> intChannels = helper.getChannelsByInt(opChannels);
        //获取分拣数量大于整件烟的产品信息
        List<op_productLoad> intProducts = helper.getProductsByInt(opProducts);
        //根据产品尺寸和烟仓尺寸，分配烟仓
        coreHelper.dispatchChannelProduct(opLine,intProducts,intChannels);
    }
    //给非整件上烟立式烟仓分配产品
    private void distributeNoIntChannel(op_line opLine,List<op_productLoad> opProducts,
                                      List<op_channel> opChannels) {
        List<op_productLoad> noBindProducts = helper.getNoBindProducts(opProducts);
        List<op_channel> noIntChannels = helper.getChannelsByNormal(opChannels);
        //根据产品尺寸和烟仓尺寸，分配烟仓
        coreHelper.dispatchChannelProduct(opLine,noBindProducts,noIntChannels);
    }
    //给电子标签烟仓分配产品,电子标签产品只分配异型烟
    private void distributeTagChannel(op_line opLine,List<op_productLoad> opProducts,
                                      List<op_channel> opChannels) {
        List<op_productLoad> noBindProducts = helper.getNoBindProducts(opProducts);
        List<op_channel> noIntChannels = helper.getChannelsByTag(opChannels);
        //根据产品尺寸和烟仓尺寸，分配烟仓
        coreHelper.dispatchChannelProduct(opLine,noBindProducts,noIntChannels);
    }
}
