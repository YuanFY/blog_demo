package com.yuanfy.monitorsite.common.util.alogrithm;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: res算法工具类
 * @author yuanfy
 * @date 2018年1月3日 上午10:44:12 
 * @version 1.0
 */
public class RSAUtils {
    private static Logger log = LoggerFactory.getLogger(RSAUtils.class);
    
    public final static String ALOGRITHM_RSA = "RSA";
    
    /**
     * @Description: 根据私/公钥使用rsa加密，javax.crypto.Cipher类提供加密和解密功能，该类是JCE框架的核心。
     * @param key
     * @param data
     * @author yuanfy
     * @date 2018年1月3日 上午10:50:04 
     * @version 1.0
     */
    public static byte[] encrypt(Key key, String data) {
        try {
            Cipher cipher = Cipher.getInstance(ALOGRITHM_RSA);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //如果是分段加密，则需要使用ByteArrayOutputStream 来获取数据  
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] dataArr = data.getBytes();
            for(int i=0 ;i < dataArr.length; i+=102){
                byte[] b = null ;
                if(i+102 > dataArr.length){
                    b = getByteArr(dataArr, i, dataArr.length-i);
                }else{
                    b = getByteArr(dataArr, i, 102);
                }
                byte[] encryptData = cipher.doFinal(b);
                baos.write(encryptData, 0, encryptData.length);
            }
            return baos.toByteArray();
        }
        catch (NoSuchAlgorithmException e) {
            log.error("根据公钥使用RSA加密时，获取cipher报错找不到RSA算法：", e);
        }
        catch (NoSuchPaddingException e) {
            log.error("根据公钥使用RSA加密时，获取cipher实例报错", e);
        }
        catch (InvalidKeyException e) {
            log.error("根据公钥使用RSA加密时，初始化cipher报错", e);
        }
        catch (Exception e) {
            log.error("根据公钥使用RSA加密时，doFinal()加密报错", e);
        }
        return null;
    }
    
    /**
     * @Description: 使用私/公钥解密
     * @author yuanfy
     * @date 2018年1月4日 下午3:30:00 
     * @version 1.0
     */
    public static String decrypt(Key key, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(ALOGRITHM_RSA);
            cipher.init(Cipher.DECRYPT_MODE, key);
            //解密需要分段，最大长度为245 这个分段长度无需和加密时的一致。
            ByteArrayOutputStream ba = new ByteArrayOutputStream();
            for(int i=0; i < data.length; i+=128 ){
                byte[] b = null ;
                if(i+128 > data.length){
                    b = getByteArr(data, i, data.length-i);
                }else{
                    b = getByteArr(data, i, 128);
                }
                ba.write(cipher.doFinal(b));
            }
            return new String (ba.toByteArray());
        }
        catch (NoSuchAlgorithmException e) {
            log.error("根据私钥使用RSA解密时，获取cipher报错找不到RSA算法：", e);
        }
        catch (NoSuchPaddingException e) {
            log.error("根据私钥使用RSA解密时，获取cipher实例报错", e);
        }
        catch (InvalidKeyException e) {
            log.error("根据私钥使用RSA解密时，初始化cipher报错", e);
        }
        catch (Exception e) {
            log.error("根据私钥使用RSA解密时，doFinal()私密报错", e);
        }
        return null;
    }
    
    private static byte[] getByteArr(byte[] inputData, int i, int len) {
        byte[] a = new byte[len];
        int k =0;
        for(int j=i;j<i+len;j++){
            a[k++] = inputData[j];
        }
        return a;
    }
}
