package com.yc.service.optimization.impl;

import com.yc.dao.entity.op.op_channel;
import com.yc.dao.entity.op.op_order;
import com.yc.dao.entity.op.op_orderChannel;
import com.yc.dao.mapper.op.op_channelMapper;
import com.yc.dao.mapper.op.op_orderMapper;
import com.yc.service.optimization.relateOrderChannelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-22 14:22
 **/
@Service
public class relateOrderChannelServiceImpl implements relateOrderChannelService {
    @Resource
    private op_orderMapper opOrderMapper;
    @Resource
    private op_channelMapper opChannelMapper;
    @Override
    public void relateOrderChannel(Integer lineCode) throws Exception {
            List<op_order> opOrders = opOrderMapper.getOrders(lineCode);
            List<op_channel> opChannels = opChannelMapper.getOpChannels(lineCode);
            List<op_orderChannel> orderChannels = new ArrayList<>(opChannels.size());
            for(op_order o : opOrders)
            {
                orderChannels.add(getChannel(opChannels,o));
            }
            if(orderChannels.size() == 0)
                throw new Exception("关联订单烟仓失败，原因: 订单烟仓信息为空！") ;
            opOrderMapper.addOrderChannels(orderChannels);
    }

    //
    private op_orderChannel getChannel(List<op_channel> channels,op_order o)
    {
        op_channel channel = channels.stream()
                .filter(c -> c.getQty() > 0 && c.getProductCode().equals(o.getProductCode()))
                .findFirst().get();
        op_orderChannel orderChannel = new op_orderChannel();
        orderChannel.setOrderId(o.getId());
        orderChannel.setChannelCode(channel.getChannelCode());
        orderChannel.setChannelQty(o.getQty());
        orderChannel.setSubLineCode(channel.getSubLineCode());
        channel.setQty(channel.getQty() - o.getQty());
        return orderChannel;
    }
}
