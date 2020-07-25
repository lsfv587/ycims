package com.yc.dataIo.mapper;

import com.yc.dao.entity.pub.pub_batch;
import com.yc.dao.entity.pub.pub_channel;
import com.yc.dao.entity.pub.pub_channelBind;
import com.yc.dao.entity.pub.pub_product;
import com.yc.dao.mapper.pub.pub_batchMapper;
import com.yc.dao.mapper.pub.pub_channelBindMapper;
import com.yc.dao.mapper.pub.pub_channelMapper;
import com.yc.dao.mapper.pub.pub_productMapper;
import com.yc.dataIo.entity.io_pub_batch;
import com.yc.dataIo.entity.io_pub_channel;
import com.yc.dataIo.entity.io_pub_channelBind;
import com.yc.dataIo.entity.io_pub_product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-15 12:28
 **/
@SpringBootTest
public class pub_test {
    @Resource
    private io_pub_channelMapper channelMapper;
    @Resource
    private io_pub_productMapper productMapper;
    @Resource
    private io_pub_channelBindMapper ioPubChannelBindMapper;
    @Resource
    private io_pub_batchMapper ioPubBatchMapper;
    @Resource
    private pub_channelMapper pub_channelMapper;
    @Resource
    private pub_productMapper pub_productMapper;
    @Resource
    private pub_channelBindMapper pubChannelBindMapper;
    @Resource
    private pub_batchMapper pubBatchMapper;

    @Test
    public void findAll_channel()
    {
        List<io_pub_channel> list = channelMapper.findAll();
        List<pub_channel> channels = new ArrayList<>(list.size());
        for (io_pub_channel c : list){
            pub_channel channel = new pub_channel();
            BeanUtils.copyProperties(c,channel);
            channels.add(channel);
        }
        System.out.println(channels);
        pub_channelMapper.addList(channels);
    }
    @Test
    public void finaAll_product(){
        List<io_pub_product> list = productMapper.findAll();
        List<pub_product> products = new ArrayList<>(list.size());
        for (io_pub_product c : list){
            pub_product product = new pub_product();
            BeanUtils.copyProperties(c,product);
            products.add(product);
        }
        System.out.println(products);
        pub_productMapper.addList(products);
    }
    @Test
    public void findAll_channelBind()
    {
        List<io_pub_channelBind> list = ioPubChannelBindMapper.findAll();
        List<pub_channelBind> targets = new ArrayList<>(list.size());
        for (io_pub_channelBind c : list){
            pub_channelBind target = new pub_channelBind();
            BeanUtils.copyProperties(c,target);
            targets.add(target);
        }
        System.out.println(targets);
        pubChannelBindMapper.addList(targets);
    }
    @Test
    public void findAll_batch()
    {
        List<io_pub_batch> list = ioPubBatchMapper.findAll();
        System.out.println(list);
        List<pub_batch> targets = new ArrayList<>(list.size());
        for (io_pub_batch c : list){
            pub_batch target = new pub_batch();
            BeanUtils.copyProperties(c,target);
            targets.add(target);
        }
        System.out.println(targets);
        pubBatchMapper.addList(targets);
    }
}
