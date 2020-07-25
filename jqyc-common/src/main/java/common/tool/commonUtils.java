package common.tool;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: jqyc
 * @description: 通用工具类
 * @author: lsf
 * @create: 2020-07-16 08:32
 **/
public class commonUtils {

    /**
     * @Description:  将源集合对象转换成目标集合对象（字段类型和名称相同）
     * @param sources 源集合
     * @param tTargetClass 目标集合类型
     * @return:  目标集合对象
     * @Author: lsf
     * @Date: 2020/7/16
     */
    public static <TSource,TTarget> List<TTarget> copyList(List<TSource> sources,Class<TTarget> tTargetClass) throws IllegalAccessException, InstantiationException {
        List<TTarget> targets = new ArrayList<>(sources.size());
        for (TSource source : sources){
            TTarget target = tTargetClass.newInstance();
            BeanUtils.copyProperties(source,target);
            targets.add(target);
        }
        return  targets;
    }
    /**
     * @Description: 根据除数和被除数获取能整除的被除数的整数
     * @param sourceNum 源整数
     * @param dividend 被除数
     * @return:
     * @Author: lsf
     * @Date: 2020/7/17
     */
    public static Integer getNumTimes(Integer sourceNum,Integer dividend)
    {
        if (sourceNum == 0 || dividend == 0)
            return 0;
        return sourceNum - (sourceNum % dividend);
    }
}
