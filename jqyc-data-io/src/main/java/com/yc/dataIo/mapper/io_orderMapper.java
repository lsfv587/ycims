package com.yc.dataIo.mapper;

import com.yc.dataIo.entity.io_order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface io_orderMapper {
    List<io_order> findAll();
}
