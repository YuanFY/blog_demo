package com.yuanfy.monitorsite.commons.utils.keystore;

import org.junit.Test;

import com.yuanfy.monitorsite.common.util.keystore.KeyStoreUtils;

/**
 * @Description: 工具类测试
 * @author yuanfy
 * @date 2017年12月29日 下午5:07:16 
 * @version 6.5
 */
public class UtilTest {

    @Test
    public void testKeyStore(){
        KeyStoreUtils.generateKeyPair();
    }
}
