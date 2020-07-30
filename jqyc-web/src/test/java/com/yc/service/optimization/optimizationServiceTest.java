package com.yc.service.optimization;

import com.yc.dao.entity.op.op_channel;
import com.yc.dao.mapper.op.op_channelMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-17 15:43
 **/
@SpringBootTest
public class optimizationServiceTest {
    @Resource
    private downDataService dataService;
    @Resource
    private drawSortDataService drawSortDataService;
    @Resource
    private distributeChannelService distributeChannelService;
    @Resource
    private relateOrderChannelService relateOrderChannelService;
    @Resource
    private splitOrderService splitOrderService;
    private String orderDate = "20191126";

    @Test
    public void downData() throws Exception {
        dataService.downData(orderDate,1);
    }
    @Test
    public void drawSortDataOp() throws Exception {
        drawSortDataService.drawSortDataOp(1);
    }
    @Test
    public void distributeChannel()
    {
        distributeChannelService.distributeChannel(1);
    }
    @Test
    public void relateOrderChannel() throws Exception {
        relateOrderChannelService.relateOrderChannel(1);
    }
    @Test
    public void splitOrder() throws Exception {
        splitOrderService.splitOrderOp(1);
    }

    @Test
    public  void optimization() throws Exception {
        drawSortDataService.drawSortDataOp(1);
        distributeChannelService.distributeChannel(1);
        relateOrderChannelService.relateOrderChannel(1);
        splitOrderService.splitOrderOp(1);
    }
}
