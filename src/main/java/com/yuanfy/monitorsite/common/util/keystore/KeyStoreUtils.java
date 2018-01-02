package com.yuanfy.monitorsite.common.util.keystore;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

import org.apache.log4j.Logger;

import com.yuanfy.monitorsite.common.util.file.ResPathUtils;
import com.yuanfy.monitorsite.common.util.file.StreamUtils;


/**
 * @Description: 密钥库工具类：用于生成私钥和公钥，可以作为一个单独的项目导出。
 * @author yuanfy
 * @date 2017年12月28日 下午7:43:46 
 * @version 1.0
 */
public class KeyStoreUtils {
    
    private static Logger log = Logger.getLogger(KeyStoreUtils.class);
    /**
     * 设置别名，默认为mykey
     */
    private final static String ALIAS = "blog1.0.0";
    /**
     * 指定别名条目的密码 
     */
    private final static String KEY_PASS = "blogpassw0rd520";
    /**
     * 指定密钥库的密码 
     */
    private final static String STORAGE_PASS = "blogpassw0rd521";
    /**
     * 存放密钥对文件
     */
    private final static String STORAGE_FILE = "blog.jks";
    
    /**
     * @Description: 生成密钥库
     * @author yuanfy
     * @date 2018年1月2日 上午9:25:28 
     * @version 1.0
     */
    public static void generateKeyPair() {
        String command = createCommand(getKeystorePath());
        Process process = null;
        try {
            if (ResPathUtils.isWindows()) {
                process = Runtime.getRuntime().exec("cmd /c " + command);
            } else {
                String[] cmds = {"/bin/sh", "-c", command};
                process = Runtime.getRuntime().exec(cmds);
            }
            process.waitFor();//等待执行完毕
            //读取管道信息
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("gb2312")));
            String line = null;
            StringBuffer msg = new StringBuffer();
            boolean isError = false;
            while ((line = reader.readLine()) != null) {
                msg.append(line);
                if (line.contains("错误") || line.contains("error")) {
                    isError = true;
                }
            }
            if (isError) {
                log.error("密钥库生成失败:" + msg);
            } else {
                log.info("密钥库生成成功");
            }
        }
        catch (IOException e) {
            log.error("密钥库生成失败，命令执行有问题：", e);
        }
        catch (InterruptedException e) {
            log.error("密钥库生成失败，命令执行线程中断异常：", e);
        }
    }
    /**
     * @Description: 创建密钥库命令
     * @param filePath 存放密钥库文件路径
     * @author yuanfy
     * @date 2017年12月29日 下午4:31:45 
     * @version 1.0
     */
    private static String createCommand(String filePath) {
        StringBuffer command = new StringBuffer();
        command.append("keytool -genkeypair -dname \"CN=YuanFY, OU=YuanFY, O=YuanFY, L=GZ, ST=GD, C=CN\" -alias ")
        .append(ALIAS).append(" -keyalg RSA -keystore ").append(filePath).append(" -keypass ")
        .append(KEY_PASS).append(" -storepass ").append(STORAGE_PASS).append(" -validity 100000");
        return command.toString();
    }
    /**
     * @Description: 获取密钥库路径
     * @author yuanfy
     * @date 2018年1月2日 上午9:48:11 
     * @version 1.0
     */
    private static String getKeystorePath() {
        return ResPathUtils.getKeyStorePath() + ResPathUtils.fileSeparator + STORAGE_FILE;
    }
    /**
     * @Description: 创建公钥文件（包括内容）
     * @author yuanfy
     * @date 2018年1月2日 上午9:45:01 
     * @version 1.0
     */
    public static void createPublicKeyFile (){
        //1、先根据密钥库获取公钥
        PublicKey publicKey = getPublicKey();
        ObjectOutputStream oos = null;
        //2、将公钥内容写入公钥文件。
        try {
            oos = new ObjectOutputStream(new FileOutputStream(ResPathUtils.getKeyStorePath() + ResPathUtils.fileSeparator + "publickey"));
            oos.writeObject(publicKey);
        }
        catch (FileNotFoundException e) {
            log.error("创建公钥文件时，公钥文件路径找不到：", e);
            return ;
        }
        catch (IOException e) {
            log.error("创建公钥文件时，io异常：", e);
            return ;
        }
        finally {
            StreamUtils.close(oos);
        }
        log.info("创建并写入publickey成功");
    }
    /**
     * @Description: 获取公钥
     * @author yuanfy
     * @date 2018年1月2日 上午9:55:25 
     * @version 1.0
     */
    public static PublicKey getPublicKey() {
        //1、先获取密钥库对象
        KeyStore keyStore = getKeyStore();
        if (keyStore == null) {
            return null;
        }
        try {
            //2、提取证书
            Certificate certificate = keyStore.getCertificate(ALIAS);
            //3、根据证书获取公钥
            return certificate.getPublicKey();
        }
        catch (KeyStoreException e) {
            log.error("获取公钥时，提取证书失败：", e);
        }
        return null;
    }
    
    /**
     * @Description: 获取私钥
     * @author yuanfy
     * @date 2018年1月2日 上午9:55:25 
     * @version 1.0
     */
    public static PrivateKey getPrivateKey() {
        //1、先获取密钥库对象
        KeyStore keyStore = getKeyStore();
        if (keyStore == null) {
            return null;
        }
        //2、根据密钥库对象获取私钥,并返回
        try {
            //注意这里使用的密码，应该是密钥的密码，而不是密钥库的密码
            PrivateKey privateKey = (PrivateKey)keyStore.getKey(ALIAS, KEY_PASS.toCharArray());
            return privateKey;
        }
        catch (Exception e) {
            log.error("获取私钥失败：", e);
        }
        return null;
    }
    /**
     * @Description: 从密钥库中获取密钥库对象
     * @return KeyStore
     * @author yuanfy
     * @date 2018年1月2日 上午10:15:01 
     * @version 1.0
     */
    public static KeyStore getKeyStore(){
        KeyStore keyStore = null;
        FileInputStream fis = null;
        try {
            //1、先获取密钥库实例
            keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            //2、在加载密钥库之前，要获取密钥库文件流
            fis = new FileInputStream(getKeystorePath());
            //3、加载密钥库  要是用密钥库的密码进行访问加载
            keyStore.load(fis, STORAGE_PASS.toCharArray());
            return keyStore;
        }
        catch (KeyStoreException e) {
            log.error("获取密钥库对象实例失败：", e);
        }
        catch (FileNotFoundException e) {
            log.error("获取密钥库对象时，找不到对应的密钥库文件：", e);
        }
        catch (Exception e) {
            log.error("获取密钥库对象时，加载解析密钥库文件失败：", e);
        }
        finally {
            StreamUtils.close(fis);
        }
        return null;
    }
}
