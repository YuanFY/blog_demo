package com.yuanfy.monitorsite.common.util.keystore;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;




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
    public static void createNewLicense(boolean isTryUse) throws IOException{
        String wciFilePath = getFilePath("wci");
        LicenseDto entity = WlicGenerate.loadWci(wciFilePath);
        System.out.println(entity.getOrgName());
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
