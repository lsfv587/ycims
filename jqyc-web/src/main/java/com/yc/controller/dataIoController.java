package com.yc.controller;


import com.yc.dataIo.entity.io_cust;
import com.yc.dataIo.entity.io_order;
import com.yc.dataIo.entity.io_path;
import com.yc.dataIo.entity.io_product;
import com.yc.dataIo.mapper.io_custMapper;
import com.yc.dataIo.mapper.io_orderMapper;
import com.yc.dataIo.mapper.io_pathMapper;
import com.yc.dataIo.mapper.io_productMapper;
import common.web.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-14 13:42
 **/
@RestController
@RequestMapping("/data/io/")
public class dataIoController {

    @Resource
    private io_orderMapper orderMapper;
    @Resource
    private io_custMapper custMapper;
    @Resource
    private io_pathMapper pathMapper;
    @Resource
    private io_productMapper productMapper;

    @GetMapping("/order")
    public ResponseResult getOrders()
    {
        List<io_order> list = orderMapper.findAll();
        if (list != null && list.size() > 0)
        {
            return ResponseResult.success(list);
        }
        else{
            return ResponseResult.fail("未查询到订单接口数据！");
        }
    }
    @GetMapping("/cust")
    public ResponseResult getCusts()
    {
        List<io_cust> list = custMapper.findAll();
        if (list != null && list.size() > 0)
        {
            return ResponseResult.success(list);
        }
        else{
            return ResponseResult.fail("未查询到客户接口数据！");
        }
    }
    @GetMapping("/path")
    public ResponseResult getPaths()
    {
        List<io_path> list = pathMapper.findAll();
        if (list != null && list.size() > 0)
        {
            return ResponseResult.success(list);
        }
        else{
            return ResponseResult.fail("未查询到线路接口数据！");
        }
    }
    @GetMapping("/product")
    public ResponseResult getProducts()
    {
        List<io_product> list = productMapper.findAll();
        if (list != null && list.size() > 0)
        {
            return ResponseResult.success(list);
        }
        else{
            return ResponseResult.fail("未查询到产品接口数据！");
        }
    }
}
