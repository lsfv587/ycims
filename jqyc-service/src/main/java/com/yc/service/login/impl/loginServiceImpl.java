package com.yc.service.login.impl;

import com.yc.dao.entity.sys.sys_menu;
import com.yc.dao.entity.sys.sys_user;
import com.yc.dao.mapper.sys.sys_menuMapper;
import com.yc.dao.mapper.sys.sys_userMapper;
import com.yc.service.login.loginService;
import com.yc.tool.MenuGenerateTool;
import com.yc.vo.inner.RouterVo;
import com.yc.vo.response.loginResponseVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.security.auth.login.AccountException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-27 11:07
 **/
@Service
public class loginServiceImpl implements loginService {
    @Resource
    private sys_userMapper userMapper;
    @Resource
    private sys_menuMapper menuMapper;
    @Override
    public void login(String userName, String password) throws Exception {
        sys_user user = userMapper.getUserByName(userName);
        if (user == null)
        {
            throw new Exception("用户不存在!");
        }
        if(user.getIsLock() == 1)
        {
            throw new Exception("用户被锁定!");
        }
        if (!user.getPassword().equals(password))
        {
            throw new Exception("密码错误!");
        }
    }

    @Override
    public loginResponseVo getLoginVoByUsername(String userName) {
        loginResponseVo loginVo = new loginResponseVo();
        List<sys_menu> menus = menuMapper.getMenuByUsername(userName);
        loginVo.setMenus(MenuGenerateTool.GenerateMenuVos(menus));
        List<RouterVo> routerVos = new ArrayList<>();
        routerVos.add(MenuGenerateTool.GenerateRouterVos(menus));
        loginVo.setRoutes(routerVos);
        loginVo.setToken(userName);
        return loginVo;
    }
}
