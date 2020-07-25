package com.yc.service.optimization.impl;

import com.yc.dao.entity.dl.*;
import com.yc.dao.mapper.dl.*;
import com.yc.dataIo.entity.*;
import com.yc.dataIo.mapper.*;
import com.yc.service.optimization.downDataService;
import common.tool.commonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @program: jqyc
 * @description: 下载接口数据服务实现类
 * @author: lsf
 * @create: 2020-07-15 15:45
 **/
@Service
public class downDataServiceImpl implements downDataService {
    @Resource
    private dl_areaMapper dl_areaMapper;
    @Resource
    private dl_orderMapper dl_orderMapper;
    @Resource
    private dl_custMapper dl_custMapper;
    @Resource
    private dl_pathMapper dl_pathMapper;
    @Resource
    private dl_productMapper dl_productMapper;
    @Resource
    private io_areaMapper io_areaMapper;
    @Resource
    private io_orderMapper io_orderMapper;
    @Resource
    private io_custMapper io_custMapper;
    @Resource
    private io_pathMapper io_pathMapper;
    @Resource
    private io_productMapper io_productMapper;

    @Override
    public void downData(Integer lineCode) throws InstantiationException, IllegalAccessException {
        //获取接口物流数据
        List<io_area> io_areas = io_areaMapper.findAll();
        //获取订单接口数据
        List<io_order> io_orders = io_orderMapper.findAll();
        //获取线路接口数据
        List<io_path> io_paths = io_pathMapper.findAll();
        //获取客户接口数据
        List<io_cust> io_custs = io_custMapper.findAll();
        //获取产品接口数据
        List<io_product> io_products = io_productMapper.findAll();
        //判断接口是否有数据
        if (io_custs.size() == 0 || io_orders.size() == 0 || io_areas.size() == 0 ||
            io_paths.size() == 0 || io_products.size() == 0){
            return ;
        }
        List<dl_area> dl_areas = commonUtils.copyList(io_areas,dl_area.class);
        List<dl_order> dl_orders = commonUtils.copyList(io_orders,dl_order.class);
        List<dl_path> dl_paths = commonUtils.copyList(io_paths,dl_path.class);
        List<dl_cust> dl_custs = commonUtils.copyList(io_custs,dl_cust.class);
        List<dl_product> dl_products = commonUtils.copyList(io_products,dl_product.class);
        //将接口数据插入到本地
        insertData(lineCode,dl_areas,dl_orders,dl_custs,dl_paths,dl_products);
    }

    @Transactional
    @Override
    public void insertData(Integer lineCode,List<dl_area> areas,List<dl_order> orders, List<dl_cust> custs, List<dl_path> paths, List<dl_product> products) {
        //下载数据之前将该分拣线已下载的数据删除
        dl_orderMapper.delete(lineCode);
        dl_custMapper.delete(lineCode);
        dl_pathMapper.delete(lineCode);
        dl_productMapper.delete(lineCode);
        dl_areaMapper.delete(lineCode);
        //插入数据
        dl_areaMapper.addList(areas);
        dl_productMapper.addList(products);
        dl_pathMapper.addList(paths);
        dl_custMapper.addList(custs);
        dl_orderMapper.addList(orders);
    }
}
