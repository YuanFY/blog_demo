package com.yuanfy.monitorsite.commons.utils.keystore;

import org.junit.Test;

import com.yuanfy.monitorsite.common.util.keystore.KeyStoreUtils;

/**
 * @Description: 工具类测试
 * @author yuanfy
 * @date 2017年12月29日 下午5:07:16 
 * @version 1.0
 */
public class KeyStoreUtilsTest {

    /**
     * @Description: 测试是否能生成密钥库
     * @author yuanfy
     * @date 2018年1月2日 上午10:39:29 
     * @version 1.0
     */
    @Test
    public void testKeyStore(){
        KeyStoreUtils.generateKeyPair();
    }
    
    /**
     * @Description: 测试是否获取公钥
     * @author yuanfy
     * @date 2018年1月2日 上午10:39:29 
     * @version 1.0
     */
    @Test
    public void testGetPublicKey(){
        System.out.println(KeyStoreUtils.getPublicKey());
    }
    
    /**
     * @Description: 测试是否创建公钥文件
     * @author yuanfy
     * @date 2018年1月2日 上午10:39:29 
     * @version 1.0
     */
    @Test
    public void testCreatePublicKeyFile(){
        KeyStoreUtils.createPublicKeyFile();
    }
    
    /**
     * @Description: 测试是否能获取私钥
     * @author yuanfy
     * @date 2018年1月2日 上午10:39:29 
     * @version 1.0
     */
    @Test
    public void testGetPrivateKey(){
        System.out.println(KeyStoreUtils.getPrivateKey());
    }
}
