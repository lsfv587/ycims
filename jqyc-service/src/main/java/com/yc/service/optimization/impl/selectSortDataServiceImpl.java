package com.yc.service.optimization.impl;

import com.yc.dao.entity.dl.dl_path;
import com.yc.dao.mapper.dl.dl_pathMapper;
import com.yc.service.optimization.selectSortDataService;
import com.yc.vo.response.optimization.selectPathVo;
import common.tool.commonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-30 10:13
 **/
@Service
public class selectSortDataServiceImpl implements selectSortDataService {
    @Resource
    private dl_pathMapper dlPathMapper;
    @Override
    public List<selectPathVo> getSelectPaths(Integer lineCode) throws Exception {
        List<dl_path> dlPaths = dlPathMapper.getDlPaths(lineCode);
        List<selectPathVo> selectPathVos = commonUtils.copyList(dlPaths,selectPathVo.class);
        return selectPathVos;
    }

    @Transactional
    @Override
    public void updateSelectedPath(List<String> ids) {
        dlPathMapper.updateSelectedPath(ids);
    }
}
