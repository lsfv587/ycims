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
    private String type;
    private String message;
    private Object data;

    public static ResponseResult success(){
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        result.setType("success");
        result.setMessage("success");
        result.setData(null);
        return result;
    }

    public static ResponseResult success(Object data)
    {
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        result.setType("success");
        result.setMessage("success");
        result.setData(data);
        return result;
    }
    public static ResponseResult success(String msg,Object data)
    {
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        result.setType("success");
        result.setMessage(msg);
        result.setData(data);
        return result;
    }
    public static ResponseResult fail(String msg)
    {
        ResponseResult result = new ResponseResult();
        result.setCode(404);
        result.setType("error");
        result.setMessage(msg);
        result.setData(null);
        return result;
    }
    public static ResponseResult fail(Integer code,String msg)
    {
        ResponseResult result = new ResponseResult();
        result.setCode(code);
        result.setType("error");
        result.setMessage(msg);
        result.setData(null);
        return result;
    }
}

