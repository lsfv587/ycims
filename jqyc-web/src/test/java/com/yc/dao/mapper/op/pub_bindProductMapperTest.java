package com.yc.dao.mapper.op;

import com.yc.dao.entity.op.op_product;
import com.yc.dao.entity.op.op_productBind;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-20 09:16
 **/
@SpringBootTest
public class pub_bindProductMapperTest {
    @Resource
    private op_channelBindMapper channelBindMapper;
    @Resource op_productMapper opProductMapper;

    @Test
    public void getList()
    {
        List<op_product> opProducts = opProductMapper.getChoseProducts(1,"1");
        System.out.println(opProducts);
    }
}
