package com.yuanfy.monitorsite.common.util.license;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.PublicKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuanfy.monitorsite.common.util.Base64Utils;
import com.yuanfy.monitorsite.common.util.StringUtils;
import com.yuanfy.monitorsite.common.util.alogrithm.RSAUtils;
import com.yuanfy.monitorsite.common.util.alogrithm.SignatureUtils;
import com.yuanfy.monitorsite.common.util.file.FileUtils;
import com.yuanfy.monitorsite.common.util.file.ResPathUtils;
import com.yuanfy.monitorsite.common.util.file.StreamUtils;
import com.yuanfy.monitorsite.common.util.xml.XMLConvertUtils;
import com.yuanfy.monitorsite.test.entity.BlogLicense;
import com.yuanfy.monitorsite.test.entity.LicenseEntity;

/**
 * @Description: license管理类：
 * @author yuanfy
 * @date 2018年1月2日 下午4:34:38 
 * @version 1.0
 */
public class LicenseManager {
    
    private static Logger log = LoggerFactory.getLogger(LicenseManager.class);
    /**
     * @Description: 根据公钥文件路径获取公钥对象
     * @param publicKeyPath
     * @author yuanfy
     * @date 2018年1月3日 上午10:11:41 
     * @version 1.0
     * @throws FileNotFoundException 
     */
    public static PublicKey getPublicKey(String publicKeyPath) throws FileNotFoundException {
        //因为写入公钥文件采用的是ObjectOutputStream所以就采用对应的ObjectInputStream
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(publicKeyPath));
            PublicKey publicKey = (PublicKey)ois.readObject();
            return publicKey;
        }
        catch (FileNotFoundException e) {
            log.error("获取公钥对象时，找不到公钥文件:", e);
            //这个是可以提示用户的。
            throw new FileNotFoundException("检测到公钥(publickey)文件不存在");
        }
        catch (IOException e) {
            log.error("获取公钥对象时，读取公钥文件io异常:", e);
        }
        catch (ClassNotFoundException e) {
            log.error("获取公钥对象时，类转换异常:", e);
        }
        finally{
            StreamUtils.close(ois);
        }
        return null;
    }
    
    /**
     * @Description: 加密wci文件信息
     * @author yuanfy
     * @date 2018年1月3日 上午11:19:57 
     * @version 1.0
     * @throws FileNotFoundException 
     */
    public static String encryptWciInfo(LicenseEntity entity) throws FileNotFoundException {
        //1、先将内容变成xml类型的字符串，便于加密（既保存了bean结构的目录，有获取了字符串）
        String xmlStr = XMLConvertUtils.objectConvertXMl(entity);
        if (StringUtils.isEmpty(xmlStr)) {
            return null;
        }
        //2、然后再使用公钥进行加密
        PublicKey publicKey = getPublicKey(ResPathUtils.getPublicKeyPath());
        if (publicKey == null) {
            return null;
        }
        byte[] encryptData = RSAUtils.encrypt(publicKey, xmlStr);
        if (encryptData == null) {
            return null;
        }
        //3 使用base64进行编码
        return Base64Utils.encode(encryptData);
    }
    /**
     * @Description: 解密wlic文件信息并获取blogLicense对象
     * @author yuanfy
     * @date 2018年1月4日 下午4:40:46 
     * @version 6.5
     */
    public static BlogLicense getBlogLicense(){
        try {
            //1、获取wlic文件内容后解码
            String content = FileUtils.getFileContentByte(ResPathUtils.getWlicPath());
            byte[] decode = Base64Utils.decode(content);
            //2 解密
            PublicKey publicKey = getPublicKey(ResPathUtils.getPublicKeyPath());
            String xmlStr = RSAUtils.decrypt(publicKey, decode);
            //3 转换bean对象
            BlogLicense bl = (BlogLicense)XMLConvertUtils.xmlConvertObject(xmlStr, BlogLicense.class.getCanonicalName());
            //4 验证签名
            if (SignatureUtils.verfiy(publicKey, bl.getWci().toString(), bl.getSignature())) {
                return bl;
            }
        }
        catch (IOException e) {
            log.error("获取wlic文件内容失败：", e);
        }
        return null;
    }
}
