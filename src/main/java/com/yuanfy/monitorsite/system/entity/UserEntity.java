package com.yuanfy.monitorsite.system.entity;

import javax.servlet.http.HttpServletRequest;


/**
 * @Description: 用户实体类
 * @author yuanfy
 * @date 2017年7月17日 下午4:13:21 
 * @version 1.0
 */
public class UserEntity {
    private Long id;
    
    private String name;

    private String password;
    
    private String email;
    
    private String phone;
    
    private String registerTime;
    
    private String lastLoginTime;
    
    public UserEntity getSessionUser(HttpServletRequest request){
        /*if (this.getUserId() == null){
            this.setUserId(System.currentTimeMillis());
            SessionUtil.setAttr(request, SessionUtil.SESSION_USER, this);
            return this;
        } 
        UserEntity sessionUser = SessionUtil.getUser(request);
        if (sessionUser.getUserId().equals(this.getUserId())) {
           return sessionUser; 
        }*/
        return null;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
}