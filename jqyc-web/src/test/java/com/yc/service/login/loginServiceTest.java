package com.yc.service.login;

import com.yc.dao.entity.sys.sys_menu;
import com.yc.dao.entity.sys.sys_user;
import com.yc.dao.mapper.sys.sys_menuMapper;
import com.yc.dao.mapper.sys.sys_userMapper;
import com.yc.vo.response.loginResponseVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-27 11:50
 **/
@SpringBootTest
public class loginServiceTest {
    @Resource
    private loginService loginService;
    @Resource
    private sys_menuMapper sysMenuMapper;
    @Resource
    private sys_userMapper sysUserMapper;
    @Test
    public void login()
    {
        List<sys_menu> menus = sysMenuMapper.getMenuByUsername("admin");
        System.out.println(menus);
        loginResponseVo loginResponseVos = loginService.getLoginVoByUsername("admin");
        System.out.println(loginResponseVos);
    }
    @Test
    public  void getUserByName()
    {
        sys_user user = sysUserMapper.getUserByName("admin");
        System.out.println(user);
    }
}
