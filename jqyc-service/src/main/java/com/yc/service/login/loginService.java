package com.yc.service.login;

import com.yc.vo.response.loginResponseVo;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-27 11:03
 **/
public interface loginService {
    void login(String userName,String password) throws Exception;
    loginResponseVo getLoginVoByUsername(String userName);
}
