package com.yc.dao.mapper.op;

import com.yc.dao.entity.op.op_product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-16 11:20
 **/
public interface op_productMapper {

    List<op_product> getProducts();
    List<op_product> getChoseProducts(@Param("lineCode") Integer lineCode,
                                      @Param("batchId") String batchId);
    void addList(@Param("list") List<op_product> list);
    void delete(Integer lineCode);
}
