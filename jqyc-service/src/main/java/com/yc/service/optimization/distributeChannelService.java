package com.yc.service.optimization;


import com.yc.dao.entity.op.op_channel;
import com.yc.dao.entity.op.op_productLoad;

import java.util.List;

public interface distributeChannelService {
    void distributeChannel(Integer lineCode);
    void insertData(Integer lineCode,List<op_channel> channels,List<op_productLoad> productLoads);
}
