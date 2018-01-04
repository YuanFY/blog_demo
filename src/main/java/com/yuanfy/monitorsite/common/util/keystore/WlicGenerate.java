package com.yuanfy.monitorsite.common.util.keystore;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import com.yuanfy.monitorsite.common.util.file.StreamUtils;
import com.yuanfy.monitorsite.test.entity.LicenseEntity;

/**
 * @Description:wlic生成类 
 * @author yuanfy
 * @date 2018年1月3日 下午3:12:57 
 * @version 1.0
 */
public class WlicGenerate {
    
    public static LicenseEntity loadWci(String wciFilePath) {
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
            String encryptInfo = new String(bos.toByteArray());
            return (LicenseEntity)KeyStoreUtils.decryptWciInfo(encryptInfo, LicenseEntity.class.getCanonicalName());
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
