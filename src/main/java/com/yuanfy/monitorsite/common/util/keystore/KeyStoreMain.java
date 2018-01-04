package com.yuanfy.monitorsite.common.util.keystore;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import com.yuanfy.monitorsite.common.util.Base64Utils;
import com.yuanfy.monitorsite.common.util.alogrithm.RSAUtils;
import com.yuanfy.monitorsite.common.util.alogrithm.SignatureUtils;
import com.yuanfy.monitorsite.common.util.xml.XMLConvertUtils;
import com.yuanfy.monitorsite.test.entity.BlogLicense;
import com.yuanfy.monitorsite.test.entity.LicenseEntity;

/**
 * @Description: 密钥库主类：
 * @author yuanfy
 * @date 2017年12月28日 下午7:43:46 
 * @version 1.0
 */
public class KeyStoreMain {
    
    public static void main(String[] args) throws IOException {
        while (true) {
            printHead();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String command;
            command = br.readLine();
            if ("1".equals(command)) {
                createNewLicense(false);//正式
            }else if("2".equals(command)){
                createNewLicense(true);//试用
            }else if("3".equals(command)){
                createKeyStoreFile();
            }
        }
    }
    //生成wlic文件
    public static void createNewLicense(boolean isTryUse) throws IOException{
        String wciFilePath = getFilePath("wci");
        //1、先解密还原成bean对象
        LicenseEntity entity = WlicGenerate.loadWci(wciFilePath);
        //2、设置一些信息后进行包装
        //.....
        //进行包装
        BlogLicense license = new BlogLicense();
        license.setWci(entity);
        license.setCreateTime(new Date());
        //设置签名信息
        String signed = SignatureUtils.sign(KeyStoreUtils.getPrivateKey(), entity.toString());
        license.setSignature(signed);
        //3、进行加密保存
        String xmlStr = XMLConvertUtils.objectConvertXMl(license);
        byte[] encrypt = RSAUtils.encrypt(KeyStoreUtils.getPrivateKey(), xmlStr);
        String encryptStr = Base64Utils.encode(encrypt);
        //写入文件
        String path = wciFilePath.replace(".wci", ".wlic");
        DataOutputStream out = new DataOutputStream(new FileOutputStream(path));
        out.write(encryptStr.getBytes());
        out.close();
    }
    /**
     * @Description: 创建新的密钥文件，并成导出公钥
     * @author yuanfy
     * @date 2018年1月3日 下午3:05:32 
     * @version 1.0
     */
    public static void createKeyStoreFile()  {
        KeyStoreUtils.generateKeyPair();
        KeyStoreUtils.createPublicKeyFile();
    }
    
    private static String getFilePath(String fileType) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\n请输入生成的"+fileType+"文件路径：");
        String filePath = br.readLine();
        while (notFileType(filePath,fileType)) {
            System.out.println("无效文件!请重新输入"+fileType+"文件路径：");
            filePath = br.readLine();
        }
        return filePath;
    }
    
    private static boolean notFileType(String filePath,String suffix) {
        File file = new File(filePath);
        if("wci".equals(suffix)){
            if(!file.isFile()){
                return true;
            }
        }
        if(file.getName().endsWith(suffix)){
            return false;
        }
        return true;
    }
    
    private static void printHead() {
        System.out.println("*********************************************************************");

        System.out.println("\n\t\tblog license service version 1.0\n");

        System.out.println("*********************************************************************\n");

        System.out.println("1.生成正式许可证文件\n");

        System.out.println("2.生成试用许可证文件\n");

        System.out.println("3.创建新的密钥文件，并成导出公钥\n");

        System.out.println("\n请输入操作选项：");
    }
}
