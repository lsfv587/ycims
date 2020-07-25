package com.yc.service.optimization;

import com.yc.dao.entity.op.*;

import java.util.List;

public interface drawSortDataService {
    void drawSortDataOp(Integer lineCode);
    void insertOpData(op_batch batch, List<op_area> areas,List<op_path> paths,
                      List<op_cust> custs,List<op_product> products,List<op_order> orders);
}
