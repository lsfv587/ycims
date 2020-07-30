package com.yc.dao.mapper.sys;

import com.yc.dao.entity.sys.sys_menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-27 10:34
 **/
@Mapper
@Repository
public interface sys_menuMapper {
    List<sys_menu> getMenuByUsername(String userName);
}
