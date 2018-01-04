package com.yuanfy.monitorsite.test.entity;


/**
 * @Description: license实体类
 * @author yuanfy
 * @date 2017年12月28日 上午11:35:32 
 * @version 1.0
 */
public class LicenseEntity {
    
    private String orgName;
    
    private String orgPhone;
    
    private String version;
    
    private Integer number;
    
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgPhone() {
        return orgPhone;
    }

    public void setOrgPhone(String orgPhone) {
        this.orgPhone = orgPhone;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "LicenseEnriry [orgName=" +orgName+ ", orgPhone=" + orgPhone
                + ", version=" + version + ", number=" + number + "]";
    }
    
}
