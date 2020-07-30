package com.yc.shiro;

import com.yc.service.login.loginService;
import lombok.SneakyThrows;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * @program: springboot_shior
 * @description: 用户密码session验证
 * @author: lsf
 * @create: 2020-06-15 09:20
 **/
public class UserPasswordRealm extends AuthorizingRealm {
    @Resource
    private loginService loginService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
        authInfo.addStringPermission("user");
        return authInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) auth;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        try{
            loginService.login(username,password);
        }
        catch(Exception e)
        {
            throw new AuthenticationException(e.getMessage());
        }
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
