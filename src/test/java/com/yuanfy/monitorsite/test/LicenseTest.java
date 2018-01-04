package com.yuanfy.monitorsite.test;

import java.io.FileNotFoundException;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.junit.Test;

import com.yuanfy.monitorsite.common.util.alogrithm.RSAUtils;
import com.yuanfy.monitorsite.common.util.file.ResPathUtils;
import com.yuanfy.monitorsite.common.util.keystore.KeyStoreUtils;
import com.yuanfy.monitorsite.common.util.license.LicenseManager;
import com.yuanfy.monitorsite.test.entity.BlogLicense;
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
            String encryptInfo = LicenseManager.encryptWciInfo(entity);
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
    /**
     * @Description: 测试经过加密后再解密得到的信息是否一致
     * @throws FileNotFoundException
     * @author yuanfy
     * @date 2018年1月4日 下午2:00:35 
     * @version 1.0
     */
    @Test
    public void testEncrypt() throws FileNotFoundException{
        LicenseEntity entity = new LicenseEntity();
        entity.setNumber(123);
        entity.setOrgName("哈哈哈");
        entity.setOrgPhone("哈哈哈");
        entity.setVersion("哈哈哈");
        
        String encryptInfo = LicenseManager.encryptWciInfo(entity);
        System.out.println("加密后的信息：" + encryptInfo);
        
        LicenseEntity obj = (LicenseEntity)KeyStoreUtils.decryptWciInfo(encryptInfo, LicenseEntity.class.getCanonicalName());
        
        System.out.println(obj.getOrgName());
    }
    
    @Test
    public void testEncrypt2() throws FileNotFoundException{
        String info = "qwer去玩儿";
        PublicKey publicKey = LicenseManager.getPublicKey(ResPathUtils.getPublicKeyPath());
        PrivateKey privateKey = KeyStoreUtils.getPrivateKey();
        
        byte[] encrypt = RSAUtils.encrypt(privateKey, info);
        
        String decrypt = RSAUtils.decrypt(publicKey, encrypt);
        
        System.out.println(decrypt);
    }
    /**
     * @Description: 验证解析wlic文件
     * @throws FileNotFoundException
     * @author yuanfy
     * @date 2018年1月4日 下午3:52:17 
     * @version 1.0
     */
    @Test
    public void testBlogLicense(){
        BlogLicense bl = LicenseManager.getBlogLicense();
        System.out.println(bl.getWci().getOrgName());
    }
}
