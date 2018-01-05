package com.yuanfy.monitorsite.exception;

/**
 * @Description: 自定义异常
 * @author yuanfy
 * @date 2018年1月5日 下午4:59:12 
 * @version 1.0
 */
public class AppException extends Exception{

    private static final long serialVersionUID = 1L;

    public AppException(String message) {
        super(message);
    }
    
    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public AppException(Throwable cause) {
        super(cause);
    }
}
