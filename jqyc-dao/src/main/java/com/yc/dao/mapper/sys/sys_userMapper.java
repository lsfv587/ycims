package com.yc.dao.mapper.sys;

import com.yc.dao.entity.sys.sys_user;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-27 10:44
 **/
@Mapper
@Repository
public interface sys_userMapper {
    sys_user getUserByName(String userName);
}
