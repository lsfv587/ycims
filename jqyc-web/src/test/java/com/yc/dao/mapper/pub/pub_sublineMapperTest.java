package com.yc.dao.mapper.pub;

import com.yc.dao.entity.pub.pub_subLine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-16 09:19
 **/
@SpringBootTest
public class pub_sublineMapperTest {
    @Resource
    private pub_subLineMapper mapper;

    @Test
    public void getSubLines()
    {
        List<pub_subLine> list = mapper.getSubLines(1);
        System.out.println(list);
    }
}
