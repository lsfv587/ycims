package com.yc.controller.optimization;

import com.yc.service.optimization.*;
import com.yc.vo.request.optimization.opRequestVo;
import com.yc.vo.response.optimization.selectPathVo;
import common.web.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-27 08:32
 **/
@RestController
@RequestMapping("/op")
public class optimizationController{
    @Resource
    private downDataService downService;
    @Resource
    private selectSortDataService selectService;
    @Resource
    private drawSortDataService drawService;
    @Resource
    private relateOrderChannelService relateServcie;
    @Resource
    private splitOrderService splitService;

    @PostMapping("/downData")
    public ResponseResult downData(@RequestBody opRequestVo vo)
    {
        try{
            downService.downData(vo.getOrderDate(),vo.getLineCode());
            return ResponseResult.success();
        }catch (Exception e){
            return ResponseResult.fail(e.getMessage());
        }
    }
    @GetMapping("/selectPath")
    public ResponseResult selectPath(String orderDate,Integer lineCode){
        try{
            List<selectPathVo> vos = selectService.getSelectPaths(lineCode);
            return ResponseResult.success(vos);
        }catch (Exception e){
            return ResponseResult.fail(e.getMessage());
        }
    }
    @PostMapping("/selectPath")
    public ResponseResult updateSelectPath(@RequestBody opRequestVo vo)
    {
        try{
            List<String> list = Arrays.asList(vo.getPathIds());
            selectService.updateSelectedPath(list);
            return ResponseResult.success();
        }catch (Exception e){
            return ResponseResult.fail(e.getMessage());
        }
    }
    @PostMapping("/optimization")
    public ResponseResult optimization(@RequestBody opRequestVo vo){
        try{
            System.out.println(vo);
            drawService.drawSortDataOp(vo.getLineCode());
            relateServcie.relateOrderChannel(vo.getLineCode());
            splitService.splitOrderOp(vo.getLineCode());
            return ResponseResult.success();
        }catch (Exception e){
            return ResponseResult.fail(e.getMessage());
        }
    }
}
