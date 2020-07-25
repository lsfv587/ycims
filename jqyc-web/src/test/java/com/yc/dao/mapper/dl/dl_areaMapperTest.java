package com.yc.dao.mapper.dl;

import com.yc.dao.entity.dl.dl_area;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-15 11:07
 **/
@SpringBootTest
public class dl_areaMapperTest {

    @Resource
    private dl_areaMapper mapper;

    @Test
    public void findAll()
    {
        List<dl_area> list = mapper.findAll();
        System.out.println(list);
    }

}
