package common.web;

import lombok.Data;

/**
 * @program: jqyc
 * @description: rest api 返回数据类
 * @author: lsf
 * @create: 2020-07-14 13:45
 **/
@Data
public class ResponseResult {
    private Integer code;
    private String msg;
    private Object data;

    public static ResponseResult success(Object data)
    {
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    public static ResponseResult fail(String msg)
    {
        ResponseResult result = new ResponseResult();
        result.setCode(404);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}

