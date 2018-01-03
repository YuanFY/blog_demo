package com.yuanfy.monitorsite.test;

import java.io.FileNotFoundException;

import org.junit.Test;

import com.yuanfy.monitorsite.common.util.license.LicenseManager;
import com.yuanfy.monitorsite.test.entity.LicenseEntity;

/**
 * @Description: 有关license测试类
 * @author yuanfy
 * @date 2018年1月3日 上午11:46:37 
 * @version 1.0
 */
public class LicenseTest {
    
    @Test
    public void testCheckLicense(){
        LicenseEntity entity = new LicenseEntity();
        entity.setNumber(123);
        entity.setOrgName("中文哈哈");
        entity.setOrgPhone("12341234123");
        entity.setVersion("增强版");
        
        //加密许可信息
        try {
            String encryptInfo = LicenseManager.encryptLicenseInfo(entity);
            if (encryptInfo == null) {
                System.out.println("检测下次wci文件失败，请重试");
            }else {
                System.out.println("加密后的信息：" + encryptInfo);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
