package com.yuanfy.monitorsite.common.util.file;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.yuanfy.monitorsite.common.util.StringUtils;

/**
 * @Description: 系统路径工具类
 * @author yuanfy
 * @date 2017年12月28日 下午8:01:00 
 * @version 1.0
 */
public class ResPathUtils {
    private static Logger log = Logger.getLogger(ResPathUtils.class);
    
    public static String osType = System.getProperty("os.name");
    
    public static String fileSeparator = System.getProperty("file.separator");
    
    private static ResourceBundle resource = ResourceBundle.getBundle("path");
    
    public final static String WCI_FILE_NAME = "license.wci";
    
    public final static String WLIC_FILE_NAME = "license.wlic";
    
    public final static String PUBLICKEY = "publickey";
    /**
     * @Description: 获取所有额外存放文件的根路径
     * @return
     * @author yuanfy
     * @date 2017年12月29日 下午3:34:04 
     * @version 1.0
     */
    public static String getHome() {
        String userHome = System.getProperty("user.home");
        String path = null;
        if (isWindows()) {
            path = userHome + (userHome.endsWith(fileSeparator) ? "" : fileSeparator) + ".blog" +fileSeparator;
        }else {
            path = getPathByKey("root_path");
        }
        createPath(path);
        return path;
    }
    
    /**
     * @Description: 获取密钥存放的路径
     * @return
     * @author yuanfy
     * @date 2017年12月29日 下午3:42:17 
     * @version 1.0
     */
    public static String getKeyStorePath(){
        String path = getHome() + getPathByKey("keystore_path");
        createPath(path);
        return path;
    }
    
    /**
     * @Description: 获取license文件存放的路径
     * @author yuanfy
     * @date 2017年12月29日 下午3:42:17 
     * @version 1.0
     */
    public static String getLicensePath(){
        String path = getHome() + getPathByKey("license_file_path");
        createPath(path);
        return path;
    }
    /**
     * @Description: 获取wci文件存放的路径
     * @author yuanfy
     * @date 2017年12月29日 下午3:42:17 
     * @version 1.0
     */
    public static String getWciPath(){
        String path = getLicensePath() + fileSeparator + WCI_FILE_NAME;
        return path;
    }
    /**
     * @Description: 获取wlic文件存放的路径
     * @author yuanfy
     * @date 2017年12月29日 下午3:42:17 
     * @version 1.0
     */
    public static String getWlicPath(){
        String path = getLicensePath() + fileSeparator + WLIC_FILE_NAME;
        return path;
    }
    /**
     * @Description: 获取publickey文件存放的路径
     * @author yuanfy
     * @date 2017年12月29日 下午3:42:17 
     * @version 1.0
     */
    public static String getPublicKeyPath(){
        String path = getLicensePath() + fileSeparator + PUBLICKEY;
        return path;
    }
    
    /**
     * @Description: 判断系统是否为操作系统
     * @return
     * @author yuanfy
     * @date 2017年12月29日 下午3:43:28 
     * @version 1.0
     */
    public static boolean isWindows() {
        if (StringUtils.isEmpty(osType) || osType.toLowerCase().contains("windows")) {
            return true;
        }
        return false;
    }
    
    /**
     * @Description: 根据path.properties中的key获取其对应的值。
     * @author yuanfy
     * @date 2017年12月29日 下午3:44:36 
     * @version 1.0
     */
    public static String getPathByKey(String key) {
        return resource.getString(key);
    }
    
    /**
     * @Description: 根据路径创建（如果路径不存在，则创建路径）
     * @param path 路径
     * @param isDir 是否为目录
     * @return
     * @author yuanfy
     * @date 2017年12月29日 下午4:07:32 
     * @version 1.0
     */
    public static File createPath(String path, boolean isDir) {
        File file = new File(path);
        if (!file.exists()) {
            if (isDir) {
                file.mkdirs();
            } else {
                try {
                    file.createNewFile();
                }
                catch (IOException e) {
                    log.error("创建文件失败：", e);
                    return null;
                }
            }
        }
        return file;
    }
    
    public static File createPath(String path){
        return createPath(path, true);
    }
 
}
