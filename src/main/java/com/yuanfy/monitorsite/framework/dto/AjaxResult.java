package com.yuanfy.monitorsite.framework.dto;

/**
 * @Description: 异步请求返回结果 信息类
 * @author yuanfy
 * @date 2017年7月17日 下午4:15:58 
 * @version 1.0
 */
public class AjaxResult {
    //表示是否是预期结果，1：是，0：否 （通常情况下1表示正常，其它情况情具体设计）
    private int error;
    //表示错误信息
    private String msg;
    //返回结果
    private Object data;
    
    public AjaxResult(){}
    
    public AjaxResult(int error) {
        this.error = error;
    }
    
    public int getError() {
        return error;
    }
    public void setError(int error) {
        this.error = error;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}
