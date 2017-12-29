package com.yuanfy.monitorsite.common.util.keystore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import com.yuanfy.monitorsite.common.util.file.ResPathUtils;


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
     * 密钥库密码
     */
    private final static String KEY_PASS = "blogpassw0rd520";
    /**
     * 密钥密码
     */
    private final static String STORAGE_PASS = "blogpassw0rd520";
    /**
     * 存放密钥对文件
     */
    private final static String STORAGE_FILE = "blog.jks";
    
    public static void generateKeyPair() {
        String filePath = ResPathUtils.getKeyStorePath() + ResPathUtils.fileSeparator + STORAGE_FILE;
        String command = createCommand(filePath);
        Process process = null;
        try {
            if (ResPathUtils.isWindows()) {
                process = Runtime.getRuntime().exec("cmd /c " + command);
            } else {
                String[] cmds = {"/bin/sh", "-c", command};
                process = Runtime.getRuntime().exec(cmds);
            }
            process.waitFor();//等待执行完毕
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
                log.info("密钥库生成成功:" + msg);
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
     * @version 6.5
     */
    private static String createCommand(String filePath) {
        StringBuffer command = new StringBuffer();
        command.append("keytool -genkeypair -dname \"CN=YuanFY, OU=YuanFY, O=YuanFY, L=GZ, ST=GD, C=CN\" -alias ")
        .append(ALIAS).append(" -keyalg RSA -keystore ").append(filePath).append(" -keypass ")
        .append(KEY_PASS).append(" -storepass ").append(STORAGE_PASS).append(" -validity 100000");
        return command.toString();
    }
}
