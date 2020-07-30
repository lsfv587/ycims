package com.yc.service.optimization;

import com.yc.vo.response.optimization.selectPathVo;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-30 10:05
 **/
public interface selectSortDataService {
    List<selectPathVo> getSelectPaths(Integer lineCode) throws Exception;
    void updateSelectedPath(List<String> ids);
}
