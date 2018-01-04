package com.yuanfy.monitorsite.test.entity;

import java.util.Date;

/**
 * @Description: license信息包装类
 * @author yuanfy
 * @date 2018年1月4日 下午2:38:46 
 * @version 6.5
 */
public class BlogLicense {

    private Date createTime; //创建时间

    private String signature; //签名内容

    private LicenseEntity wci; //wci文件内容对应的实体bean对象

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public LicenseEntity getWci() {
        return wci;
    }

    public void setWci(LicenseEntity wci) {
        this.wci = wci;
    }
}
