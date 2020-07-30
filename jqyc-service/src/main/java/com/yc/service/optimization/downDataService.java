package com.yc.service.optimization;

import com.yc.dao.entity.dl.*;

import java.util.List;

/**
 * @program: jqyc
 * @description: 下载数据订单服务
 * @author: lsf
 * @create: 2020-07-15 15:43
 **/
public interface downDataService {
    void downData(String orderDate,Integer lineCode) throws Exception;
    void insertData(Integer lineCode,List<dl_area> areas,List<dl_order> orders, List<dl_cust> custs, List<dl_path> paths, List<dl_product> products);
}
