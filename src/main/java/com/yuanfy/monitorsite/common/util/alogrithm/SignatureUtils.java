package com.yuanfy.monitorsite.common.util.alogrithm;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuanfy.monitorsite.common.util.Base64Utils;

/**
 * @Description: 签名工具类
 * @author yuanfy
 * @date 2018年1月4日 下午2:50:42 
 * @version 1.0
 */
public class SignatureUtils {
    private static Logger log = LoggerFactory.getLogger(SignatureUtils.class);

    /**
     * @Description: 使用私钥进行签名
     * @param privateKey 私钥
     * @param content 待签名内容
     * @return 返回签名内容
     * @author yuanfy
     * @date 2018年1月4日 下午3:05:54 
     * @version 1.0
     */
    public static String sign(PrivateKey privateKey, String content){
        try {
            //1、获取实例
            Signature signature = Signature.getInstance("MD5withRSA");
            //2、使用私钥初始化签名
            signature.initSign(privateKey);
            //3、更新内容
            signature.update(content.getBytes());
            //4、获取签名内容
            String signed = Base64Utils.encode(signature.sign());
            return signed;
        }
        catch (NoSuchAlgorithmException e) {
            log.error("进行签名时，获取Signature实例报错：找不到算法MD5withRSA.", e);
        }
        catch (InvalidKeyException e) {
            log.error("进行签名时，使用私钥初始化签名报错：", e);
        }
        catch (SignatureException e) {
            log.error("进行签名时，更新内容报错：", e);
        }
        return null;
    }
    
    /**
     * @Description: 使用公钥进行签名验证
     * @param privateKey 私钥
     * @param content 待签名内容
     * @param signContent 已签名内容
     * @return 返回boolean
     * @author yuanfy
     * @date 2018年1月4日 下午3:05:54 
     * @version 1.0
     */
    public static boolean verfiy(PublicKey publicKey, String content, String signContent){
        try {
            //1、获取实例
            Signature signature = Signature.getInstance("MD5withRSA");
            //2、使用公钥初始化签名
            signature.initVerify(publicKey);
            //3、更新内容
            signature.update(content.getBytes());
            //4、验证签名内容
            byte[] signed = Base64Utils.decode(signContent);
            return signature.verify(signed);
        }
        catch (NoSuchAlgorithmException e) {
            log.error("进行签名验证时，获取Signature实例报错：找不到算法MD5withRSA.", e);
        }
        catch (InvalidKeyException e) {
            log.error("进行签名验证时，使用公钥初始化签名报错：", e);
        }
        catch (SignatureException e) {
            log.error("进行签名验证时，更新内容报错：", e);
        }
        return false;
    }
}
