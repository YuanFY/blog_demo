package com.yuanfy.monitorsite.system.entity;

/**
 * @Description: 用户实体类
 * @author yuanfy
 * @date 2017年7月17日 下午4:13:21 
 * @version 1.0
 */
public class UserEntity {
    private Long userId;
    
    private String userName;

    public UserEntity(){}
    
    public UserEntity(String userName) {
        this.userId = System.currentTimeMillis();
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
