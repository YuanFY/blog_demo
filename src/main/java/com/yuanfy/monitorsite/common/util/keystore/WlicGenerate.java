package com.yuanfy.monitorsite.common.util.keystore;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.PrivateKey;

import com.yuanfy.monitorsite.common.util.Base64Utils;
import com.yuanfy.monitorsite.common.util.alogrithm.RSAUtils;
import com.yuanfy.monitorsite.common.util.file.StreamUtils;
import com.yuanfy.monitorsite.common.util.xml.XMLConvertUtils;

/**
 * @Description:wlic生成类 
 * @author yuanfy
 * @date 2018年1月3日 下午3:12:57 
 * @version 1.0
 */
public class WlicGenerate {
    
    public static LicenseDto loadWci(String wciFilePath) {
        //先读取文件内容
        ByteArrayOutputStream bos = null;
        DataInputStream dis = null;
        try {
            bos = new ByteArrayOutputStream();
            dis = new DataInputStream(new FileInputStream(wciFilePath));
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = dis.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            String encryptInfo = new String(bos.toByteArray(), "utf-8");
            //2、base64解码
            byte[] data = Base64Utils.decode(encryptInfo);
            //3、使用私钥解密
            PrivateKey privateKey = KeyStoreUtils.getPrivateKey();
            String xmlStr = RSAUtils.decrypt(privateKey, data);
            //4 转化成bean
            LicenseDto entity = (LicenseDto)XMLConvertUtils.xmlConvertObject(xmlStr, LicenseDto.class.getCanonicalName());
            return entity;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            StreamUtils.close(dis);
            StreamUtils.close(bos);
        }
        return null;
    }
}
