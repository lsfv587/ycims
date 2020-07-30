package com.yc.controller;

import com.yc.service.login.loginService;
import com.yc.vo.request.loginRequestVo;
import com.yc.vo.response.loginResponseVo;
import common.web.ResponseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-27 09:24
 **/
@RestController
@RequestMapping("/login")
public class loginController {
    @Resource
    private loginService loginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody loginRequestVo vo){
        try{
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken();
            token.setUsername(vo.getUserName());
            token.setPassword(vo.getPassword().toCharArray());
            subject.login(token);
            loginResponseVo loginResponseVo = loginService.getLoginVoByUsername(vo.getUserName());
            return ResponseResult.success(loginResponseVo);
        }catch (Exception e)
        {
            return ResponseResult.fail(e.getMessage());
        }
    }
}
